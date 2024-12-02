package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.ShippingBox;





public interface ShippingBoxDAO extends GenericDAO<ShippingBox, Integer> {
  
	List<ShippingBox> findAll();
	






}


