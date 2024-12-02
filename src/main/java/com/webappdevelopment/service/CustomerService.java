package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Customer;
import com.webappdevelopment.dto.CustomerDTO;
import com.webappdevelopment.dto.CustomerSearchDTO;
import com.webappdevelopment.dto.CustomerPageDTO;
import com.webappdevelopment.dto.CustomerConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CustomerService extends GenericService<Customer, Integer> {

	List<Customer> findAll();

	ResultDTO addCustomer(CustomerDTO customerDTO, RequestDTO requestDTO);

	ResultDTO updateCustomer(CustomerDTO customerDTO, RequestDTO requestDTO);

    Page<Customer> getAllCustomers(Pageable pageable);

    Page<Customer> getAllCustomers(Specification<Customer> spec, Pageable pageable);

	ResponseEntity<CustomerPageDTO> getCustomers(CustomerSearchDTO customerSearchDTO);
	
	List<CustomerDTO> convertCustomersToCustomerDTOs(List<Customer> customers, CustomerConvertCriteriaDTO convertCriteria);

	CustomerDTO getCustomerDTOById(Integer customerId);







}





