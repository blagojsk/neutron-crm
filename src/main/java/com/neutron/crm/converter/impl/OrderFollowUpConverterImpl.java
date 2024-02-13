package com.neutron.crm.converter.impl;

import com.neutron.crm.converter.OrderConverter;
import com.neutron.crm.converter.OrderFollowUpConverter;
import com.neutron.crm.model.Order;
import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.web.dto.FollowUpsDTO;
import com.neutron.crm.web.dto.OrderDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderFollowUpConverterImpl implements OrderFollowUpConverter {

    private final OrderConverter orderConverter;

    @Override
    public FollowUpsDTO convertToOrderFollowUpsDTO(final Collection<OrderFollowUp> orderFollowUps) {
        List<Order> orders = orderFollowUps.stream().map(OrderFollowUp::getOrder).toList();
        Integer totalFollowUpQuantity = orders.stream().mapToInt(Order::getTotalQuantity).sum();
        Double totalFollowUpPrice = orders.stream().mapToDouble(Order::getTotalPrice).sum();
        final List<OrderDetailsDTO> orderDetails = orderConverter.convertToOrderDetailsDTOS(orders);
        return new FollowUpsDTO(orderDetails, totalFollowUpQuantity, totalFollowUpPrice);
    }
}
