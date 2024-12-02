package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.CustomerSubmission;





public interface CustomerSubmissionDAO extends GenericDAO<CustomerSubmission, Integer> {
  
	List<CustomerSubmission> findAll();
	






}


