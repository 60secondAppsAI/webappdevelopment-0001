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

import com.webappdevelopment.domain.Website;
import com.webappdevelopment.dto.WebsiteDTO;
import com.webappdevelopment.dto.WebsiteSearchDTO;
import com.webappdevelopment.dto.WebsitePageDTO;
import com.webappdevelopment.service.WebsiteService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/website")
@RestController
public class WebsiteController {

	private final static Logger logger = LoggerFactory.getLogger(WebsiteController.class);

	@Autowired
	WebsiteService websiteService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Website> getAll() {

		List<Website> websites = websiteService.findAll();
		
		return websites;	
	}

	@GetMapping(value = "/{websiteId}")
	@ResponseBody
	public WebsiteDTO getWebsite(@PathVariable Integer websiteId) {
		
		return (websiteService.getWebsiteDTOById(websiteId));
	}

 	@RequestMapping(value = "/addWebsite", method = RequestMethod.POST)
	public ResponseEntity<?> addWebsite(@RequestBody WebsiteDTO websiteDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = websiteService.addWebsite(websiteDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/websites")
	public ResponseEntity<WebsitePageDTO> getWebsites(WebsiteSearchDTO websiteSearchDTO) {
 
		return websiteService.getWebsites(websiteSearchDTO);
	}	

	@RequestMapping(value = "/updateWebsite", method = RequestMethod.POST)
	public ResponseEntity<?> updateWebsite(@RequestBody WebsiteDTO websiteDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = websiteService.updateWebsite(websiteDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
