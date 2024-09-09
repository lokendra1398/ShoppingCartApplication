package com.codebuffer.OrderService.service;

import com.codebuffer.OrderService.model.OrderRequest;
import com.codebuffer.OrderService.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderId);

	

}
