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
@Table(name="websites")
@Getter @Setter @NoArgsConstructor
public class Website {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="website_id")
	private Integer websiteId;
    
  	@Column(name="seo_strategy")
	private String seoStrategy;
    
  	@Column(name="content_strategy")
	private String contentStrategy;
    
	




}
