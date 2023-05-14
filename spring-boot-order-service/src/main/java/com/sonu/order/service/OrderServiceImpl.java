package com.sonu.order.service;

import com.sonu.order.beans.entity.Customer;
import com.sonu.order.beans.entity.Order;
import com.sonu.order.beans.entity.Product;
import com.sonu.order.beans.request.OrderRequest;
import com.sonu.order.beans.response.OrderResponse;
import com.sonu.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Customer customer = restTemplate.getForObject("http://192.168.0.100:1001/customer-register/customer/getCustomer/" + orderRequest.getCustomerID(), Customer.class);

        List<Product> products = new ArrayList<>();
        for (Integer productId : orderRequest.getProductId()) {
            products.add(restTemplate.getForObject("http://192.168.0.100:1002/product-register/product/getProduct/" + productId, Product.class));
        }
        System.out.println("OrderServiceImpl.placeOrder--> " + customer);
        Order order = new Order();
        order.setCustomerID(orderRequest.getCustomerID());
        order.setProductId(orderRequest.getProductId());
        order.setOrderTime(LocalDateTime.now());
        Order orderRes = orderRepository.save(order);
        return new OrderResponse(orderRes.getOrderId(), orderRes.getOrderTime(), customer, products);
    }
}
