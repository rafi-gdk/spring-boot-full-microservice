package com.sonu.order.service;

import com.sonu.order.beans.request.OrderRequest;
import com.sonu.order.beans.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);

}
