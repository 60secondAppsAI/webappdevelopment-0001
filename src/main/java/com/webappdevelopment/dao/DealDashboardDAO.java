package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.DealDashboard;





public interface DealDashboardDAO extends GenericDAO<DealDashboard, Integer> {
  
	List<DealDashboard> findAll();
	






}


