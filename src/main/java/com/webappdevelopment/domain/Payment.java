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
@Table(name="payments")
@Getter @Setter @NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="payment_id")
	private Integer paymentId;
    
  	@Column(name="payment_amount")
	private double paymentAmount;
    
  	@Column(name="payment_method")
	private String paymentMethod;
    
	




}
