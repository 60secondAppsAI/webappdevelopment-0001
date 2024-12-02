package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Website;
import com.webappdevelopment.dto.WebsiteDTO;
import com.webappdevelopment.dto.WebsiteSearchDTO;
import com.webappdevelopment.dto.WebsitePageDTO;
import com.webappdevelopment.dto.WebsiteConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WebsiteService extends GenericService<Website, Integer> {

	List<Website> findAll();

	ResultDTO addWebsite(WebsiteDTO websiteDTO, RequestDTO requestDTO);

	ResultDTO updateWebsite(WebsiteDTO websiteDTO, RequestDTO requestDTO);

    Page<Website> getAllWebsites(Pageable pageable);

    Page<Website> getAllWebsites(Specification<Website> spec, Pageable pageable);

	ResponseEntity<WebsitePageDTO> getWebsites(WebsiteSearchDTO websiteSearchDTO);
	
	List<WebsiteDTO> convertWebsitesToWebsiteDTOs(List<Website> websites, WebsiteConvertCriteriaDTO convertCriteria);

	WebsiteDTO getWebsiteDTOById(Integer websiteId);







}





