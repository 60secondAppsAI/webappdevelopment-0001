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

import com.webappdevelopment.domain.ItemHandling;
import com.webappdevelopment.dto.ItemHandlingDTO;
import com.webappdevelopment.dto.ItemHandlingSearchDTO;
import com.webappdevelopment.dto.ItemHandlingPageDTO;
import com.webappdevelopment.service.ItemHandlingService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/itemHandling")
@RestController
public class ItemHandlingController {

	private final static Logger logger = LoggerFactory.getLogger(ItemHandlingController.class);

	@Autowired
	ItemHandlingService itemHandlingService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ItemHandling> getAll() {

		List<ItemHandling> itemHandlings = itemHandlingService.findAll();
		
		return itemHandlings;	
	}

	@GetMapping(value = "/{itemHandlingId}")
	@ResponseBody
	public ItemHandlingDTO getItemHandling(@PathVariable Integer itemHandlingId) {
		
		return (itemHandlingService.getItemHandlingDTOById(itemHandlingId));
	}

 	@RequestMapping(value = "/addItemHandling", method = RequestMethod.POST)
	public ResponseEntity<?> addItemHandling(@RequestBody ItemHandlingDTO itemHandlingDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = itemHandlingService.addItemHandling(itemHandlingDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/itemHandlings")
	public ResponseEntity<ItemHandlingPageDTO> getItemHandlings(ItemHandlingSearchDTO itemHandlingSearchDTO) {
 
		return itemHandlingService.getItemHandlings(itemHandlingSearchDTO);
	}	

	@RequestMapping(value = "/updateItemHandling", method = RequestMethod.POST)
	public ResponseEntity<?> updateItemHandling(@RequestBody ItemHandlingDTO itemHandlingDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = itemHandlingService.updateItemHandling(itemHandlingDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
