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

import com.webappdevelopment.domain.Deal;
import com.webappdevelopment.dto.DealDTO;
import com.webappdevelopment.dto.DealSearchDTO;
import com.webappdevelopment.dto.DealPageDTO;
import com.webappdevelopment.service.DealService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/deal")
@RestController
public class DealController {

	private final static Logger logger = LoggerFactory.getLogger(DealController.class);

	@Autowired
	DealService dealService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Deal> getAll() {

		List<Deal> deals = dealService.findAll();
		
		return deals;	
	}

	@GetMapping(value = "/{dealId}")
	@ResponseBody
	public DealDTO getDeal(@PathVariable Integer dealId) {
		
		return (dealService.getDealDTOById(dealId));
	}

 	@RequestMapping(value = "/addDeal", method = RequestMethod.POST)
	public ResponseEntity<?> addDeal(@RequestBody DealDTO dealDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealService.addDeal(dealDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/deals")
	public ResponseEntity<DealPageDTO> getDeals(DealSearchDTO dealSearchDTO) {
 
		return dealService.getDeals(dealSearchDTO);
	}	

	@RequestMapping(value = "/updateDeal", method = RequestMethod.POST)
	public ResponseEntity<?> updateDeal(@RequestBody DealDTO dealDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealService.updateDeal(dealDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
