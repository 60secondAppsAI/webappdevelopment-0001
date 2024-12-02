package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


