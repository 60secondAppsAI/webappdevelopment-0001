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
import com.webappdevelopment.dao.RepaymentDAO;
import com.webappdevelopment.domain.Repayment;
import com.webappdevelopment.dto.RepaymentDTO;
import com.webappdevelopment.dto.RepaymentSearchDTO;
import com.webappdevelopment.dto.RepaymentPageDTO;
import com.webappdevelopment.dto.RepaymentConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.RepaymentService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class RepaymentServiceImpl extends GenericServiceImpl<Repayment, Integer> implements RepaymentService {

    private final static Logger logger = LoggerFactory.getLogger(RepaymentServiceImpl.class);

	@Autowired
	RepaymentDAO repaymentDao;

	


	@Override
	public GenericDAO<Repayment, Integer> getDAO() {
		return (GenericDAO<Repayment, Integer>) repaymentDao;
	}
	
	public List<Repayment> findAll () {
		List<Repayment> repayments = repaymentDao.findAll();
		
		return repayments;	
		
	}

	public ResultDTO addRepayment(RepaymentDTO repaymentDTO, RequestDTO requestDTO) {

		Repayment repayment = new Repayment();

		repayment.setRepaymentId(repaymentDTO.getRepaymentId());


		repayment.setRepaymentSchedule(repaymentDTO.getRepaymentSchedule());


		repayment.setRemainingBalance(repaymentDTO.getRemainingBalance());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		repayment = repaymentDao.save(repayment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Repayment> getAllRepayments(Pageable pageable) {
		return repaymentDao.findAll(pageable);
	}

	public Page<Repayment> getAllRepayments(Specification<Repayment> spec, Pageable pageable) {
		return repaymentDao.findAll(spec, pageable);
	}

	public ResponseEntity<RepaymentPageDTO> getRepayments(RepaymentSearchDTO repaymentSearchDTO) {
	
			Integer repaymentId = repaymentSearchDTO.getRepaymentId(); 
 			String repaymentSchedule = repaymentSearchDTO.getRepaymentSchedule(); 
  			String sortBy = repaymentSearchDTO.getSortBy();
			String sortOrder = repaymentSearchDTO.getSortOrder();
			String searchQuery = repaymentSearchDTO.getSearchQuery();
			Integer page = repaymentSearchDTO.getPage();
			Integer size = repaymentSearchDTO.getSize();

	        Specification<Repayment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, repaymentId, "repaymentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, repaymentSchedule, "repaymentSchedule"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("repaymentSchedule")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Repayment> repayments = this.getAllRepayments(spec, pageable);
		
		//System.out.println(String.valueOf(repayments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(repayments.getTotalPages()));
		
		List<Repayment> repaymentsList = repayments.getContent();
		
		RepaymentConvertCriteriaDTO convertCriteria = new RepaymentConvertCriteriaDTO();
		List<RepaymentDTO> repaymentDTOs = this.convertRepaymentsToRepaymentDTOs(repaymentsList,convertCriteria);
		
		RepaymentPageDTO repaymentPageDTO = new RepaymentPageDTO();
		repaymentPageDTO.setRepayments(repaymentDTOs);
		repaymentPageDTO.setTotalElements(repayments.getTotalElements());
		return ResponseEntity.ok(repaymentPageDTO);
	}

	public List<RepaymentDTO> convertRepaymentsToRepaymentDTOs(List<Repayment> repayments, RepaymentConvertCriteriaDTO convertCriteria) {
		
		List<RepaymentDTO> repaymentDTOs = new ArrayList<RepaymentDTO>();
		
		for (Repayment repayment : repayments) {
			repaymentDTOs.add(convertRepaymentToRepaymentDTO(repayment,convertCriteria));
		}
		
		return repaymentDTOs;

	}
	
	public RepaymentDTO convertRepaymentToRepaymentDTO(Repayment repayment, RepaymentConvertCriteriaDTO convertCriteria) {
		
		RepaymentDTO repaymentDTO = new RepaymentDTO();
		
		repaymentDTO.setRepaymentId(repayment.getRepaymentId());

	
		repaymentDTO.setRepaymentSchedule(repayment.getRepaymentSchedule());

	
		repaymentDTO.setRemainingBalance(repayment.getRemainingBalance());

	

		
		return repaymentDTO;
	}

	public ResultDTO updateRepayment(RepaymentDTO repaymentDTO, RequestDTO requestDTO) {
		
		Repayment repayment = repaymentDao.getById(repaymentDTO.getRepaymentId());

		repayment.setRepaymentId(ControllerUtils.setValue(repayment.getRepaymentId(), repaymentDTO.getRepaymentId()));

		repayment.setRepaymentSchedule(ControllerUtils.setValue(repayment.getRepaymentSchedule(), repaymentDTO.getRepaymentSchedule()));

		repayment.setRemainingBalance(ControllerUtils.setValue(repayment.getRemainingBalance(), repaymentDTO.getRemainingBalance()));



        repayment = repaymentDao.save(repayment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RepaymentDTO getRepaymentDTOById(Integer repaymentId) {
	
		Repayment repayment = repaymentDao.getById(repaymentId);
			
		
		RepaymentConvertCriteriaDTO convertCriteria = new RepaymentConvertCriteriaDTO();
		return(this.convertRepaymentToRepaymentDTO(repayment,convertCriteria));
	}







}
