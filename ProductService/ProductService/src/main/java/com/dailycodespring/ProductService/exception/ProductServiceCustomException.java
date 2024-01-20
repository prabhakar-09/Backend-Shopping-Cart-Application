package com.dailycodespring.ProductService.exception;

import java.util.List;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException{

	private String errorCode;
	
	private List<String> errorCodeList;
	
	
	public ProductServiceCustomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	
	
}
