package com.dailycodespring.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dailycodespring.OrderService.entity.Order;
import com.dailycodespring.OrderService.exception.CustomException;
import com.dailycodespring.OrderService.external.client.PaymentService;
import com.dailycodespring.OrderService.external.client.ProductService;
import com.dailycodespring.OrderService.external.request.PaymentRequest;
import com.dailycodespring.OrderService.external.response.PaymentResponse;
import com.dailycodespring.OrderService.model.OrderRequest;
import com.dailycodespring.OrderService.model.OrderResponse;
//import com.dailycodespring.OrderService.model.OrderResponse.ProductDetails;
import com.dailycodespring.OrderService.model.ProductResponse;
import com.dailycodespring.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public long placeOrder(OrderRequest orderRequest) {
//		Creating order entity & save it with status order created..
//		product service to reduce our quantity 
//		once all done call payment service do payments & then mark it as complete or cancelled 
//		Payment Service -> Payments -> Success -> COMPLETE
		log.info("Placing order request: {} ", orderRequest);
		
		productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
		
		log.info("Creating order with Status CREATED!");
		
		Order order = Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus("CREATED")
				.productId(orderRequest.getProductId())
				.orderDate(Instant.now())
				.quantity(orderRequest.getQuantity())
				.build();
		
		order = orderRepository.save(order);
		
		log.info("Calling Payment Service to complete the payment!!");
		PaymentRequest paymentRequest 
				= PaymentRequest.builder()
				.orderId(order.getId())
				.paymentMode(orderRequest.getPaymentMode())
				.amount(orderRequest.getTotalAmount())
				.build();
		
		String orderStatus = null;
		
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Payment Done successfully. Changing order status to PLACED ");
			orderStatus = "PLACED";
		}catch (Exception e) {
			log.error("Error occured in payment. Changing order status to PAYMENT_FAILED");
			orderStatus = "PAYMENT_FAILED";
		}
		
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		
		log.info("Order placed successfully with order Id ", order.getId());
		
		
		return order.getId();
	}

//	@Override
//	public OrderResponse getOrderDetails(long orderId) {
//		log.info("Getting order details for Order Id : {}",orderId);
//		Order order 
//			= orderRepository.findById(orderId)
//			.orElseThrow(()->new CustomException("Order not found for the order ID: "+orderId,
//					"NOT_FOUND", 404));
//				
//		log.info("Order status: ",order.getOrderStatus());
//		
//		if(order.getOrderStatus() == "PAYMENT_FAILED") {
//			throw new CustomException("Cannot fetch since payment was failed for this order " +orderId,
//					"NOT FOUND", 404);
//		}
//
//		log.info("Invoking Product Service to fetch the product for ID {}", order.getProductId());
//		ProductResponse productResponse 
//			= restTemplate.getForObject(
//					"http://PRODUCT-SERVICE/product/"+order.getProductId(), 
//					ProductResponse.class); 
//		
//		log.info("Getting Payment Information from payment service!!");
//		
//		PaymentResponse paymentResponse 
//			= restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+order.getId(),
//					PaymentResponse.class); 
//		
//		OrderResponse.ProductDetails productDetails
//			= OrderResponse.ProductDetails
//			.builder()
//			.productName(productResponse.getProductName())
//			.productId(productResponse.getProductId())
//			.quantity(productResponse.getQuantity())
//			.price(productResponse.getPrice())
//			.build();
//		
//		OrderResponse.PaymentDetails paymentDetails
//			= OrderResponse.PaymentDetails
//			.builder()
//			.paymentId(paymentResponse.getPaymentId())
//			.status(paymentResponse.getStatus())
//			.paymentDate(paymentResponse.getPaymentDate())
//			.paymentMode(paymentResponse.getPaymentMode())
//			.amount(paymentResponse.getAmount())
//			.build();
//		
//		OrderResponse orderResponse 
//			= OrderResponse.builder()
//			.orderId(order.getId())
//			.orderStatus(order.getOrderStatus())
//			.amount(order.getAmount())
//			.orderDate(order.getOrderDate())
//			.productDetails(productDetails)
//			.paymentDetails(paymentDetails)
//			.build();
//		return orderResponse;
//	}
	
	@Override
	public OrderResponse getOrderDetails(long orderId) {
	    log.info("Getting order details for Order Id: {}", orderId);

	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new CustomException("Order not found for the order ID: " + orderId,
	                    "NOT_FOUND", 404));

	    log.info("Order status: {}", order.getOrderStatus());

	    if ("PAYMENT_FAILED".equals(order.getOrderStatus())) {
	        throw new CustomException("Cannot fetch since payment was failed for this order " + orderId,
	                "PAYMENT_FAILED", 400); // Using a more specific exception and adjusting HTTP status code
	    }

	    try {
	        log.info("Invoking Product Service to fetch the product for ID: {}", order.getProductId());
	        ProductResponse productResponse = restTemplate.getForObject(
	                "http://PRODUCT-SERVICE/product/" + order.getProductId(),
	                ProductResponse.class);

	        log.info("Getting Payment Information from payment service");
	        PaymentResponse paymentResponse = restTemplate.getForObject(
	                "http://PAYMENT-SERVICE/payment/order/" + order.getId(),
	                PaymentResponse.class);

	        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
	                .productName(productResponse.getProductName())
	                .productId(productResponse.getProductId())
	                .quantity(productResponse.getQuantity())
	                .price(productResponse.getPrice())
	                .build();

	        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
	                .paymentId(paymentResponse.getPaymentId())
	                .status(paymentResponse.getStatus())
	                .paymentDate(paymentResponse.getPaymentDate())
	                .paymentMode(paymentResponse.getPaymentMode())
	                .amount(paymentResponse.getAmount())
	                .build();

	        OrderResponse orderResponse = OrderResponse.builder()
	                .orderId(order.getId())
	                .orderStatus(order.getOrderStatus())
	                .amount(order.getAmount())
	                .orderDate(order.getOrderDate())
	                .productDetails(productDetails)
	                .paymentDetails(paymentDetails)
	                .build();

	        log.info("Order details retrieved successfully for Order Id: {}", orderId);

	        return orderResponse;
	    } catch (Exception e) {
	        log.error("Error while processing order details for Order Id: {}", orderId, e);
	        throw new CustomException("Error while processing order details for Order Id: " + orderId,
	                "INTERNAL_SERVER_ERROR", 500);
	    }
	}


	
}
