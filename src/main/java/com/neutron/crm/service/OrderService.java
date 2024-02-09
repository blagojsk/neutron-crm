package com.neutron.crm.service;

import com.neutron.crm.model.Order;
import com.neutron.crm.web.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getAllOrderDTOS();

    Optional<Order> findById(Long orderId);
}
