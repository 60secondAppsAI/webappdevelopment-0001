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
import com.webappdevelopment.dao.ItemHandlingDAO;
import com.webappdevelopment.domain.ItemHandling;
import com.webappdevelopment.dto.ItemHandlingDTO;
import com.webappdevelopment.dto.ItemHandlingSearchDTO;
import com.webappdevelopment.dto.ItemHandlingPageDTO;
import com.webappdevelopment.dto.ItemHandlingConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.ItemHandlingService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class ItemHandlingServiceImpl extends GenericServiceImpl<ItemHandling, Integer> implements ItemHandlingService {

    private final static Logger logger = LoggerFactory.getLogger(ItemHandlingServiceImpl.class);

	@Autowired
	ItemHandlingDAO itemHandlingDao;

	


	@Override
	public GenericDAO<ItemHandling, Integer> getDAO() {
		return (GenericDAO<ItemHandling, Integer>) itemHandlingDao;
	}
	
	public List<ItemHandling> findAll () {
		List<ItemHandling> itemHandlings = itemHandlingDao.findAll();
		
		return itemHandlings;	
		
	}

	public ResultDTO addItemHandling(ItemHandlingDTO itemHandlingDTO, RequestDTO requestDTO) {

		ItemHandling itemHandling = new ItemHandling();

		itemHandling.setItemHandlingId(itemHandlingDTO.getItemHandlingId());


		itemHandling.setUnboxingVideo(itemHandlingDTO.getUnboxingVideo());


		itemHandling.setRfidTag(itemHandlingDTO.getRfidTag());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		itemHandling = itemHandlingDao.save(itemHandling);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ItemHandling> getAllItemHandlings(Pageable pageable) {
		return itemHandlingDao.findAll(pageable);
	}

	public Page<ItemHandling> getAllItemHandlings(Specification<ItemHandling> spec, Pageable pageable) {
		return itemHandlingDao.findAll(spec, pageable);
	}

	public ResponseEntity<ItemHandlingPageDTO> getItemHandlings(ItemHandlingSearchDTO itemHandlingSearchDTO) {
	
			Integer itemHandlingId = itemHandlingSearchDTO.getItemHandlingId(); 
 			String unboxingVideo = itemHandlingSearchDTO.getUnboxingVideo(); 
 			String rfidTag = itemHandlingSearchDTO.getRfidTag(); 
 			String sortBy = itemHandlingSearchDTO.getSortBy();
			String sortOrder = itemHandlingSearchDTO.getSortOrder();
			String searchQuery = itemHandlingSearchDTO.getSearchQuery();
			Integer page = itemHandlingSearchDTO.getPage();
			Integer size = itemHandlingSearchDTO.getSize();

	        Specification<ItemHandling> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, itemHandlingId, "itemHandlingId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, unboxingVideo, "unboxingVideo"); 
			
			spec = ControllerUtils.andIfNecessary(spec, rfidTag, "rfidTag"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("unboxingVideo")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("rfidTag")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ItemHandling> itemHandlings = this.getAllItemHandlings(spec, pageable);
		
		//System.out.println(String.valueOf(itemHandlings.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(itemHandlings.getTotalPages()));
		
		List<ItemHandling> itemHandlingsList = itemHandlings.getContent();
		
		ItemHandlingConvertCriteriaDTO convertCriteria = new ItemHandlingConvertCriteriaDTO();
		List<ItemHandlingDTO> itemHandlingDTOs = this.convertItemHandlingsToItemHandlingDTOs(itemHandlingsList,convertCriteria);
		
		ItemHandlingPageDTO itemHandlingPageDTO = new ItemHandlingPageDTO();
		itemHandlingPageDTO.setItemHandlings(itemHandlingDTOs);
		itemHandlingPageDTO.setTotalElements(itemHandlings.getTotalElements());
		return ResponseEntity.ok(itemHandlingPageDTO);
	}

	public List<ItemHandlingDTO> convertItemHandlingsToItemHandlingDTOs(List<ItemHandling> itemHandlings, ItemHandlingConvertCriteriaDTO convertCriteria) {
		
		List<ItemHandlingDTO> itemHandlingDTOs = new ArrayList<ItemHandlingDTO>();
		
		for (ItemHandling itemHandling : itemHandlings) {
			itemHandlingDTOs.add(convertItemHandlingToItemHandlingDTO(itemHandling,convertCriteria));
		}
		
		return itemHandlingDTOs;

	}
	
	public ItemHandlingDTO convertItemHandlingToItemHandlingDTO(ItemHandling itemHandling, ItemHandlingConvertCriteriaDTO convertCriteria) {
		
		ItemHandlingDTO itemHandlingDTO = new ItemHandlingDTO();
		
		itemHandlingDTO.setItemHandlingId(itemHandling.getItemHandlingId());

	
		itemHandlingDTO.setUnboxingVideo(itemHandling.getUnboxingVideo());

	
		itemHandlingDTO.setRfidTag(itemHandling.getRfidTag());

	

		
		return itemHandlingDTO;
	}

	public ResultDTO updateItemHandling(ItemHandlingDTO itemHandlingDTO, RequestDTO requestDTO) {
		
		ItemHandling itemHandling = itemHandlingDao.getById(itemHandlingDTO.getItemHandlingId());

		itemHandling.setItemHandlingId(ControllerUtils.setValue(itemHandling.getItemHandlingId(), itemHandlingDTO.getItemHandlingId()));

		itemHandling.setUnboxingVideo(ControllerUtils.setValue(itemHandling.getUnboxingVideo(), itemHandlingDTO.getUnboxingVideo()));

		itemHandling.setRfidTag(ControllerUtils.setValue(itemHandling.getRfidTag(), itemHandlingDTO.getRfidTag()));



        itemHandling = itemHandlingDao.save(itemHandling);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ItemHandlingDTO getItemHandlingDTOById(Integer itemHandlingId) {
	
		ItemHandling itemHandling = itemHandlingDao.getById(itemHandlingId);
			
		
		ItemHandlingConvertCriteriaDTO convertCriteria = new ItemHandlingConvertCriteriaDTO();
		return(this.convertItemHandlingToItemHandlingDTO(itemHandling,convertCriteria));
	}







}
