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

import com.webappdevelopment.domain.UserAccount;
import com.webappdevelopment.dto.UserAccountDTO;
import com.webappdevelopment.dto.UserAccountSearchDTO;
import com.webappdevelopment.dto.UserAccountPageDTO;
import com.webappdevelopment.service.UserAccountService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/userAccount")
@RestController
public class UserAccountController {

	private final static Logger logger = LoggerFactory.getLogger(UserAccountController.class);

	@Autowired
	UserAccountService userAccountService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<UserAccount> getAll() {

		List<UserAccount> userAccounts = userAccountService.findAll();
		
		return userAccounts;	
	}

	@GetMapping(value = "/{userAccountId}")
	@ResponseBody
	public UserAccountDTO getUserAccount(@PathVariable Integer userAccountId) {
		
		return (userAccountService.getUserAccountDTOById(userAccountId));
	}

 	@RequestMapping(value = "/addUserAccount", method = RequestMethod.POST)
	public ResponseEntity<?> addUserAccount(@RequestBody UserAccountDTO userAccountDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = userAccountService.addUserAccount(userAccountDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/userAccounts")
	public ResponseEntity<UserAccountPageDTO> getUserAccounts(UserAccountSearchDTO userAccountSearchDTO) {
 
		return userAccountService.getUserAccounts(userAccountSearchDTO);
	}	

	@RequestMapping(value = "/updateUserAccount", method = RequestMethod.POST)
	public ResponseEntity<?> updateUserAccount(@RequestBody UserAccountDTO userAccountDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = userAccountService.updateUserAccount(userAccountDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
