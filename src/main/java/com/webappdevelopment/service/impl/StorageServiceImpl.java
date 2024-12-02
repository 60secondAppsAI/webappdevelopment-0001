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
import com.webappdevelopment.dao.StorageDAO;
import com.webappdevelopment.domain.Storage;
import com.webappdevelopment.dto.StorageDTO;
import com.webappdevelopment.dto.StorageSearchDTO;
import com.webappdevelopment.dto.StoragePageDTO;
import com.webappdevelopment.dto.StorageConvertCriteriaDTO;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import com.webappdevelopment.service.StorageService;
import com.webappdevelopment.util.ControllerUtils;





@Service
public class StorageServiceImpl extends GenericServiceImpl<Storage, Integer> implements StorageService {

    private final static Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

	@Autowired
	StorageDAO storageDao;

	


	@Override
	public GenericDAO<Storage, Integer> getDAO() {
		return (GenericDAO<Storage, Integer>) storageDao;
	}
	
	public List<Storage> findAll () {
		List<Storage> storages = storageDao.findAll();
		
		return storages;	
		
	}

	public ResultDTO addStorage(StorageDTO storageDTO, RequestDTO requestDTO) {

		Storage storage = new Storage();

		storage.setStorageId(storageDTO.getStorageId());


		storage.setStorageLocation(storageDTO.getStorageLocation());


		storage.setSecurityFeatures(storageDTO.getSecurityFeatures());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		storage = storageDao.save(storage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Storage> getAllStorages(Pageable pageable) {
		return storageDao.findAll(pageable);
	}

	public Page<Storage> getAllStorages(Specification<Storage> spec, Pageable pageable) {
		return storageDao.findAll(spec, pageable);
	}

	public ResponseEntity<StoragePageDTO> getStorages(StorageSearchDTO storageSearchDTO) {
	
			Integer storageId = storageSearchDTO.getStorageId(); 
 			String storageLocation = storageSearchDTO.getStorageLocation(); 
 			String securityFeatures = storageSearchDTO.getSecurityFeatures(); 
 			String sortBy = storageSearchDTO.getSortBy();
			String sortOrder = storageSearchDTO.getSortOrder();
			String searchQuery = storageSearchDTO.getSearchQuery();
			Integer page = storageSearchDTO.getPage();
			Integer size = storageSearchDTO.getSize();

	        Specification<Storage> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, storageId, "storageId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, storageLocation, "storageLocation"); 
			
			spec = ControllerUtils.andIfNecessary(spec, securityFeatures, "securityFeatures"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("storageLocation")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("securityFeatures")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Storage> storages = this.getAllStorages(spec, pageable);
		
		//System.out.println(String.valueOf(storages.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(storages.getTotalPages()));
		
		List<Storage> storagesList = storages.getContent();
		
		StorageConvertCriteriaDTO convertCriteria = new StorageConvertCriteriaDTO();
		List<StorageDTO> storageDTOs = this.convertStoragesToStorageDTOs(storagesList,convertCriteria);
		
		StoragePageDTO storagePageDTO = new StoragePageDTO();
		storagePageDTO.setStorages(storageDTOs);
		storagePageDTO.setTotalElements(storages.getTotalElements());
		return ResponseEntity.ok(storagePageDTO);
	}

	public List<StorageDTO> convertStoragesToStorageDTOs(List<Storage> storages, StorageConvertCriteriaDTO convertCriteria) {
		
		List<StorageDTO> storageDTOs = new ArrayList<StorageDTO>();
		
		for (Storage storage : storages) {
			storageDTOs.add(convertStorageToStorageDTO(storage,convertCriteria));
		}
		
		return storageDTOs;

	}
	
	public StorageDTO convertStorageToStorageDTO(Storage storage, StorageConvertCriteriaDTO convertCriteria) {
		
		StorageDTO storageDTO = new StorageDTO();
		
		storageDTO.setStorageId(storage.getStorageId());

	
		storageDTO.setStorageLocation(storage.getStorageLocation());

	
		storageDTO.setSecurityFeatures(storage.getSecurityFeatures());

	

		
		return storageDTO;
	}

	public ResultDTO updateStorage(StorageDTO storageDTO, RequestDTO requestDTO) {
		
		Storage storage = storageDao.getById(storageDTO.getStorageId());

		storage.setStorageId(ControllerUtils.setValue(storage.getStorageId(), storageDTO.getStorageId()));

		storage.setStorageLocation(ControllerUtils.setValue(storage.getStorageLocation(), storageDTO.getStorageLocation()));

		storage.setSecurityFeatures(ControllerUtils.setValue(storage.getSecurityFeatures(), storageDTO.getSecurityFeatures()));



        storage = storageDao.save(storage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public StorageDTO getStorageDTOById(Integer storageId) {
	
		Storage storage = storageDao.getById(storageId);
			
		
		StorageConvertCriteriaDTO convertCriteria = new StorageConvertCriteriaDTO();
		return(this.convertStorageToStorageDTO(storage,convertCriteria));
	}







}
