package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Customer;





public interface CustomerDAO extends GenericDAO<Customer, Integer> {
  
	List<Customer> findAll();
	






}


