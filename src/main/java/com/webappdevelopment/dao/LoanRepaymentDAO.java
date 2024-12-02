package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.LoanRepayment;





public interface LoanRepaymentDAO extends GenericDAO<LoanRepayment, Integer> {
  
	List<LoanRepayment> findAll();
	






}


