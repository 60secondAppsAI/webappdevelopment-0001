package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Agreement;





public interface AgreementDAO extends GenericDAO<Agreement, Integer> {
  
	List<Agreement> findAll();
	






}


