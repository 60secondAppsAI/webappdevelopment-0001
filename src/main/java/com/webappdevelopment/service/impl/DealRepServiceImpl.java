package com.webappdevelopment.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.service.impl.GenericServiceImpl;
import com.webappdevelopment.dao.DealRepDAO;
import com.webappdevelopment.domain.DealRep;
import com.webappdevelopment.dto.DealRepDTO;
import com.webappdevelopment.dto.DealRepSearchDTO;
import com.webappdevelopment.dto.DealRepPageDTO;
import com.webappdevelopment.dto.DealRepConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.DealRepService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class DealRepServiceImpl extends GenericServiceImpl<DealRep, Integer> implements DealRepService {

    private final static Logger logger = LoggerFactory.getLogger(DealRepServiceImpl.class);

	@Autowired
	DealRepDAO dealRepDao;

	


	@Override
	public GenericDAO<DealRep, Integer> getDAO() {
		return (GenericDAO<DealRep, Integer>) dealRepDao;
	}
	
	public List<DealRep> findAll () {
		List<DealRep> dealReps = dealRepDao.findAll();
		
		return dealReps;	
		
	}

	public ResultDTO addDealRep(DealRepDTO dealRepDTO, RequestDTO requestDTO) {

		DealRep dealRep = new DealRep();

		dealRep.setDealRepId(dealRepDTO.getDealRepId());


		dealRep.setName(dealRepDTO.getName());


		dealRep.setContact(dealRepDTO.getContact());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		dealRep = dealRepDao.save(dealRep);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DealRep> getAllDealReps(Pageable pageable) {
		return dealRepDao.findAll(pageable);
	}

	public Page<DealRep> getAllDealReps(Specification<DealRep> spec, Pageable pageable) {
		return dealRepDao.findAll(spec, pageable);
	}

	public ResponseEntity<DealRepPageDTO> getDealReps(DealRepSearchDTO dealRepSearchDTO) {
	
			Integer dealRepId = dealRepSearchDTO.getDealRepId(); 
 			String name = dealRepSearchDTO.getName(); 
 			String contact = dealRepSearchDTO.getContact(); 
 			String sortBy = dealRepSearchDTO.getSortBy();
			String sortOrder = dealRepSearchDTO.getSortOrder();
			String searchQuery = dealRepSearchDTO.getSearchQuery();
			Integer page = dealRepSearchDTO.getPage();
			Integer size = dealRepSearchDTO.getSize();

	        Specification<DealRep> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, dealRepId, "dealRepId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contact, "contact"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contact")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<DealRep> dealReps = this.getAllDealReps(spec, pageable);
		
		//System.out.println(String.valueOf(dealReps.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(dealReps.getTotalPages()));
		
		List<DealRep> dealRepsList = dealReps.getContent();
		
		DealRepConvertCriteriaDTO convertCriteria = new DealRepConvertCriteriaDTO();
		List<DealRepDTO> dealRepDTOs = this.convertDealRepsToDealRepDTOs(dealRepsList,convertCriteria);
		
		DealRepPageDTO dealRepPageDTO = new DealRepPageDTO();
		dealRepPageDTO.setDealReps(dealRepDTOs);
		dealRepPageDTO.setTotalElements(dealReps.getTotalElements());
		return ResponseEntity.ok(dealRepPageDTO);
	}

	public List<DealRepDTO> convertDealRepsToDealRepDTOs(List<DealRep> dealReps, DealRepConvertCriteriaDTO convertCriteria) {
		
		List<DealRepDTO> dealRepDTOs = new ArrayList<DealRepDTO>();
		
		for (DealRep dealRep : dealReps) {
			dealRepDTOs.add(convertDealRepToDealRepDTO(dealRep,convertCriteria));
		}
		
		return dealRepDTOs;

	}
	
	public DealRepDTO convertDealRepToDealRepDTO(DealRep dealRep, DealRepConvertCriteriaDTO convertCriteria) {
		
		DealRepDTO dealRepDTO = new DealRepDTO();
		
		dealRepDTO.setDealRepId(dealRep.getDealRepId());

	
		dealRepDTO.setName(dealRep.getName());

	
		dealRepDTO.setContact(dealRep.getContact());

	

		
		return dealRepDTO;
	}

	public ResultDTO updateDealRep(DealRepDTO dealRepDTO, RequestDTO requestDTO) {
		
		DealRep dealRep = dealRepDao.getById(dealRepDTO.getDealRepId());

		dealRep.setDealRepId(ControllerUtils.setValue(dealRep.getDealRepId(), dealRepDTO.getDealRepId()));

		dealRep.setName(ControllerUtils.setValue(dealRep.getName(), dealRepDTO.getName()));

		dealRep.setContact(ControllerUtils.setValue(dealRep.getContact(), dealRepDTO.getContact()));



        dealRep = dealRepDao.save(dealRep);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DealRepDTO getDealRepDTOById(Integer dealRepId) {
	
		DealRep dealRep = dealRepDao.getById(dealRepId);
			
		
		DealRepConvertCriteriaDTO convertCriteria = new DealRepConvertCriteriaDTO();
		return(this.convertDealRepToDealRepDTO(dealRep,convertCriteria));
	}







}
