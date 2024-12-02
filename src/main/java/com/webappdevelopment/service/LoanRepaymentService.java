package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.LoanRepayment;
import com.webappdevelopment.dto.LoanRepaymentDTO;
import com.webappdevelopment.dto.LoanRepaymentSearchDTO;
import com.webappdevelopment.dto.LoanRepaymentPageDTO;
import com.webappdevelopment.dto.LoanRepaymentConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoanRepaymentService extends GenericService<LoanRepayment, Integer> {

	List<LoanRepayment> findAll();

	ResultDTO addLoanRepayment(LoanRepaymentDTO loanRepaymentDTO, RequestDTO requestDTO);

	ResultDTO updateLoanRepayment(LoanRepaymentDTO loanRepaymentDTO, RequestDTO requestDTO);

    Page<LoanRepayment> getAllLoanRepayments(Pageable pageable);

    Page<LoanRepayment> getAllLoanRepayments(Specification<LoanRepayment> spec, Pageable pageable);

	ResponseEntity<LoanRepaymentPageDTO> getLoanRepayments(LoanRepaymentSearchDTO loanRepaymentSearchDTO);
	
	List<LoanRepaymentDTO> convertLoanRepaymentsToLoanRepaymentDTOs(List<LoanRepayment> loanRepayments, LoanRepaymentConvertCriteriaDTO convertCriteria);

	LoanRepaymentDTO getLoanRepaymentDTOById(Integer loanRepaymentId);







}





