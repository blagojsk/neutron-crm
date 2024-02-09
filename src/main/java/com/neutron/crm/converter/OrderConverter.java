package com.neutron.crm.converter;

import com.neutron.crm.model.Order;
import com.neutron.crm.web.dto.OrderDTO;

import java.util.Collection;
import java.util.List;

public interface OrderConverter {
    List<OrderDTO> convertToOrderDTOS(Collection<Order> orders);
}
