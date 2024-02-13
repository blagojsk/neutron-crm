package com.neutron.crm.service.impl;

import com.neutron.crm.converter.OrderConverter;
import com.neutron.crm.model.Order;
import com.neutron.crm.repository.OrderRepository;
import com.neutron.crm.web.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplUnitTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderConverter orderConverter;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void getAllOrderDTOS_ShouldReturnListOfOrderDTOs_WhenOrdersExist() {
        // Arrange
        List<Order> mockOrders = Arrays.asList(new Order(), new Order());
        List<OrderDTO> mockOrderDTOs = Arrays.asList(Mockito.mock(OrderDTO.class), Mockito.mock(OrderDTO.class));

        when(orderRepository.findAll()).thenReturn(mockOrders);
        when(orderConverter.convertToOrderDTOS(mockOrders)).thenReturn(mockOrderDTOs);

        // Act
        List<OrderDTO> result = orderService.getAllOrderDTOS();

        // Assert
        assertEquals(mockOrderDTOs.size(), result.size());
        verify(orderRepository, times(1)).findAll();
        verify(orderConverter, times(1)).convertToOrderDTOS(mockOrders);
    }

    @Test
    void getAllOrderDTOS_ShouldReturnEmptyList_WhenNoOrdersExist() {
        // Arrange
        List<Order> mockOrders = Arrays.asList();
        List<OrderDTO> mockOrderDTOs = Arrays.asList();

        when(orderRepository.findAll()).thenReturn(mockOrders);
        when(orderConverter.convertToOrderDTOS(mockOrders)).thenReturn(mockOrderDTOs);

        // Act
        List<OrderDTO> result = orderService.getAllOrderDTOS();

        // Assert
        assertEquals(mockOrderDTOs.size(), result.size());
        verify(orderRepository, times(1)).findAll();
        verify(orderConverter, times(1)).convertToOrderDTOS(mockOrders);
    }

    @Test
    void findById_ShouldReturnOrder_WhenOrderExists() {
        // Arrange
        Long orderId = 1L;
        Order mockOrder = new Order();
        Optional<Order> optionalOrder = Optional.of(mockOrder);

        when(orderRepository.findById(orderId)).thenReturn(optionalOrder);

        // Act
        Optional<Order> result = orderService.findById(orderId);

        // Assert
        assertEquals(optionalOrder, result);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void findById_ShouldReturnEmptyOptional_WhenOrderDoesNotExist() {
        // Arrange
        Long orderId = 1L;
        Optional<Order> optionalOrder = Optional.empty();

        when(orderRepository.findById(orderId)).thenReturn(optionalOrder);

        // Act
        Optional<Order> result = orderService.findById(orderId);

        // Assert
        assertEquals(optionalOrder, result);
        verify(orderRepository, times(1)).findById(orderId);
    }
}
