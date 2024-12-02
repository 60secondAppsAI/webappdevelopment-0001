package com.webappdevelopment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDAO<T, ID> extends JpaRepository<T, ID> {

	    Page<T> findAll(Pageable pageable);

	    Page<T> findAll(Specification<T> spec, Pageable pageable);

}