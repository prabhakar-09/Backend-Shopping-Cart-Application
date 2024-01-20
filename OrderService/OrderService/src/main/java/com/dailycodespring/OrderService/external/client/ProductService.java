package com.dailycodespring.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.dailycodespring.OrderService.exception.CustomException;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

//@CircuitBreaker(name="external", fallbackMethod="fallback")
@FeignClient(name = "product-service/product")
public interface ProductService { // This interface implementation is there in product microservice.
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(
			@PathVariable("id") long productId,
			@RequestParam long quantity
			);

//	default void fallback(Exception e) {
//		throw new CustomException("Product Service is not available", "UNAVAILABLE", 500);
//	}
}
