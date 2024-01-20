package com.dailycodespring.ProductService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // this is a lombok annotation which will take care of all the constructors and other getters and setters itself
@AllArgsConstructor // will generate constructors with all the arguments 
@NoArgsConstructor // will generate no args constructors as well
@Builder // This will give the builder pattern implementation for this particular class
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  // will generate automatically sequentially
	private long productId;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRICE")
	private long price;
	
	@Column(name = "QUANTITY")
	private long quantity;
	
}
