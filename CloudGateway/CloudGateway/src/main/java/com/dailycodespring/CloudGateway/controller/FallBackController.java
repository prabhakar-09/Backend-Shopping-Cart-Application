package com.dailycodespring.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	@GetMapping("/orderServiceFallBack")
	public String orderServiceFallBack() {
		return "Order Service is down please come back later!";
	}
	@GetMapping("/paymentServiceFallBack")
	public String paymentServiceFallBack() {
		return "Payment Service is down please come back later!";
	}
	@GetMapping("/productServiceFallBack")
	public String productServiceFallBack() {
		return "Product Service is down please come back later!";
	}
}
