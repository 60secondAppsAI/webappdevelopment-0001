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
@Table(name="agreements")
@Getter @Setter @NoArgsConstructor
public class Agreement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="agreement_id")
	private Integer agreementId;
    
  	@Column(name="document_link")
	private String documentLink;
    
  	@Column(name="signed_date")
	private Date signedDate;
    
	




}
