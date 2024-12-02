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

import com.webappdevelopment.domain.PreliminaryOffer;
import com.webappdevelopment.dto.PreliminaryOfferDTO;
import com.webappdevelopment.dto.PreliminaryOfferSearchDTO;
import com.webappdevelopment.dto.PreliminaryOfferPageDTO;
import com.webappdevelopment.service.PreliminaryOfferService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/preliminaryOffer")
@RestController
public class PreliminaryOfferController {

	private final static Logger logger = LoggerFactory.getLogger(PreliminaryOfferController.class);

	@Autowired
	PreliminaryOfferService preliminaryOfferService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PreliminaryOffer> getAll() {

		List<PreliminaryOffer> preliminaryOffers = preliminaryOfferService.findAll();
		
		return preliminaryOffers;	
	}

	@GetMapping(value = "/{preliminaryOfferId}")
	@ResponseBody
	public PreliminaryOfferDTO getPreliminaryOffer(@PathVariable Integer preliminaryOfferId) {
		
		return (preliminaryOfferService.getPreliminaryOfferDTOById(preliminaryOfferId));
	}

 	@RequestMapping(value = "/addPreliminaryOffer", method = RequestMethod.POST)
	public ResponseEntity<?> addPreliminaryOffer(@RequestBody PreliminaryOfferDTO preliminaryOfferDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = preliminaryOfferService.addPreliminaryOffer(preliminaryOfferDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/preliminaryOffers")
	public ResponseEntity<PreliminaryOfferPageDTO> getPreliminaryOffers(PreliminaryOfferSearchDTO preliminaryOfferSearchDTO) {
 
		return preliminaryOfferService.getPreliminaryOffers(preliminaryOfferSearchDTO);
	}	

	@RequestMapping(value = "/updatePreliminaryOffer", method = RequestMethod.POST)
	public ResponseEntity<?> updatePreliminaryOffer(@RequestBody PreliminaryOfferDTO preliminaryOfferDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = preliminaryOfferService.updatePreliminaryOffer(preliminaryOfferDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
