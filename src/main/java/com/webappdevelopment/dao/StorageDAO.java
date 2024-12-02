package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Storage;





public interface StorageDAO extends GenericDAO<Storage, Integer> {
  
	List<Storage> findAll();
	






}


