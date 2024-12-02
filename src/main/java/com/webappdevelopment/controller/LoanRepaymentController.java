package com.webappdevelopment.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.webappdevelopment.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.webappdevelopment.domain.LoanRepayment;
import com.webappdevelopment.dto.LoanRepaymentDTO;
import com.webappdevelopment.dto.LoanRepaymentSearchDTO;
import com.webappdevelopment.dto.LoanRepaymentPageDTO;
import com.webappdevelopment.service.LoanRepaymentService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/loanRepayment")
@RestController
public class LoanRepaymentController {

	private final static Logger logger = LoggerFactory.getLogger(LoanRepaymentController.class);

	@Autowired
	LoanRepaymentService loanRepaymentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<LoanRepayment> getAll() {

		List<LoanRepayment> loanRepayments = loanRepaymentService.findAll();
		
		return loanRepayments;	
	}

	@GetMapping(value = "/{loanRepaymentId}")
	@ResponseBody
	public LoanRepaymentDTO getLoanRepayment(@PathVariable Integer loanRepaymentId) {
		
		return (loanRepaymentService.getLoanRepaymentDTOById(loanRepaymentId));
	}

 	@RequestMapping(value = "/addLoanRepayment", method = RequestMethod.POST)
	public ResponseEntity<?> addLoanRepayment(@RequestBody LoanRepaymentDTO loanRepaymentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loanRepaymentService.addLoanRepayment(loanRepaymentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/loanRepayments")
	public ResponseEntity<LoanRepaymentPageDTO> getLoanRepayments(LoanRepaymentSearchDTO loanRepaymentSearchDTO) {
 
		return loanRepaymentService.getLoanRepayments(loanRepaymentSearchDTO);
	}	

	@RequestMapping(value = "/updateLoanRepayment", method = RequestMethod.POST)
	public ResponseEntity<?> updateLoanRepayment(@RequestBody LoanRepaymentDTO loanRepaymentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loanRepaymentService.updateLoanRepayment(loanRepaymentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
