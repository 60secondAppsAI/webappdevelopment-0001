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
import com.webappdevelopment.dao.ValuationTeamDAO;
import com.webappdevelopment.domain.ValuationTeam;
import com.webappdevelopment.dto.ValuationTeamDTO;
import com.webappdevelopment.dto.ValuationTeamSearchDTO;
import com.webappdevelopment.dto.ValuationTeamPageDTO;
import com.webappdevelopment.dto.ValuationTeamConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.ValuationTeamService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class ValuationTeamServiceImpl extends GenericServiceImpl<ValuationTeam, Integer> implements ValuationTeamService {

    private final static Logger logger = LoggerFactory.getLogger(ValuationTeamServiceImpl.class);

	@Autowired
	ValuationTeamDAO valuationTeamDao;

	


	@Override
	public GenericDAO<ValuationTeam, Integer> getDAO() {
		return (GenericDAO<ValuationTeam, Integer>) valuationTeamDao;
	}
	
	public List<ValuationTeam> findAll () {
		List<ValuationTeam> valuationTeams = valuationTeamDao.findAll();
		
		return valuationTeams;	
		
	}

	public ResultDTO addValuationTeam(ValuationTeamDTO valuationTeamDTO, RequestDTO requestDTO) {

		ValuationTeam valuationTeam = new ValuationTeam();

		valuationTeam.setValuationTeamId(valuationTeamDTO.getValuationTeamId());


		valuationTeam.setTeamName(valuationTeamDTO.getTeamName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		valuationTeam = valuationTeamDao.save(valuationTeam);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ValuationTeam> getAllValuationTeams(Pageable pageable) {
		return valuationTeamDao.findAll(pageable);
	}

	public Page<ValuationTeam> getAllValuationTeams(Specification<ValuationTeam> spec, Pageable pageable) {
		return valuationTeamDao.findAll(spec, pageable);
	}

	public ResponseEntity<ValuationTeamPageDTO> getValuationTeams(ValuationTeamSearchDTO valuationTeamSearchDTO) {
	
			Integer valuationTeamId = valuationTeamSearchDTO.getValuationTeamId(); 
 			String teamName = valuationTeamSearchDTO.getTeamName(); 
 			String sortBy = valuationTeamSearchDTO.getSortBy();
			String sortOrder = valuationTeamSearchDTO.getSortOrder();
			String searchQuery = valuationTeamSearchDTO.getSearchQuery();
			Integer page = valuationTeamSearchDTO.getPage();
			Integer size = valuationTeamSearchDTO.getSize();

	        Specification<ValuationTeam> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, valuationTeamId, "valuationTeamId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, teamName, "teamName"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("teamName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ValuationTeam> valuationTeams = this.getAllValuationTeams(spec, pageable);
		
		//System.out.println(String.valueOf(valuationTeams.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(valuationTeams.getTotalPages()));
		
		List<ValuationTeam> valuationTeamsList = valuationTeams.getContent();
		
		ValuationTeamConvertCriteriaDTO convertCriteria = new ValuationTeamConvertCriteriaDTO();
		List<ValuationTeamDTO> valuationTeamDTOs = this.convertValuationTeamsToValuationTeamDTOs(valuationTeamsList,convertCriteria);
		
		ValuationTeamPageDTO valuationTeamPageDTO = new ValuationTeamPageDTO();
		valuationTeamPageDTO.setValuationTeams(valuationTeamDTOs);
		valuationTeamPageDTO.setTotalElements(valuationTeams.getTotalElements());
		return ResponseEntity.ok(valuationTeamPageDTO);
	}

	public List<ValuationTeamDTO> convertValuationTeamsToValuationTeamDTOs(List<ValuationTeam> valuationTeams, ValuationTeamConvertCriteriaDTO convertCriteria) {
		
		List<ValuationTeamDTO> valuationTeamDTOs = new ArrayList<ValuationTeamDTO>();
		
		for (ValuationTeam valuationTeam : valuationTeams) {
			valuationTeamDTOs.add(convertValuationTeamToValuationTeamDTO(valuationTeam,convertCriteria));
		}
		
		return valuationTeamDTOs;

	}
	
	public ValuationTeamDTO convertValuationTeamToValuationTeamDTO(ValuationTeam valuationTeam, ValuationTeamConvertCriteriaDTO convertCriteria) {
		
		ValuationTeamDTO valuationTeamDTO = new ValuationTeamDTO();
		
		valuationTeamDTO.setValuationTeamId(valuationTeam.getValuationTeamId());

	
		valuationTeamDTO.setTeamName(valuationTeam.getTeamName());

	

		
		return valuationTeamDTO;
	}

	public ResultDTO updateValuationTeam(ValuationTeamDTO valuationTeamDTO, RequestDTO requestDTO) {
		
		ValuationTeam valuationTeam = valuationTeamDao.getById(valuationTeamDTO.getValuationTeamId());

		valuationTeam.setValuationTeamId(ControllerUtils.setValue(valuationTeam.getValuationTeamId(), valuationTeamDTO.getValuationTeamId()));

		valuationTeam.setTeamName(ControllerUtils.setValue(valuationTeam.getTeamName(), valuationTeamDTO.getTeamName()));



        valuationTeam = valuationTeamDao.save(valuationTeam);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ValuationTeamDTO getValuationTeamDTOById(Integer valuationTeamId) {
	
		ValuationTeam valuationTeam = valuationTeamDao.getById(valuationTeamId);
			
		
		ValuationTeamConvertCriteriaDTO convertCriteria = new ValuationTeamConvertCriteriaDTO();
		return(this.convertValuationTeamToValuationTeamDTO(valuationTeam,convertCriteria));
	}







}
