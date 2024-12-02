package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.DealRep;
import com.webappdevelopment.dto.DealRepDTO;
import com.webappdevelopment.dto.DealRepSearchDTO;
import com.webappdevelopment.dto.DealRepPageDTO;
import com.webappdevelopment.dto.DealRepConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DealRepService extends GenericService<DealRep, Integer> {

	List<DealRep> findAll();

	ResultDTO addDealRep(DealRepDTO dealRepDTO, RequestDTO requestDTO);

	ResultDTO updateDealRep(DealRepDTO dealRepDTO, RequestDTO requestDTO);

    Page<DealRep> getAllDealReps(Pageable pageable);

    Page<DealRep> getAllDealReps(Specification<DealRep> spec, Pageable pageable);

	ResponseEntity<DealRepPageDTO> getDealReps(DealRepSearchDTO dealRepSearchDTO);
	
	List<DealRepDTO> convertDealRepsToDealRepDTOs(List<DealRep> dealReps, DealRepConvertCriteriaDTO convertCriteria);

	DealRepDTO getDealRepDTOById(Integer dealRepId);







}





