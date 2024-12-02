package com.webappdevelopment.dao;

import java.util.List;

import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.domain.ValuationTeam;





public interface ValuationTeamDAO extends GenericDAO<ValuationTeam, Integer> {
  
	List<ValuationTeam> findAll();
	






}


