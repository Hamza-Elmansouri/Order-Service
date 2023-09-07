package com.ecomerce_data_model.orderservice;

import com.ecomerce_data_model.orderservice.entity.Order;
import com.ecomerce_data_model.orderservice.repository.OrderRepository;
import com.ecomerce_data_model.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static reactor.core.publisher.Mono.when;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertNotNull(createdOrder);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> retrievedOrder = orderService.getOrderById(orderId);

        assertTrue(retrievedOrder.isPresent());
        assertEquals(order, retrievedOrder.get());
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        Order updatedOrder = new Order();
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(new Order()));

        boolean result = orderService.updateOrder(orderId, updatedOrder);

        assertTrue(result);
        verify(orderRepository, times(1)).save(updatedOrder);
    }

    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(new Order()));

        boolean result = orderService.deleteOrder(orderId);

        assertTrue(result);
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    public void testGetOrdersByAccountId() {
        Long accountId = 1L;
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        Mockito.when(orderRepository.findByAccountIdOrderByOrderDate(accountId)).thenReturn(orders);

        List<Order> retrievedOrders = orderService.getOrdersByAccountId(accountId);

        assertEquals(2, retrievedOrders.size());
        verify(orderRepository, times(1)).findByAccountIdOrderByOrderDate(accountId);
    }
}
