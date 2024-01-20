package com.dailycodespring.PaymentService.service;

import com.dailycodespring.PaymentService.model.PaymentRequest;
import com.dailycodespring.PaymentService.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(String orderId);

}
