package com.webappdevelopment.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class LoanRepaymentDTO {

	private Integer loanRepaymentId;

	private double paidAmount;

	private Date paymentDate;






}
