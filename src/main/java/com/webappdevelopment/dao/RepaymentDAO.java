package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Repayment;





public interface RepaymentDAO extends GenericDAO<Repayment, Integer> {
  
	List<Repayment> findAll();
	






}


