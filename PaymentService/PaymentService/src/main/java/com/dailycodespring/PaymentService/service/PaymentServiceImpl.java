package com.dailycodespring.PaymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodespring.PaymentService.entity.TransactionDetails;
import com.dailycodespring.PaymentService.model.PaymentMode;
import com.dailycodespring.PaymentService.model.PaymentRequest;
import com.dailycodespring.PaymentService.model.PaymentResponse;
import com.dailycodespring.PaymentService.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;
	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("Recording Payment Details {}", paymentRequest);
		TransactionDetails transactionDetails 
			= TransactionDetails.builder()
			.paymentDate(Instant.now())
			.paymentMode(paymentRequest.getPaymentMode().name())
			.paymentStatus("SUCCESS")
			.orderId(paymentRequest.getOrderId())
			.referenceNumber(paymentRequest.getReferenceNumber())
			.amount(paymentRequest.getAmount())
			.build();
		transactionDetailsRepository.save(transactionDetails);
		log.info("Transaction Completed with Id: {}", transactionDetails.getId());
		return transactionDetails.getId();
	}
	
	@Override
	public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
		log.info("Getting Payment Details for order ID {}", orderId);
// Here the JPA method will not provide the find by order ID it's a special method we need to write in 
// transaction details repository!!
		TransactionDetails transactionDetails
			= transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));
//		Now these transactional details has to be changed to payment response object
		PaymentResponse paymentResponse 
			= PaymentResponse.builder()
			.paymentId(transactionDetails.getId())
			.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
			.paymentDate(transactionDetails.getPaymentDate())
			.orderId(transactionDetails.getOrderId())
			.status(transactionDetails.getPaymentStatus())
			.amount(transactionDetails.getAmount())
			.build();
		
		return paymentResponse;
	}

}
