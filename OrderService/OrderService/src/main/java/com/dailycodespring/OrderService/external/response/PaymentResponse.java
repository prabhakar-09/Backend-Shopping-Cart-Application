package com.dailycodespring.OrderService.external.response;

import java.time.Instant;

import com.dailycodespring.OrderService.model.PaymentMode;

//import com.dailycodespring.PaymentService.model.PaymentMode;
//import com.dailycodespring.PaymentService.model.PaymentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

	private long paymentId;
	private String status;
	private PaymentMode paymentMode;
	private long amount;
	private Instant paymentDate;
	private long orderId;
	
}