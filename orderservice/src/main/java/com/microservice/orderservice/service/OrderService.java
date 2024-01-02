package com.microservice.orderservice.service;

import com.microservice.orderservice.payload.request.OrderRequest;
import com.microservice.orderservice.payload.response.OrderResponse;

public interface OrderService {
    int placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(int orderId);
}
