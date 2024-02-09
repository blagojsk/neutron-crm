package com.neutron.crm.converter.impl;

import com.neutron.crm.converter.OrderConverter;
import com.neutron.crm.converter.OrderFollowUpConverter;
import com.neutron.crm.model.Order;
import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.web.dto.OrderDTO;
import com.neutron.crm.web.dto.OrderFollowUpsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderFollowUpConverterImpl implements OrderFollowUpConverter {

    private final OrderConverter orderConverter;

    @Override
    public OrderFollowUpsDTO convertToOrderFollowUpsDTO(final Collection<OrderFollowUp> orderFollowUps) {
        List<Order> orders = orderFollowUps.stream().map(OrderFollowUp::getOrder).toList();
        Integer totalFollowUpQuantity = orders.stream().mapToInt(Order::getTotalQuantity).sum();
        Double totalFollowUpPrice = orders.stream().mapToDouble(Order::getTotalPrice).sum();
        final List<OrderDTO> orderDTOS = orderConverter.convertToOrderDTOS(orders);
        return new OrderFollowUpsDTO(orderDTOS, totalFollowUpQuantity, totalFollowUpPrice);
    }
}
