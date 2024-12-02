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

import com.webappdevelopment.domain.Valuation;
import com.webappdevelopment.dto.ValuationDTO;
import com.webappdevelopment.dto.ValuationSearchDTO;
import com.webappdevelopment.dto.ValuationPageDTO;
import com.webappdevelopment.service.ValuationService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/valuation")
@RestController
public class ValuationController {

	private final static Logger logger = LoggerFactory.getLogger(ValuationController.class);

	@Autowired
	ValuationService valuationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Valuation> getAll() {

		List<Valuation> valuations = valuationService.findAll();
		
		return valuations;	
	}

	@GetMapping(value = "/{valuationId}")
	@ResponseBody
	public ValuationDTO getValuation(@PathVariable Integer valuationId) {
		
		return (valuationService.getValuationDTOById(valuationId));
	}

 	@RequestMapping(value = "/addValuation", method = RequestMethod.POST)
	public ResponseEntity<?> addValuation(@RequestBody ValuationDTO valuationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = valuationService.addValuation(valuationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/valuations")
	public ResponseEntity<ValuationPageDTO> getValuations(ValuationSearchDTO valuationSearchDTO) {
 
		return valuationService.getValuations(valuationSearchDTO);
	}	

	@RequestMapping(value = "/updateValuation", method = RequestMethod.POST)
	public ResponseEntity<?> updateValuation(@RequestBody ValuationDTO valuationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = valuationService.updateValuation(valuationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
