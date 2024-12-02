package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Valuation;
import com.webappdevelopment.dto.ValuationDTO;
import com.webappdevelopment.dto.ValuationSearchDTO;
import com.webappdevelopment.dto.ValuationPageDTO;
import com.webappdevelopment.dto.ValuationConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ValuationService extends GenericService<Valuation, Integer> {

	List<Valuation> findAll();

	ResultDTO addValuation(ValuationDTO valuationDTO, RequestDTO requestDTO);

	ResultDTO updateValuation(ValuationDTO valuationDTO, RequestDTO requestDTO);

    Page<Valuation> getAllValuations(Pageable pageable);

    Page<Valuation> getAllValuations(Specification<Valuation> spec, Pageable pageable);

	ResponseEntity<ValuationPageDTO> getValuations(ValuationSearchDTO valuationSearchDTO);
	
	List<ValuationDTO> convertValuationsToValuationDTOs(List<Valuation> valuations, ValuationConvertCriteriaDTO convertCriteria);

	ValuationDTO getValuationDTOById(Integer valuationId);







}





