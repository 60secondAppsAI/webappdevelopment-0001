package com.webappdevelopment.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.webappdevelopment.dao.GenericDAO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.service.impl.GenericServiceImpl;
import com.webappdevelopment.dao.WebsiteDAO;
import com.webappdevelopment.domain.Website;
import com.webappdevelopment.dto.WebsiteDTO;
import com.webappdevelopment.dto.WebsiteSearchDTO;
import com.webappdevelopment.dto.WebsitePageDTO;
import com.webappdevelopment.dto.WebsiteConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.WebsiteService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class WebsiteServiceImpl extends GenericServiceImpl<Website, Integer> implements WebsiteService {

    private final static Logger logger = LoggerFactory.getLogger(WebsiteServiceImpl.class);

	@Autowired
	WebsiteDAO websiteDao;

	


	@Override
	public GenericDAO<Website, Integer> getDAO() {
		return (GenericDAO<Website, Integer>) websiteDao;
	}
	
	public List<Website> findAll () {
		List<Website> websites = websiteDao.findAll();
		
		return websites;	
		
	}

	public ResultDTO addWebsite(WebsiteDTO websiteDTO, RequestDTO requestDTO) {

		Website website = new Website();

		website.setWebsiteId(websiteDTO.getWebsiteId());


		website.setSeoStrategy(websiteDTO.getSeoStrategy());


		website.setContentStrategy(websiteDTO.getContentStrategy());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		website = websiteDao.save(website);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Website> getAllWebsites(Pageable pageable) {
		return websiteDao.findAll(pageable);
	}

	public Page<Website> getAllWebsites(Specification<Website> spec, Pageable pageable) {
		return websiteDao.findAll(spec, pageable);
	}

	public ResponseEntity<WebsitePageDTO> getWebsites(WebsiteSearchDTO websiteSearchDTO) {
	
			Integer websiteId = websiteSearchDTO.getWebsiteId(); 
 			String seoStrategy = websiteSearchDTO.getSeoStrategy(); 
 			String contentStrategy = websiteSearchDTO.getContentStrategy(); 
 			String sortBy = websiteSearchDTO.getSortBy();
			String sortOrder = websiteSearchDTO.getSortOrder();
			String searchQuery = websiteSearchDTO.getSearchQuery();
			Integer page = websiteSearchDTO.getPage();
			Integer size = websiteSearchDTO.getSize();

	        Specification<Website> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, websiteId, "websiteId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, seoStrategy, "seoStrategy"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contentStrategy, "contentStrategy"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("seoStrategy")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contentStrategy")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Website> websites = this.getAllWebsites(spec, pageable);
		
		//System.out.println(String.valueOf(websites.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(websites.getTotalPages()));
		
		List<Website> websitesList = websites.getContent();
		
		WebsiteConvertCriteriaDTO convertCriteria = new WebsiteConvertCriteriaDTO();
		List<WebsiteDTO> websiteDTOs = this.convertWebsitesToWebsiteDTOs(websitesList,convertCriteria);
		
		WebsitePageDTO websitePageDTO = new WebsitePageDTO();
		websitePageDTO.setWebsites(websiteDTOs);
		websitePageDTO.setTotalElements(websites.getTotalElements());
		return ResponseEntity.ok(websitePageDTO);
	}

	public List<WebsiteDTO> convertWebsitesToWebsiteDTOs(List<Website> websites, WebsiteConvertCriteriaDTO convertCriteria) {
		
		List<WebsiteDTO> websiteDTOs = new ArrayList<WebsiteDTO>();
		
		for (Website website : websites) {
			websiteDTOs.add(convertWebsiteToWebsiteDTO(website,convertCriteria));
		}
		
		return websiteDTOs;

	}
	
	public WebsiteDTO convertWebsiteToWebsiteDTO(Website website, WebsiteConvertCriteriaDTO convertCriteria) {
		
		WebsiteDTO websiteDTO = new WebsiteDTO();
		
		websiteDTO.setWebsiteId(website.getWebsiteId());

	
		websiteDTO.setSeoStrategy(website.getSeoStrategy());

	
		websiteDTO.setContentStrategy(website.getContentStrategy());

	

		
		return websiteDTO;
	}

	public ResultDTO updateWebsite(WebsiteDTO websiteDTO, RequestDTO requestDTO) {
		
		Website website = websiteDao.getById(websiteDTO.getWebsiteId());

		website.setWebsiteId(ControllerUtils.setValue(website.getWebsiteId(), websiteDTO.getWebsiteId()));

		website.setSeoStrategy(ControllerUtils.setValue(website.getSeoStrategy(), websiteDTO.getSeoStrategy()));

		website.setContentStrategy(ControllerUtils.setValue(website.getContentStrategy(), websiteDTO.getContentStrategy()));



        website = websiteDao.save(website);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WebsiteDTO getWebsiteDTOById(Integer websiteId) {
	
		Website website = websiteDao.getById(websiteId);
			
		
		WebsiteConvertCriteriaDTO convertCriteria = new WebsiteConvertCriteriaDTO();
		return(this.convertWebsiteToWebsiteDTO(website,convertCriteria));
	}







}
