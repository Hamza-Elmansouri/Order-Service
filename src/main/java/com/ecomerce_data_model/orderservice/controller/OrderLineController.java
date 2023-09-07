package com.ecomerce_data_model.orderservice.controller;


import com.ecomerce_data_model.orderservice.entity.Order;
import com.ecomerce_data_model.orderservice.entity.OrderLine;
import com.ecomerce_data_model.orderservice.service.OrderLineService;
import com.ecomerce_data_model.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders/{id}/lines")
@Slf4j
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @PostMapping
    public ResponseEntity<OrderLine> createOrderLine(@RequestBody OrderLine orderLine) {
        log.info("inside createOrderLine in OrderLineController");
        OrderLine createdOrder = orderLineService.createOrderLine(orderLine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{orderLine_id}")
    public ResponseEntity<OrderLine> getOrderLine(@PathVariable("orderLine_id") Long orderLineId) {
        log.info("inside getOrderLine in OrderController");
        Optional<OrderLine> order = orderLineService.getOrderById(orderLineId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{orderLine_id}")
    public ResponseEntity<String> updateOrderLine(@PathVariable("orderLine_id") Long orderLineId, @RequestBody OrderLine orderLine) {
        log.info("inside updateOrderLine in OrderLineController");
        boolean updated = orderLineService.updateOrderLine(orderLineId, orderLine);
        if (updated) {
            return ResponseEntity.ok("OrderLine updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderLine_id}")
    public ResponseEntity<String> deleteOrderLine(@PathVariable("orderLine_id") Long orderLineId) {
        log.info("inside deleteOrderLine in OrderLineController");
        boolean deleted = orderLineService.deleteOrderLine(orderLineId);
        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
