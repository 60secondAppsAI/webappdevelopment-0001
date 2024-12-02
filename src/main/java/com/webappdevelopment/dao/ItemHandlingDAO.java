package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.ItemHandling;





public interface ItemHandlingDAO extends GenericDAO<ItemHandling, Integer> {
  
	List<ItemHandling> findAll();
	






}


