package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.PreliminaryOffer;
import com.webappdevelopment.dto.PreliminaryOfferDTO;
import com.webappdevelopment.dto.PreliminaryOfferSearchDTO;
import com.webappdevelopment.dto.PreliminaryOfferPageDTO;
import com.webappdevelopment.dto.PreliminaryOfferConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PreliminaryOfferService extends GenericService<PreliminaryOffer, Integer> {

	List<PreliminaryOffer> findAll();

	ResultDTO addPreliminaryOffer(PreliminaryOfferDTO preliminaryOfferDTO, RequestDTO requestDTO);

	ResultDTO updatePreliminaryOffer(PreliminaryOfferDTO preliminaryOfferDTO, RequestDTO requestDTO);

    Page<PreliminaryOffer> getAllPreliminaryOffers(Pageable pageable);

    Page<PreliminaryOffer> getAllPreliminaryOffers(Specification<PreliminaryOffer> spec, Pageable pageable);

	ResponseEntity<PreliminaryOfferPageDTO> getPreliminaryOffers(PreliminaryOfferSearchDTO preliminaryOfferSearchDTO);
	
	List<PreliminaryOfferDTO> convertPreliminaryOffersToPreliminaryOfferDTOs(List<PreliminaryOffer> preliminaryOffers, PreliminaryOfferConvertCriteriaDTO convertCriteria);

	PreliminaryOfferDTO getPreliminaryOfferDTOById(Integer preliminaryOfferId);







}





