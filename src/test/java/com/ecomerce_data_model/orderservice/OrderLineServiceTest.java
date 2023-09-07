package com.ecomerce_data_model.orderservice;

import com.ecomerce_data_model.orderservice.entity.OrderLine;
import com.ecomerce_data_model.orderservice.repository.OrderLinesRepository;
import com.ecomerce_data_model.orderservice.service.OrderLineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderLineServiceTest {

    @Mock
    private OrderLinesRepository orderLinesRepository;

    @InjectMocks
    private OrderLineService orderLineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrderLine() {
        OrderLine orderLine = new OrderLine();
        when(orderLinesRepository.save(any(OrderLine.class))).thenReturn(orderLine);

        OrderLine createdOrderLine = orderLineService.createOrderLine(orderLine);

        assertNotNull(createdOrderLine);
        verify(orderLinesRepository, times(1)).save(orderLine);
    }

    @Test
    public void testGetOrderLineById() {
        Long orderLineId = 1L;
        OrderLine orderLine = new OrderLine();
        when(orderLinesRepository.findById(orderLineId)).thenReturn(Optional.of(orderLine));

        Optional<OrderLine> retrievedOrderLine = orderLineService.getOrderById(orderLineId);

        assertTrue(retrievedOrderLine.isPresent());
        assertEquals(orderLine, retrievedOrderLine.get());
    }

    @Test
    public void testUpdateOrderLine() {
        Long orderLineId = 1L;
        OrderLine updatedOrderLine = new OrderLine();
        when(orderLinesRepository.findById(orderLineId)).thenReturn(Optional.of(new OrderLine()));

        boolean result = orderLineService.updateOrderLine(orderLineId, updatedOrderLine);

        assertTrue(result);
        verify(orderLinesRepository, times(1)).save(updatedOrderLine);
    }

    @Test
    public void testDeleteOrderLine() {
        Long orderLineId = 1L;
        when(orderLinesRepository.findById(orderLineId)).thenReturn(Optional.of(new OrderLine()));

        boolean result = orderLineService.deleteOrderLine(orderLineId);

        assertTrue(result);
        verify(orderLinesRepository, times(1)).deleteById(orderLineId);
    }

}