package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Deal;
import com.webappdevelopment.dto.DealDTO;
import com.webappdevelopment.dto.DealSearchDTO;
import com.webappdevelopment.dto.DealPageDTO;
import com.webappdevelopment.dto.DealConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DealService extends GenericService<Deal, Integer> {

	List<Deal> findAll();

	ResultDTO addDeal(DealDTO dealDTO, RequestDTO requestDTO);

	ResultDTO updateDeal(DealDTO dealDTO, RequestDTO requestDTO);

    Page<Deal> getAllDeals(Pageable pageable);

    Page<Deal> getAllDeals(Specification<Deal> spec, Pageable pageable);

	ResponseEntity<DealPageDTO> getDeals(DealSearchDTO dealSearchDTO);
	
	List<DealDTO> convertDealsToDealDTOs(List<Deal> deals, DealConvertCriteriaDTO convertCriteria);

	DealDTO getDealDTOById(Integer dealId);







}





