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
import com.webappdevelopment.dao.CustomerSubmissionDAO;
import com.webappdevelopment.domain.CustomerSubmission;
import com.webappdevelopment.dto.CustomerSubmissionDTO;
import com.webappdevelopment.dto.CustomerSubmissionSearchDTO;
import com.webappdevelopment.dto.CustomerSubmissionPageDTO;
import com.webappdevelopment.dto.CustomerSubmissionConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.CustomerSubmissionService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class CustomerSubmissionServiceImpl extends GenericServiceImpl<CustomerSubmission, Integer> implements CustomerSubmissionService {

    private final static Logger logger = LoggerFactory.getLogger(CustomerSubmissionServiceImpl.class);

	@Autowired
	CustomerSubmissionDAO customerSubmissionDao;

	


	@Override
	public GenericDAO<CustomerSubmission, Integer> getDAO() {
		return (GenericDAO<CustomerSubmission, Integer>) customerSubmissionDao;
	}
	
	public List<CustomerSubmission> findAll () {
		List<CustomerSubmission> customerSubmissions = customerSubmissionDao.findAll();
		
		return customerSubmissions;	
		
	}

	public ResultDTO addCustomerSubmission(CustomerSubmissionDTO customerSubmissionDTO, RequestDTO requestDTO) {

		CustomerSubmission customerSubmission = new CustomerSubmission();

		customerSubmission.setCustomerSubmissionId(customerSubmissionDTO.getCustomerSubmissionId());


		customerSubmission.setItemDescription(customerSubmissionDTO.getItemDescription());


		customerSubmission.setPhotoUpload(customerSubmissionDTO.getPhotoUpload());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		customerSubmission = customerSubmissionDao.save(customerSubmission);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CustomerSubmission> getAllCustomerSubmissions(Pageable pageable) {
		return customerSubmissionDao.findAll(pageable);
	}

	public Page<CustomerSubmission> getAllCustomerSubmissions(Specification<CustomerSubmission> spec, Pageable pageable) {
		return customerSubmissionDao.findAll(spec, pageable);
	}

	public ResponseEntity<CustomerSubmissionPageDTO> getCustomerSubmissions(CustomerSubmissionSearchDTO customerSubmissionSearchDTO) {
	
			Integer customerSubmissionId = customerSubmissionSearchDTO.getCustomerSubmissionId(); 
 			String itemDescription = customerSubmissionSearchDTO.getItemDescription(); 
 			String photoUpload = customerSubmissionSearchDTO.getPhotoUpload(); 
 			String sortBy = customerSubmissionSearchDTO.getSortBy();
			String sortOrder = customerSubmissionSearchDTO.getSortOrder();
			String searchQuery = customerSubmissionSearchDTO.getSearchQuery();
			Integer page = customerSubmissionSearchDTO.getPage();
			Integer size = customerSubmissionSearchDTO.getSize();

	        Specification<CustomerSubmission> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, customerSubmissionId, "customerSubmissionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, itemDescription, "itemDescription"); 
			
			spec = ControllerUtils.andIfNecessary(spec, photoUpload, "photoUpload"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("itemDescription")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("photoUpload")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CustomerSubmission> customerSubmissions = this.getAllCustomerSubmissions(spec, pageable);
		
		//System.out.println(String.valueOf(customerSubmissions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(customerSubmissions.getTotalPages()));
		
		List<CustomerSubmission> customerSubmissionsList = customerSubmissions.getContent();
		
		CustomerSubmissionConvertCriteriaDTO convertCriteria = new CustomerSubmissionConvertCriteriaDTO();
		List<CustomerSubmissionDTO> customerSubmissionDTOs = this.convertCustomerSubmissionsToCustomerSubmissionDTOs(customerSubmissionsList,convertCriteria);
		
		CustomerSubmissionPageDTO customerSubmissionPageDTO = new CustomerSubmissionPageDTO();
		customerSubmissionPageDTO.setCustomerSubmissions(customerSubmissionDTOs);
		customerSubmissionPageDTO.setTotalElements(customerSubmissions.getTotalElements());
		return ResponseEntity.ok(customerSubmissionPageDTO);
	}

	public List<CustomerSubmissionDTO> convertCustomerSubmissionsToCustomerSubmissionDTOs(List<CustomerSubmission> customerSubmissions, CustomerSubmissionConvertCriteriaDTO convertCriteria) {
		
		List<CustomerSubmissionDTO> customerSubmissionDTOs = new ArrayList<CustomerSubmissionDTO>();
		
		for (CustomerSubmission customerSubmission : customerSubmissions) {
			customerSubmissionDTOs.add(convertCustomerSubmissionToCustomerSubmissionDTO(customerSubmission,convertCriteria));
		}
		
		return customerSubmissionDTOs;

	}
	
	public CustomerSubmissionDTO convertCustomerSubmissionToCustomerSubmissionDTO(CustomerSubmission customerSubmission, CustomerSubmissionConvertCriteriaDTO convertCriteria) {
		
		CustomerSubmissionDTO customerSubmissionDTO = new CustomerSubmissionDTO();
		
		customerSubmissionDTO.setCustomerSubmissionId(customerSubmission.getCustomerSubmissionId());

	
		customerSubmissionDTO.setItemDescription(customerSubmission.getItemDescription());

	
		customerSubmissionDTO.setPhotoUpload(customerSubmission.getPhotoUpload());

	

		
		return customerSubmissionDTO;
	}

	public ResultDTO updateCustomerSubmission(CustomerSubmissionDTO customerSubmissionDTO, RequestDTO requestDTO) {
		
		CustomerSubmission customerSubmission = customerSubmissionDao.getById(customerSubmissionDTO.getCustomerSubmissionId());

		customerSubmission.setCustomerSubmissionId(ControllerUtils.setValue(customerSubmission.getCustomerSubmissionId(), customerSubmissionDTO.getCustomerSubmissionId()));

		customerSubmission.setItemDescription(ControllerUtils.setValue(customerSubmission.getItemDescription(), customerSubmissionDTO.getItemDescription()));

		customerSubmission.setPhotoUpload(ControllerUtils.setValue(customerSubmission.getPhotoUpload(), customerSubmissionDTO.getPhotoUpload()));



        customerSubmission = customerSubmissionDao.save(customerSubmission);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CustomerSubmissionDTO getCustomerSubmissionDTOById(Integer customerSubmissionId) {
	
		CustomerSubmission customerSubmission = customerSubmissionDao.getById(customerSubmissionId);
			
		
		CustomerSubmissionConvertCriteriaDTO convertCriteria = new CustomerSubmissionConvertCriteriaDTO();
		return(this.convertCustomerSubmissionToCustomerSubmissionDTO(customerSubmission,convertCriteria));
	}







}
