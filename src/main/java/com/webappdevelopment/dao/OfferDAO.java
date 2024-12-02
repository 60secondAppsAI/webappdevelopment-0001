package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Offer;





public interface OfferDAO extends GenericDAO<Offer, Integer> {
  
	List<Offer> findAll();
	






}


