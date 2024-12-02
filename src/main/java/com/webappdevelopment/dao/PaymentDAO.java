package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


