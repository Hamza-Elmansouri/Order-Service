package com.ecomerce_data_model.orderservice.service;


import com.ecomerce_data_model.orderservice.entity.Order;
import com.ecomerce_data_model.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }


    public boolean updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            updatedOrder.setOrderId(orderId);
            orderRepository.save(updatedOrder);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteOrder(Long orderId) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            orderRepository.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }

    public List<Order> getOrdersByAccountId(Long accountId) {
        return orderRepository.findByAccountIdOrderByOrderDate(accountId);
    }
}
