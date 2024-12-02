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

import com.webappdevelopment.domain.CustomerSubmission;
import com.webappdevelopment.dto.CustomerSubmissionDTO;
import com.webappdevelopment.dto.CustomerSubmissionSearchDTO;
import com.webappdevelopment.dto.CustomerSubmissionPageDTO;
import com.webappdevelopment.service.CustomerSubmissionService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/customerSubmission")
@RestController
public class CustomerSubmissionController {

	private final static Logger logger = LoggerFactory.getLogger(CustomerSubmissionController.class);

	@Autowired
	CustomerSubmissionService customerSubmissionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CustomerSubmission> getAll() {

		List<CustomerSubmission> customerSubmissions = customerSubmissionService.findAll();
		
		return customerSubmissions;	
	}

	@GetMapping(value = "/{customerSubmissionId}")
	@ResponseBody
	public CustomerSubmissionDTO getCustomerSubmission(@PathVariable Integer customerSubmissionId) {
		
		return (customerSubmissionService.getCustomerSubmissionDTOById(customerSubmissionId));
	}

 	@RequestMapping(value = "/addCustomerSubmission", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomerSubmission(@RequestBody CustomerSubmissionDTO customerSubmissionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerSubmissionService.addCustomerSubmission(customerSubmissionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/customerSubmissions")
	public ResponseEntity<CustomerSubmissionPageDTO> getCustomerSubmissions(CustomerSubmissionSearchDTO customerSubmissionSearchDTO) {
 
		return customerSubmissionService.getCustomerSubmissions(customerSubmissionSearchDTO);
	}	

	@RequestMapping(value = "/updateCustomerSubmission", method = RequestMethod.POST)
	public ResponseEntity<?> updateCustomerSubmission(@RequestBody CustomerSubmissionDTO customerSubmissionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerSubmissionService.updateCustomerSubmission(customerSubmissionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
