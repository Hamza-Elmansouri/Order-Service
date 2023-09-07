package com.ecomerce_data_model.orderservice;

import com.ecomerce_data_model.orderservice.controller.OrderController;
import com.ecomerce_data_model.orderservice.entity.Order;
import com.ecomerce_data_model.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderController = new OrderController(orderService);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        Order createdOrder = new Order();
        when(orderService.createOrder(order)).thenReturn(createdOrder);

        ResponseEntity<Order> response = orderController.createOrder(order);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdOrder, response.getBody());
    }

    @Test
    void testGetOrder() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(order));

        ResponseEntity<Order> response = orderController.getOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    void testGetNotFoundOrder() {
        Long orderId = 1L;
        when(orderService.getOrderById(orderId)).thenReturn(Optional.empty());

        ResponseEntity<Order> response = orderController.getOrder(orderId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateOrder() {
        Order order = new Order();
        order.setOrderNumber(1L);

        when(orderService.updateOrder(eq(1L), any(Order.class))).thenReturn(true);

        ResponseEntity<String> response = orderController.updateOrder(1L, order);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order updated successfully", response.getBody());

        verify(orderService).updateOrder(eq(1L), any(Order.class));
    }

    @Test
    void testUpdateOrderNotFound() {
        Order order = new Order();
        order.setOrderId(1L);

        when(orderService.updateOrder(eq(1L), any(Order.class))).thenReturn(false);

        ResponseEntity<String> response = orderController.updateOrder(1L, order);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(orderService).updateOrder(eq(1L), any(Order.class));
    }

    @Test
    void testDeleteOrder() {
        when(orderService.deleteOrder(1L)).thenReturn(true);

        ResponseEntity<String> response = orderController.deleteOrder(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());

        verify(orderService).deleteOrder(1L);
    }

    @Test
    void testDeleteOrderNotFound() {
        when(orderService.deleteOrder(1L)).thenReturn(false);

        ResponseEntity<String> response = orderController.deleteOrder(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(orderService).deleteOrder(1L);
    }

    @Test
    void testGetOrdersByAccountId() {
        Long accountId = 1L;
        LocalDate orderDate1 = LocalDate.of(2023, 10, 18);
        LocalDate orderDate2 = LocalDate.of(2023, 11, 18);
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setAccountId(accountId);
        order1.setOrderDate(orderDate1);
        orders.add(order1);

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setAccountId(accountId);
        order2.setOrderDate(orderDate2);
        orders.add(order2);

        when(orderService.getOrdersByAccountId(accountId)).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderController.getOrdersByAccountId(accountId);

        verify(orderService, times(1)).getOrdersByAccountId(accountId);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

}

