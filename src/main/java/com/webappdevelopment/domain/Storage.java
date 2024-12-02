package com.webappdevelopment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="storages")
@Getter @Setter @NoArgsConstructor
public class Storage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="storage_id")
	private Integer storageId;
    
  	@Column(name="storage_location")
	private String storageLocation;
    
  	@Column(name="security_features")
	private String securityFeatures;
    
	




}
