package com.ecomerce_data_model.orderservice.controller;

import com.ecomerce_data_model.orderservice.entity.Order;
import com.ecomerce_data_model.orderservice.repository.OrderRepository;
import com.ecomerce_data_model.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        log.info("inside createOrder in OrderController");
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<Order> getOrder(@PathVariable("order_id") Long orderId) {
        log.info("inside getOrder in OrderController");
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrdersByAccountId(@RequestParam("accountId") Long accountId) {
        log.info("inside getOrdersByAccountId in OrderController");
        List<Order> orders = orderService.getOrdersByAccountId(accountId);

        orders.sort(Comparator.comparing(Order::getOrderDate));

        return ResponseEntity.ok(orders);
    }


    @PutMapping("/{order_id}")
    public ResponseEntity<String> updateOrder(@PathVariable("order_id") Long orderId, @RequestBody Order order) {
        log.info("inside updateOrder in OrderController");
        boolean updated = orderService.updateOrder(orderId, order);
        if (updated) {
            return ResponseEntity.ok("Order updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{order_id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("order_id") Long orderId) {
        log.info("inside deleteOrder in OrderController");
        boolean deleted = orderService.deleteOrder(orderId);
        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

