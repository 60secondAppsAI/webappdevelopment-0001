package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.ValuationTeam;
import com.webappdevelopment.dto.ValuationTeamDTO;
import com.webappdevelopment.dto.ValuationTeamSearchDTO;
import com.webappdevelopment.dto.ValuationTeamPageDTO;
import com.webappdevelopment.dto.ValuationTeamConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ValuationTeamService extends GenericService<ValuationTeam, Integer> {

	List<ValuationTeam> findAll();

	ResultDTO addValuationTeam(ValuationTeamDTO valuationTeamDTO, RequestDTO requestDTO);

	ResultDTO updateValuationTeam(ValuationTeamDTO valuationTeamDTO, RequestDTO requestDTO);

    Page<ValuationTeam> getAllValuationTeams(Pageable pageable);

    Page<ValuationTeam> getAllValuationTeams(Specification<ValuationTeam> spec, Pageable pageable);

	ResponseEntity<ValuationTeamPageDTO> getValuationTeams(ValuationTeamSearchDTO valuationTeamSearchDTO);
	
	List<ValuationTeamDTO> convertValuationTeamsToValuationTeamDTOs(List<ValuationTeam> valuationTeams, ValuationTeamConvertCriteriaDTO convertCriteria);

	ValuationTeamDTO getValuationTeamDTOById(Integer valuationTeamId);







}





