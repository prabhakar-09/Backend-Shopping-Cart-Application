package com.dailycodespring.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // this builder class to use the builder pattern
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	private String productName;
	private long productId;
	private long quantity;
	private long price;
	
}
