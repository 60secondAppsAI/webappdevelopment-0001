package com.webappdevelopment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.webappdevelopment.domain.Storage;
import com.webappdevelopment.dto.StorageDTO;
import com.webappdevelopment.dto.StorageSearchDTO;
import com.webappdevelopment.dto.StoragePageDTO;
import com.webappdevelopment.dto.StorageConvertCriteriaDTO;
import com.webappdevelopment.service.GenericService;
import com.webappdevelopment.dto.common.RequestDTO;
import com.webappdevelopment.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface StorageService extends GenericService<Storage, Integer> {

	List<Storage> findAll();

	ResultDTO addStorage(StorageDTO storageDTO, RequestDTO requestDTO);

	ResultDTO updateStorage(StorageDTO storageDTO, RequestDTO requestDTO);

    Page<Storage> getAllStorages(Pageable pageable);

    Page<Storage> getAllStorages(Specification<Storage> spec, Pageable pageable);

	ResponseEntity<StoragePageDTO> getStorages(StorageSearchDTO storageSearchDTO);
	
	List<StorageDTO> convertStoragesToStorageDTOs(List<Storage> storages, StorageConvertCriteriaDTO convertCriteria);

	StorageDTO getStorageDTOById(Integer storageId);







}





