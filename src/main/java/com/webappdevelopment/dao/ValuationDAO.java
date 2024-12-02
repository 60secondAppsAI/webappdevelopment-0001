package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Valuation;





public interface ValuationDAO extends GenericDAO<Valuation, Integer> {
  
	List<Valuation> findAll();
	






}


