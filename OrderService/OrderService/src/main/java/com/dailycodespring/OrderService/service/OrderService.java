package com.dailycodespring.OrderService.service;

import com.dailycodespring.OrderService.model.OrderRequest;
import com.dailycodespring.OrderService.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderId);

}
