package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.DealDashboard;
import com.webappdevelopment.dto.DealDashboardDTO;
import com.webappdevelopment.dto.DealDashboardSearchDTO;
import com.webappdevelopment.dto.DealDashboardPageDTO;
import com.webappdevelopment.dto.DealDashboardConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DealDashboardService extends GenericService<DealDashboard, Integer> {

	List<DealDashboard> findAll();

	ResultDTO addDealDashboard(DealDashboardDTO dealDashboardDTO, RequestDTO requestDTO);

	ResultDTO updateDealDashboard(DealDashboardDTO dealDashboardDTO, RequestDTO requestDTO);

    Page<DealDashboard> getAllDealDashboards(Pageable pageable);

    Page<DealDashboard> getAllDealDashboards(Specification<DealDashboard> spec, Pageable pageable);

	ResponseEntity<DealDashboardPageDTO> getDealDashboards(DealDashboardSearchDTO dealDashboardSearchDTO);
	
	List<DealDashboardDTO> convertDealDashboardsToDealDashboardDTOs(List<DealDashboard> dealDashboards, DealDashboardConvertCriteriaDTO convertCriteria);

	DealDashboardDTO getDealDashboardDTOById(Integer dealDashboardId);







}





