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

import com.webappdevelopment.domain.ShippingBox;
import com.webappdevelopment.dto.ShippingBoxDTO;
import com.webappdevelopment.dto.ShippingBoxSearchDTO;
import com.webappdevelopment.dto.ShippingBoxPageDTO;
import com.webappdevelopment.service.ShippingBoxService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/shippingBox")
@RestController
public class ShippingBoxController {

	private final static Logger logger = LoggerFactory.getLogger(ShippingBoxController.class);

	@Autowired
	ShippingBoxService shippingBoxService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ShippingBox> getAll() {

		List<ShippingBox> shippingBoxs = shippingBoxService.findAll();
		
		return shippingBoxs;	
	}

	@GetMapping(value = "/{shippingBoxId}")
	@ResponseBody
	public ShippingBoxDTO getShippingBox(@PathVariable Integer shippingBoxId) {
		
		return (shippingBoxService.getShippingBoxDTOById(shippingBoxId));
	}

 	@RequestMapping(value = "/addShippingBox", method = RequestMethod.POST)
	public ResponseEntity<?> addShippingBox(@RequestBody ShippingBoxDTO shippingBoxDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingBoxService.addShippingBox(shippingBoxDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/shippingBoxs")
	public ResponseEntity<ShippingBoxPageDTO> getShippingBoxs(ShippingBoxSearchDTO shippingBoxSearchDTO) {
 
		return shippingBoxService.getShippingBoxs(shippingBoxSearchDTO);
	}	

	@RequestMapping(value = "/updateShippingBox", method = RequestMethod.POST)
	public ResponseEntity<?> updateShippingBox(@RequestBody ShippingBoxDTO shippingBoxDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingBoxService.updateShippingBox(shippingBoxDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
