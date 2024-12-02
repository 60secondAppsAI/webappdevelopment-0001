package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.UserAccount;
import com.webappdevelopment.dto.UserAccountDTO;
import com.webappdevelopment.dto.UserAccountSearchDTO;
import com.webappdevelopment.dto.UserAccountPageDTO;
import com.webappdevelopment.dto.UserAccountConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface UserAccountService extends GenericService<UserAccount, Integer> {

	List<UserAccount> findAll();

	ResultDTO addUserAccount(UserAccountDTO userAccountDTO, RequestDTO requestDTO);

	ResultDTO updateUserAccount(UserAccountDTO userAccountDTO, RequestDTO requestDTO);

    Page<UserAccount> getAllUserAccounts(Pageable pageable);

    Page<UserAccount> getAllUserAccounts(Specification<UserAccount> spec, Pageable pageable);

	ResponseEntity<UserAccountPageDTO> getUserAccounts(UserAccountSearchDTO userAccountSearchDTO);
	
	List<UserAccountDTO> convertUserAccountsToUserAccountDTOs(List<UserAccount> userAccounts, UserAccountConvertCriteriaDTO convertCriteria);

	UserAccountDTO getUserAccountDTOById(Integer userAccountId);







}





