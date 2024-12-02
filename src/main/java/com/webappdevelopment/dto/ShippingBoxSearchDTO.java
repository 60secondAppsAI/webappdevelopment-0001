package com.webappdevelopment.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShippingBoxSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer shippingBoxId;
	
	private String boxDetails;
	
	private String shippingInstructions;
	
}
