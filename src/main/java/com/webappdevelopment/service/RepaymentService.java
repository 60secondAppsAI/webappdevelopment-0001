package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Repayment;
import com.webappdevelopment.dto.RepaymentDTO;
import com.webappdevelopment.dto.RepaymentSearchDTO;
import com.webappdevelopment.dto.RepaymentPageDTO;
import com.webappdevelopment.dto.RepaymentConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RepaymentService extends GenericService<Repayment, Integer> {

	List<Repayment> findAll();

	ResultDTO addRepayment(RepaymentDTO repaymentDTO, RequestDTO requestDTO);

	ResultDTO updateRepayment(RepaymentDTO repaymentDTO, RequestDTO requestDTO);

    Page<Repayment> getAllRepayments(Pageable pageable);

    Page<Repayment> getAllRepayments(Specification<Repayment> spec, Pageable pageable);

	ResponseEntity<RepaymentPageDTO> getRepayments(RepaymentSearchDTO repaymentSearchDTO);
	
	List<RepaymentDTO> convertRepaymentsToRepaymentDTOs(List<Repayment> repayments, RepaymentConvertCriteriaDTO convertCriteria);

	RepaymentDTO getRepaymentDTOById(Integer repaymentId);







}





