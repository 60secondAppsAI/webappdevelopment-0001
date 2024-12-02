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
@Table(name="shipping_boxs")
@Getter @Setter @NoArgsConstructor
public class ShippingBox {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="shipping_box_id")
	private Integer shippingBoxId;
    
  	@Column(name="box_details")
	private String boxDetails;
    
  	@Column(name="shipping_instructions")
	private String shippingInstructions;
    
	




}
