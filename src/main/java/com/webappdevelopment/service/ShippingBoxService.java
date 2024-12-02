package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.ShippingBox;
import com.webappdevelopment.dto.ShippingBoxDTO;
import com.webappdevelopment.dto.ShippingBoxSearchDTO;
import com.webappdevelopment.dto.ShippingBoxPageDTO;
import com.webappdevelopment.dto.ShippingBoxConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShippingBoxService extends GenericService<ShippingBox, Integer> {

	List<ShippingBox> findAll();

	ResultDTO addShippingBox(ShippingBoxDTO shippingBoxDTO, RequestDTO requestDTO);

	ResultDTO updateShippingBox(ShippingBoxDTO shippingBoxDTO, RequestDTO requestDTO);

    Page<ShippingBox> getAllShippingBoxs(Pageable pageable);

    Page<ShippingBox> getAllShippingBoxs(Specification<ShippingBox> spec, Pageable pageable);

	ResponseEntity<ShippingBoxPageDTO> getShippingBoxs(ShippingBoxSearchDTO shippingBoxSearchDTO);
	
	List<ShippingBoxDTO> convertShippingBoxsToShippingBoxDTOs(List<ShippingBox> shippingBoxs, ShippingBoxConvertCriteriaDTO convertCriteria);

	ShippingBoxDTO getShippingBoxDTOById(Integer shippingBoxId);







}





