package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.CustomerSubmission;
import com.webappdevelopment.dto.CustomerSubmissionDTO;
import com.webappdevelopment.dto.CustomerSubmissionSearchDTO;
import com.webappdevelopment.dto.CustomerSubmissionPageDTO;
import com.webappdevelopment.dto.CustomerSubmissionConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CustomerSubmissionService extends GenericService<CustomerSubmission, Integer> {

	List<CustomerSubmission> findAll();

	ResultDTO addCustomerSubmission(CustomerSubmissionDTO customerSubmissionDTO, RequestDTO requestDTO);

	ResultDTO updateCustomerSubmission(CustomerSubmissionDTO customerSubmissionDTO, RequestDTO requestDTO);

    Page<CustomerSubmission> getAllCustomerSubmissions(Pageable pageable);

    Page<CustomerSubmission> getAllCustomerSubmissions(Specification<CustomerSubmission> spec, Pageable pageable);

	ResponseEntity<CustomerSubmissionPageDTO> getCustomerSubmissions(CustomerSubmissionSearchDTO customerSubmissionSearchDTO);
	
	List<CustomerSubmissionDTO> convertCustomerSubmissionsToCustomerSubmissionDTOs(List<CustomerSubmission> customerSubmissions, CustomerSubmissionConvertCriteriaDTO convertCriteria);

	CustomerSubmissionDTO getCustomerSubmissionDTOById(Integer customerSubmissionId);







}





