package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Offer;
import com.webappdevelopment.dto.OfferDTO;
import com.webappdevelopment.dto.OfferSearchDTO;
import com.webappdevelopment.dto.OfferPageDTO;
import com.webappdevelopment.dto.OfferConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface OfferService extends GenericService<Offer, Integer> {

	List<Offer> findAll();

	ResultDTO addOffer(OfferDTO offerDTO, RequestDTO requestDTO);

	ResultDTO updateOffer(OfferDTO offerDTO, RequestDTO requestDTO);

    Page<Offer> getAllOffers(Pageable pageable);

    Page<Offer> getAllOffers(Specification<Offer> spec, Pageable pageable);

	ResponseEntity<OfferPageDTO> getOffers(OfferSearchDTO offerSearchDTO);
	
	List<OfferDTO> convertOffersToOfferDTOs(List<Offer> offers, OfferConvertCriteriaDTO convertCriteria);

	OfferDTO getOfferDTOById(Integer offerId);







}





