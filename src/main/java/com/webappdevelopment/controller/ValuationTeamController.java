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

import com.webappdevelopment.domain.ValuationTeam;
import com.webappdevelopment.dto.ValuationTeamDTO;
import com.webappdevelopment.dto.ValuationTeamSearchDTO;
import com.webappdevelopment.dto.ValuationTeamPageDTO;
import com.webappdevelopment.service.ValuationTeamService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/valuationTeam")
@RestController
public class ValuationTeamController {

	private final static Logger logger = LoggerFactory.getLogger(ValuationTeamController.class);

	@Autowired
	ValuationTeamService valuationTeamService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ValuationTeam> getAll() {

		List<ValuationTeam> valuationTeams = valuationTeamService.findAll();
		
		return valuationTeams;	
	}

	@GetMapping(value = "/{valuationTeamId}")
	@ResponseBody
	public ValuationTeamDTO getValuationTeam(@PathVariable Integer valuationTeamId) {
		
		return (valuationTeamService.getValuationTeamDTOById(valuationTeamId));
	}

 	@RequestMapping(value = "/addValuationTeam", method = RequestMethod.POST)
	public ResponseEntity<?> addValuationTeam(@RequestBody ValuationTeamDTO valuationTeamDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = valuationTeamService.addValuationTeam(valuationTeamDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/valuationTeams")
	public ResponseEntity<ValuationTeamPageDTO> getValuationTeams(ValuationTeamSearchDTO valuationTeamSearchDTO) {
 
		return valuationTeamService.getValuationTeams(valuationTeamSearchDTO);
	}	

	@RequestMapping(value = "/updateValuationTeam", method = RequestMethod.POST)
	public ResponseEntity<?> updateValuationTeam(@RequestBody ValuationTeamDTO valuationTeamDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = valuationTeamService.updateValuationTeam(valuationTeamDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
