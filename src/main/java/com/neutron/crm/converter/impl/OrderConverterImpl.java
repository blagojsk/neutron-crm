package com.neutron.crm.converter.impl;

import com.neutron.crm.converter.OrderConverter;
import com.neutron.crm.converter.OrderLineConverter;
import com.neutron.crm.model.Order;
import com.neutron.crm.web.dto.OrderDTO;
import com.neutron.crm.web.dto.OrderLineDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConverterImpl implements OrderConverter {

    private final OrderLineConverter orderLineConverter;

    @Override
    public List<OrderDTO> convertToOrderDTOS(final Collection<Order> orders) {
        return orders.stream().map(order -> {
            final List<OrderLineDTO> orderLineDTOS = orderLineConverter.convertToOrderLineDTOS(order.getOrderLines());
            return new OrderDTO(order.getCustomer().getName(), orderLineDTOS, order.getDate());
        }).toList();
    }
}
