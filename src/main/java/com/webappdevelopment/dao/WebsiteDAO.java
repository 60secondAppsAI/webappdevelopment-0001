package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Website;





public interface WebsiteDAO extends GenericDAO<Website, Integer> {
  
	List<Website> findAll();
	






}


