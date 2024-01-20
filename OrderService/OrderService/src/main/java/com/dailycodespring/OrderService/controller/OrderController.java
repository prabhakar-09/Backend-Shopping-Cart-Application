package com.dailycodespring.OrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodespring.OrderService.model.OrderRequest;
import com.dailycodespring.OrderService.model.OrderResponse;
import com.dailycodespring.OrderService.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

	@Autowired
	private OrderService orderService;
	
//	@PreAuthorize("hasAuthority('Customer')")
	@PostMapping("/placeOrder")
//	return type long cos we send the generated order Id back 
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
		long orderId = orderService.placeOrder(orderRequest);
		log.info("Order Id {}: ", orderId);
		
		return new ResponseEntity<>(orderId, HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer')")
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
		OrderResponse orderResponse
			=  orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(orderResponse,HttpStatus.OK);
	}
}
