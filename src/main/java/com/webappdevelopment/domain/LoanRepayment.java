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
@Table(name="loan_repayments")
@Getter @Setter @NoArgsConstructor
public class LoanRepayment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="loan_repayment_id")
	private Integer loanRepaymentId;
    
  	@Column(name="paid_amount")
	private double paidAmount;
    
  	@Column(name="payment_date")
	private Date paymentDate;
    
	




}
