//package com.dailycodespring.OrderService.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.times;
//
//import java.time.Instant;
//import java.util.Optional;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.dailycodespring.OrderService.entity.Order;
//import com.dailycodespring.OrderService.exception.CustomException;
//import com.dailycodespring.OrderService.external.client.PaymentService;
//import com.dailycodespring.OrderService.external.client.ProductService;
//import com.dailycodespring.OrderService.external.request.PaymentRequest;
//import com.dailycodespring.OrderService.external.response.PaymentResponse;
//import com.dailycodespring.OrderService.model.OrderRequest;
//import com.dailycodespring.OrderService.model.OrderResponse;
//import com.dailycodespring.OrderService.model.PaymentMode;
//import com.dailycodespring.OrderService.model.ProductResponse;
//import com.dailycodespring.OrderService.repository.OrderRepository;
//
//import junit.framework.Assert;
//
//@SpringBootTest
//public class OrderServiceImplTest {
//
////	@Test
////	void test() {
////		fail("Not yet implemented");
////	}
//	
////	@Autowired this annotation will inject actual bean so just mock it with below dependency
//	@Mock 
//	private OrderRepository orderRepository;
//	
////	@Autowired
//	@Mock 
//	private ProductService productService;
//	
////	@Autowired
//	@Mock 
//	private PaymentService paymentService;
//	
////	@Autowired
//	@Mock 
//	private RestTemplate restTemplate;
//	
//
////	this will inject all the mocks
//	@InjectMocks
//	OrderService orderService = new OrderServiceImpl();
//	
//	@DisplayName("Get Order - Success Scenario")
//	@Test
//	void test_When_Order_Success() {
//		
////		Mocking
//		Order order = getMockOrder();
//		Mockito.when(orderRepository.findById(anyLong()))
//			.thenReturn(Optional.of(order));
//		
//		Mockito.when(restTemplate.getForObject("http://PRODUCT-SERVICE/product/" + order.getProductId(),
//				ProductResponse.class)).thenReturn(getMockProductResponse());
//		
//		Mockito.when(restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/" + order.getId(), 
//				PaymentResponse.class)).thenReturn(getMockPaymentResponse());
//		
////		Actual method calling & do mocking.
//		OrderResponse orderResponse = orderService.getOrderDetails(1);
//		
////		Verification
//		Mockito.verify(orderRepository, times(1)).findById(anyLong());
//		
//		Mockito.verify(restTemplate, times(1)).getForObject("http://PRODUCT-SERVICE/product/" + order.getProductId(),
//				ProductResponse.class);
//		
//		Mockito.verify(restTemplate, times(1)).getForObject("http://PAYMENT-SERVICE/payment/order/" + order.getId(),
//				PaymentResponse.class);
//		
////		Assert
//		Assert.assertNotNull(orderResponse);
//		Assert.assertEquals(order.getId(), orderResponse.getOrderId());
//		
//	}
//	
////	@Test
////	@DisplayName("Get Orders - Failure Scenario")
////	void test_When_Get_Order_NOT_FOUND_then_Not_Found() {
////		Mockito.when(orderRepository.findById(anyLong()))
////			.thenReturn(Optional.ofNullable(null)); // means whenever we get null it'll throw exception.
////		
//////		OrderResponse orderResponse = orderService.getOrderDetails(1);
////		
////		CustomException customException = 
////				assertThrows(CustomException.class, ()->orderService.getOrderDetails(1));
////		
//////		Specifying what error code it should return 
////		assertEquals("ORDER_NOT_FOUND", customException.getErrorCode());
////		assertEquals(404, customException.getStatus());
////		
////		Mockito.verify(orderRepository, times(1))
////			.findById(anyLong());
////	}
//	
//	@DisplayName("Place Order - Success Scenario.")
//	@Test
//	void test_When_Place_Order_Success() {
//		Order order = getMockOrder();
//		OrderRequest orderRequest = getMockOrderRequest();
//		
//		Mockito.when(orderRepository.save(Mockito.any(Order.class)))
//        .thenReturn(order);
//		
//		Mockito.when(productService.reduceQuantity(anyLong(), anyLong()))
//			.thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
//
//		Mockito.when(paymentService.doPayment(Mockito.any(PaymentRequest.class)))
//			.thenReturn(new ResponseEntity<Long>(1L, HttpStatus.OK));
//		
//		long orderId = orderService.placeOrder(orderRequest);
//		
//		Mockito.verify(orderRepository, times(2))
//			.save(Mockito.any());
//		
//		Mockito.verify(productService, times(1))
//			.reduceQuantity(anyLong(), anyLong());
//		
//		Mockito.verify(paymentService, times(1))
//			.doPayment(Mockito.any(PaymentRequest.class));
//		
//		assertEquals(order.getId(), orderId);
//	}
//
//	private OrderRequest getMockOrderRequest() {
//		// TODO Auto-generated method stub
//		return OrderRequest.builder()
//				.productId(1)
//				.quantity(10)
//				.paymentMode(PaymentMode.CASH)
//				.totalAmount(100)
//				.build();
//	}
//
//	private PaymentResponse getMockPaymentResponse() {
//		// TODO Auto-generated method stub
//		return PaymentResponse.builder()
//				.paymentId(1)
//				.paymentDate(Instant.now())
//				.paymentMode(PaymentMode.CASH)
//				.amount(200)
//				.orderId(1)
//				.status("ACCEPTED")
//				.build();
//	}
//
//	private ProductResponse getMockProductResponse() {
//		// TODO Auto-generated method stub
//		return ProductResponse.builder()
//				.productId(2)
//				.productName("iPhone")
//				.price(100)
//				.quantity(200)
//				.build();
//	}
//
//	private Order getMockOrder() {
//		// TODO Auto-generated method stub
////		constructing a dummy object.
//		return Order.builder()
//				.orderStatus("PLACED")
//				.orderDate(Instant.now())
//				.id(1)
//				.amount(100)
//				.quantity(200)
//				.productId(2)
//				.build();
//	}
//}
