package com.neutron.crm.service.impl;

import com.neutron.crm.converter.OrderConverter;
import com.neutron.crm.model.Order;
import com.neutron.crm.repository.OrderRepository;
import com.neutron.crm.service.OrderService;
import com.neutron.crm.web.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public List<OrderDTO> getAllOrderDTOS() {
        final List<Order> orders = orderRepository.findAll();
        return orderConverter.convertToOrderDTOS(orders);
    }

    @Override
    public Optional<Order> findById(final Long orderId) {
        return orderRepository.findById(orderId);
    }
}
