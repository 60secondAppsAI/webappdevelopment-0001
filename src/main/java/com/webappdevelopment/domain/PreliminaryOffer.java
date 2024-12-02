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
@Table(name="preliminary_offers")
@Getter @Setter @NoArgsConstructor
public class PreliminaryOffer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="preliminary_offer_id")
	private Integer preliminaryOfferId;
    
  	@Column(name="offer_range")
	private double offerRange;
    
  	@Column(name="comments")
	private String comments;
    
	




}
