package com.dailycodespring.OrderService.external.request;

import com.dailycodespring.OrderService.model.PaymentMode;

//import com.dailycodespring.PaymentService.model.PaymentMode;
//import com.dailycodespring.PaymentService.model.PaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	private long  orderId;
	private long amount;
	private String referenceNumber;
	private PaymentMode paymentMode;

	
}
