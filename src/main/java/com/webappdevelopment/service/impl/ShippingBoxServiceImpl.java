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
import com.webappdevelopment.dao.ShippingBoxDAO;
import com.webappdevelopment.domain.ShippingBox;
import com.webappdevelopment.dto.ShippingBoxDTO;
import com.webappdevelopment.dto.ShippingBoxSearchDTO;
import com.webappdevelopment.dto.ShippingBoxPageDTO;
import com.webappdevelopment.dto.ShippingBoxConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.ShippingBoxService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class ShippingBoxServiceImpl extends GenericServiceImpl<ShippingBox, Integer> implements ShippingBoxService {

    private final static Logger logger = LoggerFactory.getLogger(ShippingBoxServiceImpl.class);

	@Autowired
	ShippingBoxDAO shippingBoxDao;

	


	@Override
	public GenericDAO<ShippingBox, Integer> getDAO() {
		return (GenericDAO<ShippingBox, Integer>) shippingBoxDao;
	}
	
	public List<ShippingBox> findAll () {
		List<ShippingBox> shippingBoxs = shippingBoxDao.findAll();
		
		return shippingBoxs;	
		
	}

	public ResultDTO addShippingBox(ShippingBoxDTO shippingBoxDTO, RequestDTO requestDTO) {

		ShippingBox shippingBox = new ShippingBox();

		shippingBox.setShippingBoxId(shippingBoxDTO.getShippingBoxId());


		shippingBox.setBoxDetails(shippingBoxDTO.getBoxDetails());


		shippingBox.setShippingInstructions(shippingBoxDTO.getShippingInstructions());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		shippingBox = shippingBoxDao.save(shippingBox);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ShippingBox> getAllShippingBoxs(Pageable pageable) {
		return shippingBoxDao.findAll(pageable);
	}

	public Page<ShippingBox> getAllShippingBoxs(Specification<ShippingBox> spec, Pageable pageable) {
		return shippingBoxDao.findAll(spec, pageable);
	}

	public ResponseEntity<ShippingBoxPageDTO> getShippingBoxs(ShippingBoxSearchDTO shippingBoxSearchDTO) {
	
			Integer shippingBoxId = shippingBoxSearchDTO.getShippingBoxId(); 
 			String boxDetails = shippingBoxSearchDTO.getBoxDetails(); 
 			String shippingInstructions = shippingBoxSearchDTO.getShippingInstructions(); 
 			String sortBy = shippingBoxSearchDTO.getSortBy();
			String sortOrder = shippingBoxSearchDTO.getSortOrder();
			String searchQuery = shippingBoxSearchDTO.getSearchQuery();
			Integer page = shippingBoxSearchDTO.getPage();
			Integer size = shippingBoxSearchDTO.getSize();

	        Specification<ShippingBox> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, shippingBoxId, "shippingBoxId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, boxDetails, "boxDetails"); 
			
			spec = ControllerUtils.andIfNecessary(spec, shippingInstructions, "shippingInstructions"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("boxDetails")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("shippingInstructions")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ShippingBox> shippingBoxs = this.getAllShippingBoxs(spec, pageable);
		
		//System.out.println(String.valueOf(shippingBoxs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(shippingBoxs.getTotalPages()));
		
		List<ShippingBox> shippingBoxsList = shippingBoxs.getContent();
		
		ShippingBoxConvertCriteriaDTO convertCriteria = new ShippingBoxConvertCriteriaDTO();
		List<ShippingBoxDTO> shippingBoxDTOs = this.convertShippingBoxsToShippingBoxDTOs(shippingBoxsList,convertCriteria);
		
		ShippingBoxPageDTO shippingBoxPageDTO = new ShippingBoxPageDTO();
		shippingBoxPageDTO.setShippingBoxs(shippingBoxDTOs);
		shippingBoxPageDTO.setTotalElements(shippingBoxs.getTotalElements());
		return ResponseEntity.ok(shippingBoxPageDTO);
	}

	public List<ShippingBoxDTO> convertShippingBoxsToShippingBoxDTOs(List<ShippingBox> shippingBoxs, ShippingBoxConvertCriteriaDTO convertCriteria) {
		
		List<ShippingBoxDTO> shippingBoxDTOs = new ArrayList<ShippingBoxDTO>();
		
		for (ShippingBox shippingBox : shippingBoxs) {
			shippingBoxDTOs.add(convertShippingBoxToShippingBoxDTO(shippingBox,convertCriteria));
		}
		
		return shippingBoxDTOs;

	}
	
	public ShippingBoxDTO convertShippingBoxToShippingBoxDTO(ShippingBox shippingBox, ShippingBoxConvertCriteriaDTO convertCriteria) {
		
		ShippingBoxDTO shippingBoxDTO = new ShippingBoxDTO();
		
		shippingBoxDTO.setShippingBoxId(shippingBox.getShippingBoxId());

	
		shippingBoxDTO.setBoxDetails(shippingBox.getBoxDetails());

	
		shippingBoxDTO.setShippingInstructions(shippingBox.getShippingInstructions());

	

		
		return shippingBoxDTO;
	}

	public ResultDTO updateShippingBox(ShippingBoxDTO shippingBoxDTO, RequestDTO requestDTO) {
		
		ShippingBox shippingBox = shippingBoxDao.getById(shippingBoxDTO.getShippingBoxId());

		shippingBox.setShippingBoxId(ControllerUtils.setValue(shippingBox.getShippingBoxId(), shippingBoxDTO.getShippingBoxId()));

		shippingBox.setBoxDetails(ControllerUtils.setValue(shippingBox.getBoxDetails(), shippingBoxDTO.getBoxDetails()));

		shippingBox.setShippingInstructions(ControllerUtils.setValue(shippingBox.getShippingInstructions(), shippingBoxDTO.getShippingInstructions()));



        shippingBox = shippingBoxDao.save(shippingBox);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ShippingBoxDTO getShippingBoxDTOById(Integer shippingBoxId) {
	
		ShippingBox shippingBox = shippingBoxDao.getById(shippingBoxId);
			
		
		ShippingBoxConvertCriteriaDTO convertCriteria = new ShippingBoxConvertCriteriaDTO();
		return(this.convertShippingBoxToShippingBoxDTO(shippingBox,convertCriteria));
	}







}
