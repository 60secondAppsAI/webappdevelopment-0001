package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Agreement;
import com.webappdevelopment.dto.AgreementDTO;
import com.webappdevelopment.dto.AgreementSearchDTO;
import com.webappdevelopment.dto.AgreementPageDTO;
import com.webappdevelopment.dto.AgreementConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AgreementService extends GenericService<Agreement, Integer> {

	List<Agreement> findAll();

	ResultDTO addAgreement(AgreementDTO agreementDTO, RequestDTO requestDTO);

	ResultDTO updateAgreement(AgreementDTO agreementDTO, RequestDTO requestDTO);

    Page<Agreement> getAllAgreements(Pageable pageable);

    Page<Agreement> getAllAgreements(Specification<Agreement> spec, Pageable pageable);

	ResponseEntity<AgreementPageDTO> getAgreements(AgreementSearchDTO agreementSearchDTO);
	
	List<AgreementDTO> convertAgreementsToAgreementDTOs(List<Agreement> agreements, AgreementConvertCriteriaDTO convertCriteria);

	AgreementDTO getAgreementDTOById(Integer agreementId);







}





