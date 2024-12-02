package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.UserAccount;





public interface UserAccountDAO extends GenericDAO<UserAccount, Integer> {
  
	List<UserAccount> findAll();
	






}


