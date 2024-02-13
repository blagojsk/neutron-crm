package com.neutron.crm.converter;

import com.neutron.crm.model.Order;
import com.neutron.crm.web.dto.OrderDTO;
import com.neutron.crm.web.dto.OrderDetailsDTO;

import java.util.Collection;
import java.util.List;

public interface OrderConverter {
    List<OrderDTO> convertToOrderDTOS(Collection<Order> orders);

    List<OrderDetailsDTO> convertToOrderDetailsDTOS(Collection<Order> orders);
}
