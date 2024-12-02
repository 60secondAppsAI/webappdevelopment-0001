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
import com.webappdevelopment.dao.LoanRepaymentDAO;
import com.webappdevelopment.domain.LoanRepayment;
import com.webappdevelopment.dto.LoanRepaymentDTO;
import com.webappdevelopment.dto.LoanRepaymentSearchDTO;
import com.webappdevelopment.dto.LoanRepaymentPageDTO;
import com.webappdevelopment.dto.LoanRepaymentConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.LoanRepaymentService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class LoanRepaymentServiceImpl extends GenericServiceImpl<LoanRepayment, Integer> implements LoanRepaymentService {

    private final static Logger logger = LoggerFactory.getLogger(LoanRepaymentServiceImpl.class);

	@Autowired
	LoanRepaymentDAO loanRepaymentDao;

	


	@Override
	public GenericDAO<LoanRepayment, Integer> getDAO() {
		return (GenericDAO<LoanRepayment, Integer>) loanRepaymentDao;
	}
	
	public List<LoanRepayment> findAll () {
		List<LoanRepayment> loanRepayments = loanRepaymentDao.findAll();
		
		return loanRepayments;	
		
	}

	public ResultDTO addLoanRepayment(LoanRepaymentDTO loanRepaymentDTO, RequestDTO requestDTO) {

		LoanRepayment loanRepayment = new LoanRepayment();

		loanRepayment.setLoanRepaymentId(loanRepaymentDTO.getLoanRepaymentId());


		loanRepayment.setPaidAmount(loanRepaymentDTO.getPaidAmount());


		loanRepayment.setPaymentDate(loanRepaymentDTO.getPaymentDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		loanRepayment = loanRepaymentDao.save(loanRepayment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<LoanRepayment> getAllLoanRepayments(Pageable pageable) {
		return loanRepaymentDao.findAll(pageable);
	}

	public Page<LoanRepayment> getAllLoanRepayments(Specification<LoanRepayment> spec, Pageable pageable) {
		return loanRepaymentDao.findAll(spec, pageable);
	}

	public ResponseEntity<LoanRepaymentPageDTO> getLoanRepayments(LoanRepaymentSearchDTO loanRepaymentSearchDTO) {
	
			Integer loanRepaymentId = loanRepaymentSearchDTO.getLoanRepaymentId(); 
    			String sortBy = loanRepaymentSearchDTO.getSortBy();
			String sortOrder = loanRepaymentSearchDTO.getSortOrder();
			String searchQuery = loanRepaymentSearchDTO.getSearchQuery();
			Integer page = loanRepaymentSearchDTO.getPage();
			Integer size = loanRepaymentSearchDTO.getSize();

	        Specification<LoanRepayment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, loanRepaymentId, "loanRepaymentId"); 
			
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<LoanRepayment> loanRepayments = this.getAllLoanRepayments(spec, pageable);
		
		//System.out.println(String.valueOf(loanRepayments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(loanRepayments.getTotalPages()));
		
		List<LoanRepayment> loanRepaymentsList = loanRepayments.getContent();
		
		LoanRepaymentConvertCriteriaDTO convertCriteria = new LoanRepaymentConvertCriteriaDTO();
		List<LoanRepaymentDTO> loanRepaymentDTOs = this.convertLoanRepaymentsToLoanRepaymentDTOs(loanRepaymentsList,convertCriteria);
		
		LoanRepaymentPageDTO loanRepaymentPageDTO = new LoanRepaymentPageDTO();
		loanRepaymentPageDTO.setLoanRepayments(loanRepaymentDTOs);
		loanRepaymentPageDTO.setTotalElements(loanRepayments.getTotalElements());
		return ResponseEntity.ok(loanRepaymentPageDTO);
	}

	public List<LoanRepaymentDTO> convertLoanRepaymentsToLoanRepaymentDTOs(List<LoanRepayment> loanRepayments, LoanRepaymentConvertCriteriaDTO convertCriteria) {
		
		List<LoanRepaymentDTO> loanRepaymentDTOs = new ArrayList<LoanRepaymentDTO>();
		
		for (LoanRepayment loanRepayment : loanRepayments) {
			loanRepaymentDTOs.add(convertLoanRepaymentToLoanRepaymentDTO(loanRepayment,convertCriteria));
		}
		
		return loanRepaymentDTOs;

	}
	
	public LoanRepaymentDTO convertLoanRepaymentToLoanRepaymentDTO(LoanRepayment loanRepayment, LoanRepaymentConvertCriteriaDTO convertCriteria) {
		
		LoanRepaymentDTO loanRepaymentDTO = new LoanRepaymentDTO();
		
		loanRepaymentDTO.setLoanRepaymentId(loanRepayment.getLoanRepaymentId());

	
		loanRepaymentDTO.setPaidAmount(loanRepayment.getPaidAmount());

	
		loanRepaymentDTO.setPaymentDate(loanRepayment.getPaymentDate());

	

		
		return loanRepaymentDTO;
	}

	public ResultDTO updateLoanRepayment(LoanRepaymentDTO loanRepaymentDTO, RequestDTO requestDTO) {
		
		LoanRepayment loanRepayment = loanRepaymentDao.getById(loanRepaymentDTO.getLoanRepaymentId());

		loanRepayment.setLoanRepaymentId(ControllerUtils.setValue(loanRepayment.getLoanRepaymentId(), loanRepaymentDTO.getLoanRepaymentId()));

		loanRepayment.setPaidAmount(ControllerUtils.setValue(loanRepayment.getPaidAmount(), loanRepaymentDTO.getPaidAmount()));

		loanRepayment.setPaymentDate(ControllerUtils.setValue(loanRepayment.getPaymentDate(), loanRepaymentDTO.getPaymentDate()));



        loanRepayment = loanRepaymentDao.save(loanRepayment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LoanRepaymentDTO getLoanRepaymentDTOById(Integer loanRepaymentId) {
	
		LoanRepayment loanRepayment = loanRepaymentDao.getById(loanRepaymentId);
			
		
		LoanRepaymentConvertCriteriaDTO convertCriteria = new LoanRepaymentConvertCriteriaDTO();
		return(this.convertLoanRepaymentToLoanRepaymentDTO(loanRepayment,convertCriteria));
	}







}
