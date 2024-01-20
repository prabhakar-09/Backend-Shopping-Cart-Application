package com.dailycodespring.ProductService.service;

import com.dailycodespring.ProductService.model.ProductRequest;
import com.dailycodespring.ProductService.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

}
