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
import com.webappdevelopment.dao.PreliminaryOfferDAO;
import com.webappdevelopment.domain.PreliminaryOffer;
import com.webappdevelopment.dto.PreliminaryOfferDTO;
import com.webappdevelopment.dto.PreliminaryOfferSearchDTO;
import com.webappdevelopment.dto.PreliminaryOfferPageDTO;
import com.webappdevelopment.dto.PreliminaryOfferConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.PreliminaryOfferService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class PreliminaryOfferServiceImpl extends GenericServiceImpl<PreliminaryOffer, Integer> implements PreliminaryOfferService {

    private final static Logger logger = LoggerFactory.getLogger(PreliminaryOfferServiceImpl.class);

	@Autowired
	PreliminaryOfferDAO preliminaryOfferDao;

	


	@Override
	public GenericDAO<PreliminaryOffer, Integer> getDAO() {
		return (GenericDAO<PreliminaryOffer, Integer>) preliminaryOfferDao;
	}
	
	public List<PreliminaryOffer> findAll () {
		List<PreliminaryOffer> preliminaryOffers = preliminaryOfferDao.findAll();
		
		return preliminaryOffers;	
		
	}

	public ResultDTO addPreliminaryOffer(PreliminaryOfferDTO preliminaryOfferDTO, RequestDTO requestDTO) {

		PreliminaryOffer preliminaryOffer = new PreliminaryOffer();

		preliminaryOffer.setPreliminaryOfferId(preliminaryOfferDTO.getPreliminaryOfferId());


		preliminaryOffer.setOfferRange(preliminaryOfferDTO.getOfferRange());


		preliminaryOffer.setComments(preliminaryOfferDTO.getComments());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		preliminaryOffer = preliminaryOfferDao.save(preliminaryOffer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PreliminaryOffer> getAllPreliminaryOffers(Pageable pageable) {
		return preliminaryOfferDao.findAll(pageable);
	}

	public Page<PreliminaryOffer> getAllPreliminaryOffers(Specification<PreliminaryOffer> spec, Pageable pageable) {
		return preliminaryOfferDao.findAll(spec, pageable);
	}

	public ResponseEntity<PreliminaryOfferPageDTO> getPreliminaryOffers(PreliminaryOfferSearchDTO preliminaryOfferSearchDTO) {
	
			Integer preliminaryOfferId = preliminaryOfferSearchDTO.getPreliminaryOfferId(); 
  			String comments = preliminaryOfferSearchDTO.getComments(); 
 			String sortBy = preliminaryOfferSearchDTO.getSortBy();
			String sortOrder = preliminaryOfferSearchDTO.getSortOrder();
			String searchQuery = preliminaryOfferSearchDTO.getSearchQuery();
			Integer page = preliminaryOfferSearchDTO.getPage();
			Integer size = preliminaryOfferSearchDTO.getSize();

	        Specification<PreliminaryOffer> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, preliminaryOfferId, "preliminaryOfferId"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, comments, "comments"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("comments")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<PreliminaryOffer> preliminaryOffers = this.getAllPreliminaryOffers(spec, pageable);
		
		//System.out.println(String.valueOf(preliminaryOffers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(preliminaryOffers.getTotalPages()));
		
		List<PreliminaryOffer> preliminaryOffersList = preliminaryOffers.getContent();
		
		PreliminaryOfferConvertCriteriaDTO convertCriteria = new PreliminaryOfferConvertCriteriaDTO();
		List<PreliminaryOfferDTO> preliminaryOfferDTOs = this.convertPreliminaryOffersToPreliminaryOfferDTOs(preliminaryOffersList,convertCriteria);
		
		PreliminaryOfferPageDTO preliminaryOfferPageDTO = new PreliminaryOfferPageDTO();
		preliminaryOfferPageDTO.setPreliminaryOffers(preliminaryOfferDTOs);
		preliminaryOfferPageDTO.setTotalElements(preliminaryOffers.getTotalElements());
		return ResponseEntity.ok(preliminaryOfferPageDTO);
	}

	public List<PreliminaryOfferDTO> convertPreliminaryOffersToPreliminaryOfferDTOs(List<PreliminaryOffer> preliminaryOffers, PreliminaryOfferConvertCriteriaDTO convertCriteria) {
		
		List<PreliminaryOfferDTO> preliminaryOfferDTOs = new ArrayList<PreliminaryOfferDTO>();
		
		for (PreliminaryOffer preliminaryOffer : preliminaryOffers) {
			preliminaryOfferDTOs.add(convertPreliminaryOfferToPreliminaryOfferDTO(preliminaryOffer,convertCriteria));
		}
		
		return preliminaryOfferDTOs;

	}
	
	public PreliminaryOfferDTO convertPreliminaryOfferToPreliminaryOfferDTO(PreliminaryOffer preliminaryOffer, PreliminaryOfferConvertCriteriaDTO convertCriteria) {
		
		PreliminaryOfferDTO preliminaryOfferDTO = new PreliminaryOfferDTO();
		
		preliminaryOfferDTO.setPreliminaryOfferId(preliminaryOffer.getPreliminaryOfferId());

	
		preliminaryOfferDTO.setOfferRange(preliminaryOffer.getOfferRange());

	
		preliminaryOfferDTO.setComments(preliminaryOffer.getComments());

	

		
		return preliminaryOfferDTO;
	}

	public ResultDTO updatePreliminaryOffer(PreliminaryOfferDTO preliminaryOfferDTO, RequestDTO requestDTO) {
		
		PreliminaryOffer preliminaryOffer = preliminaryOfferDao.getById(preliminaryOfferDTO.getPreliminaryOfferId());

		preliminaryOffer.setPreliminaryOfferId(ControllerUtils.setValue(preliminaryOffer.getPreliminaryOfferId(), preliminaryOfferDTO.getPreliminaryOfferId()));

		preliminaryOffer.setOfferRange(ControllerUtils.setValue(preliminaryOffer.getOfferRange(), preliminaryOfferDTO.getOfferRange()));

		preliminaryOffer.setComments(ControllerUtils.setValue(preliminaryOffer.getComments(), preliminaryOfferDTO.getComments()));



        preliminaryOffer = preliminaryOfferDao.save(preliminaryOffer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PreliminaryOfferDTO getPreliminaryOfferDTOById(Integer preliminaryOfferId) {
	
		PreliminaryOffer preliminaryOffer = preliminaryOfferDao.getById(preliminaryOfferId);
			
		
		PreliminaryOfferConvertCriteriaDTO convertCriteria = new PreliminaryOfferConvertCriteriaDTO();
		return(this.convertPreliminaryOfferToPreliminaryOfferDTO(preliminaryOffer,convertCriteria));
	}







}
