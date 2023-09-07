package com.ecomerce_data_model.orderservice.service;

import com.ecomerce_data_model.orderservice.entity.Order;
import com.ecomerce_data_model.orderservice.entity.OrderLine;
import com.ecomerce_data_model.orderservice.repository.OrderLinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineService {

    @Autowired
    private OrderLinesRepository orderLinesRepository;

    public OrderLine createOrderLine(OrderLine orderLine) {
        return orderLinesRepository.save(orderLine);
    }

    public Optional<OrderLine> getOrderById(Long orderLineId) {
        return orderLinesRepository.findById(orderLineId);
    }

    public boolean updateOrderLine(Long orderLineId, OrderLine updatedOrderLine) {
        Optional<OrderLine> existingOrder = orderLinesRepository.findById(orderLineId);
        if (existingOrder.isPresent()) {
            updatedOrderLine.setLineId(orderLineId);
            orderLinesRepository.save(updatedOrderLine);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteOrderLine(Long orderLineId) {
        Optional<OrderLine> existingOrder = orderLinesRepository.findById(orderLineId);
        if (existingOrder.isPresent()) {
            orderLinesRepository.deleteById(orderLineId);
            return true;
        } else {
            return false;
        }
    }
}
