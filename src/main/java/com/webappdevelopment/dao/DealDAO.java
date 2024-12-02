package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Deal;





public interface DealDAO extends GenericDAO<Deal, Integer> {
  
	List<Deal> findAll();
	






}


