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

import com.webappdevelopment.domain.DealRep;
import com.webappdevelopment.dto.DealRepDTO;
import com.webappdevelopment.dto.DealRepSearchDTO;
import com.webappdevelopment.dto.DealRepPageDTO;
import com.webappdevelopment.service.DealRepService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/dealRep")
@RestController
public class DealRepController {

	private final static Logger logger = LoggerFactory.getLogger(DealRepController.class);

	@Autowired
	DealRepService dealRepService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DealRep> getAll() {

		List<DealRep> dealReps = dealRepService.findAll();
		
		return dealReps;	
	}

	@GetMapping(value = "/{dealRepId}")
	@ResponseBody
	public DealRepDTO getDealRep(@PathVariable Integer dealRepId) {
		
		return (dealRepService.getDealRepDTOById(dealRepId));
	}

 	@RequestMapping(value = "/addDealRep", method = RequestMethod.POST)
	public ResponseEntity<?> addDealRep(@RequestBody DealRepDTO dealRepDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealRepService.addDealRep(dealRepDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/dealReps")
	public ResponseEntity<DealRepPageDTO> getDealReps(DealRepSearchDTO dealRepSearchDTO) {
 
		return dealRepService.getDealReps(dealRepSearchDTO);
	}	

	@RequestMapping(value = "/updateDealRep", method = RequestMethod.POST)
	public ResponseEntity<?> updateDealRep(@RequestBody DealRepDTO dealRepDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealRepService.updateDealRep(dealRepDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
