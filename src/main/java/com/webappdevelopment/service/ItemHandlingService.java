package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.ItemHandling;
import com.webappdevelopment.dto.ItemHandlingDTO;
import com.webappdevelopment.dto.ItemHandlingSearchDTO;
import com.webappdevelopment.dto.ItemHandlingPageDTO;
import com.webappdevelopment.dto.ItemHandlingConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ItemHandlingService extends GenericService<ItemHandling, Integer> {

	List<ItemHandling> findAll();

	ResultDTO addItemHandling(ItemHandlingDTO itemHandlingDTO, RequestDTO requestDTO);

	ResultDTO updateItemHandling(ItemHandlingDTO itemHandlingDTO, RequestDTO requestDTO);

    Page<ItemHandling> getAllItemHandlings(Pageable pageable);

    Page<ItemHandling> getAllItemHandlings(Specification<ItemHandling> spec, Pageable pageable);

	ResponseEntity<ItemHandlingPageDTO> getItemHandlings(ItemHandlingSearchDTO itemHandlingSearchDTO);
	
	List<ItemHandlingDTO> convertItemHandlingsToItemHandlingDTOs(List<ItemHandling> itemHandlings, ItemHandlingConvertCriteriaDTO convertCriteria);

	ItemHandlingDTO getItemHandlingDTOById(Integer itemHandlingId);







}





