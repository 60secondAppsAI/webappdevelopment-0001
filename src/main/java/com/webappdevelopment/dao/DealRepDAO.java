package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.DealRep;





public interface DealRepDAO extends GenericDAO<DealRep, Integer> {
  
	List<DealRep> findAll();
	






}


