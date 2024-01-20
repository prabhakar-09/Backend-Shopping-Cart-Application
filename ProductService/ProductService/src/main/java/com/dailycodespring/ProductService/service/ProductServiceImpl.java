package com.dailycodespring.ProductService.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodespring.ProductService.entity.Product;
import com.dailycodespring.ProductService.exception.ProductServiceCustomException;
import com.dailycodespring.ProductService.model.ProductRequest;
import com.dailycodespring.ProductService.model.ProductResponse;
import com.dailycodespring.ProductService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding product");
		Product product = 
				Product.builder()
				.productName(productRequest.getName())
				.quantity(productRequest.getQuantity())
				.price(productRequest.getPrice())
				.build();
		
		productRepository.save(product);
		log.info("Product Created");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		log.info("Get the product for product ID: {}", productId);
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("Product with the given ID not found!","PRODUCT_NOT_FOUND"));
		
//		Converting the 'product' response object into ProductResponse using builder patterns 
		ProductResponse productResponse = new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {

		log.info("Reduced quantity {} for ID : {}", quantity, productId);
		
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new ProductServiceCustomException("Product with given ID not found ", "PRODUCT_NOT_FOUND"));
		
		if(product.getQuantity()< quantity) {
			throw new ProductServiceCustomException(
					"Product Doesnt have sufficient quantity ", 
					"INSUFFICIENT_QUANTITY"
					);
		}
		
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product);
		
		log.info("Product quantity updated successfully!!");
		
		
	}

	
	
}
