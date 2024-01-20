package com.dailycodespring.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dailycodespring.OrderService.exception.CustomException;
import com.dailycodespring.OrderService.external.request.PaymentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

//import com.dailycodespring.PaymentService.model.PaymentRequest;
//@CircuitBreaker(name="external", fallbackMethod="fallback")
@FeignClient(name = "payment-service/payment")
public interface PaymentService {

	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);
	
//	default void fallback(Exception e) {
//		throw new CustomException("Payment Service is not available", "UNAVAILABLE", 500);
//	}
}
