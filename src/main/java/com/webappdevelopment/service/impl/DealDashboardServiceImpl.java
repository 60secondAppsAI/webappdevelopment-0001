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
import com.webappdevelopment.dao.DealDashboardDAO;
import com.webappdevelopment.domain.DealDashboard;
import com.webappdevelopment.dto.DealDashboardDTO;
import com.webappdevelopment.dto.DealDashboardSearchDTO;
import com.webappdevelopment.dto.DealDashboardPageDTO;
import com.webappdevelopment.dto.DealDashboardConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.DealDashboardService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class DealDashboardServiceImpl extends GenericServiceImpl<DealDashboard, Integer> implements DealDashboardService {

    private final static Logger logger = LoggerFactory.getLogger(DealDashboardServiceImpl.class);

	@Autowired
	DealDashboardDAO dealDashboardDao;

	


	@Override
	public GenericDAO<DealDashboard, Integer> getDAO() {
		return (GenericDAO<DealDashboard, Integer>) dealDashboardDao;
	}
	
	public List<DealDashboard> findAll () {
		List<DealDashboard> dealDashboards = dealDashboardDao.findAll();
		
		return dealDashboards;	
		
	}

	public ResultDTO addDealDashboard(DealDashboardDTO dealDashboardDTO, RequestDTO requestDTO) {

		DealDashboard dealDashboard = new DealDashboard();

		dealDashboard.setDealDashboardId(dealDashboardDTO.getDealDashboardId());


		dealDashboard.setDeals(dealDashboardDTO.getDeals());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		dealDashboard = dealDashboardDao.save(dealDashboard);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DealDashboard> getAllDealDashboards(Pageable pageable) {
		return dealDashboardDao.findAll(pageable);
	}

	public Page<DealDashboard> getAllDealDashboards(Specification<DealDashboard> spec, Pageable pageable) {
		return dealDashboardDao.findAll(spec, pageable);
	}

	public ResponseEntity<DealDashboardPageDTO> getDealDashboards(DealDashboardSearchDTO dealDashboardSearchDTO) {
	
			Integer dealDashboardId = dealDashboardSearchDTO.getDealDashboardId(); 
 			String deals = dealDashboardSearchDTO.getDeals(); 
 			String sortBy = dealDashboardSearchDTO.getSortBy();
			String sortOrder = dealDashboardSearchDTO.getSortOrder();
			String searchQuery = dealDashboardSearchDTO.getSearchQuery();
			Integer page = dealDashboardSearchDTO.getPage();
			Integer size = dealDashboardSearchDTO.getSize();

	        Specification<DealDashboard> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, dealDashboardId, "dealDashboardId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, deals, "deals"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("deals")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<DealDashboard> dealDashboards = this.getAllDealDashboards(spec, pageable);
		
		//System.out.println(String.valueOf(dealDashboards.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(dealDashboards.getTotalPages()));
		
		List<DealDashboard> dealDashboardsList = dealDashboards.getContent();
		
		DealDashboardConvertCriteriaDTO convertCriteria = new DealDashboardConvertCriteriaDTO();
		List<DealDashboardDTO> dealDashboardDTOs = this.convertDealDashboardsToDealDashboardDTOs(dealDashboardsList,convertCriteria);
		
		DealDashboardPageDTO dealDashboardPageDTO = new DealDashboardPageDTO();
		dealDashboardPageDTO.setDealDashboards(dealDashboardDTOs);
		dealDashboardPageDTO.setTotalElements(dealDashboards.getTotalElements());
		return ResponseEntity.ok(dealDashboardPageDTO);
	}

	public List<DealDashboardDTO> convertDealDashboardsToDealDashboardDTOs(List<DealDashboard> dealDashboards, DealDashboardConvertCriteriaDTO convertCriteria) {
		
		List<DealDashboardDTO> dealDashboardDTOs = new ArrayList<DealDashboardDTO>();
		
		for (DealDashboard dealDashboard : dealDashboards) {
			dealDashboardDTOs.add(convertDealDashboardToDealDashboardDTO(dealDashboard,convertCriteria));
		}
		
		return dealDashboardDTOs;

	}
	
	public DealDashboardDTO convertDealDashboardToDealDashboardDTO(DealDashboard dealDashboard, DealDashboardConvertCriteriaDTO convertCriteria) {
		
		DealDashboardDTO dealDashboardDTO = new DealDashboardDTO();
		
		dealDashboardDTO.setDealDashboardId(dealDashboard.getDealDashboardId());

	
		dealDashboardDTO.setDeals(dealDashboard.getDeals());

	

		
		return dealDashboardDTO;
	}

	public ResultDTO updateDealDashboard(DealDashboardDTO dealDashboardDTO, RequestDTO requestDTO) {
		
		DealDashboard dealDashboard = dealDashboardDao.getById(dealDashboardDTO.getDealDashboardId());

		dealDashboard.setDealDashboardId(ControllerUtils.setValue(dealDashboard.getDealDashboardId(), dealDashboardDTO.getDealDashboardId()));

		dealDashboard.setDeals(ControllerUtils.setValue(dealDashboard.getDeals(), dealDashboardDTO.getDeals()));



        dealDashboard = dealDashboardDao.save(dealDashboard);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DealDashboardDTO getDealDashboardDTOById(Integer dealDashboardId) {
	
		DealDashboard dealDashboard = dealDashboardDao.getById(dealDashboardId);
			
		
		DealDashboardConvertCriteriaDTO convertCriteria = new DealDashboardConvertCriteriaDTO();
		return(this.convertDealDashboardToDealDashboardDTO(dealDashboard,convertCriteria));
	}







}
