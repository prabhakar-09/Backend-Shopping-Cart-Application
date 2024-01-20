package com.dailycodespring.PaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodespring.PaymentService.entity.TransactionDetails;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long>{
// defining a specific method to get the data for specific order ID
	TransactionDetails findByOrderId(long orderId);
}
