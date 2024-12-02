package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.PreliminaryOffer;





public interface PreliminaryOfferDAO extends GenericDAO<PreliminaryOffer, Integer> {
  
	List<PreliminaryOffer> findAll();
	






}


