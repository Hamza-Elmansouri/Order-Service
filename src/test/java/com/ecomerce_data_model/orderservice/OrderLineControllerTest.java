package com.ecomerce_data_model.orderservice;

import com.ecomerce_data_model.orderservice.controller.OrderController;
import com.ecomerce_data_model.orderservice.controller.OrderLineController;
import com.ecomerce_data_model.orderservice.entity.Order;
import com.ecomerce_data_model.orderservice.entity.OrderLine;
import com.ecomerce_data_model.orderservice.service.OrderLineService;
import com.ecomerce_data_model.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderLineControllerTest {

    private OrderLineController orderLineController;

    @Mock
    private OrderLineService orderLineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderLineController = new OrderLineController(orderLineService);
    }

    @Test
    void testCreateOrderLine() {
        OrderLine orderLine = new OrderLine();
        OrderLine createdOrderLine = new OrderLine();
        when(orderLineService.createOrderLine(orderLine)).thenReturn(createdOrderLine);

        ResponseEntity<OrderLine> response = orderLineController.createOrderLine(orderLine);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdOrderLine, response.getBody());
    }

    @Test
    void testGetOrderLine() {
        Long orderLineId = 1L;
        OrderLine orderLine = new OrderLine();
        when(orderLineService.getOrderById(orderLineId)).thenReturn(Optional.of(orderLine));

        ResponseEntity<OrderLine> response = orderLineController.getOrderLine(orderLineId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderLine, response.getBody());
    }

    @Test
    void testGetNotFoundOrderLine() {
        Long orderLineId = 1L;
        when(orderLineService.getOrderById(orderLineId)).thenReturn(Optional.empty());

        ResponseEntity<OrderLine> response = orderLineController.getOrderLine(orderLineId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateOrderLine() {
        OrderLine orderLine = new OrderLine();
        orderLine.setLineId(1L);

        when(orderLineService.updateOrderLine(eq(1L), any(OrderLine.class))).thenReturn(true);

        ResponseEntity<String> response = orderLineController.updateOrderLine(1L, orderLine);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OrderLine updated successfully", response.getBody());

        verify(orderLineService).updateOrderLine(eq(1L), any(OrderLine.class));
    }

    @Test
    void testUpdateOrderLineNotFound() {
        OrderLine orderLine = new OrderLine();
        orderLine.setLineId(1L);

        when(orderLineService.updateOrderLine(eq(1L), any(OrderLine.class))).thenReturn(false);

        ResponseEntity<String> response = orderLineController.updateOrderLine(1L, orderLine);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(orderLineService).updateOrderLine(eq(1L), any(OrderLine.class));
    }

    @Test
    void testDeleteOrderLine() {
        when(orderLineService.deleteOrderLine(1L)).thenReturn(true);

        ResponseEntity<String> response = orderLineController.deleteOrderLine(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());

        verify(orderLineService).deleteOrderLine(1L);
    }

    @Test
    void testDeleteOrderLineNotFound() {
        when(orderLineService.deleteOrderLine(1L)).thenReturn(false);

        ResponseEntity<String> response = orderLineController.deleteOrderLine(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(orderLineService).deleteOrderLine(1L);
    }
}
