package com.neutron.crm.converter.impl;

import com.neutron.crm.converter.OrderLineConverter;
import com.neutron.crm.model.OrderLine;
import com.neutron.crm.model.Product;
import com.neutron.crm.web.dto.OrderLineDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class OrderLineConverterImpl implements OrderLineConverter {

    @Override
    public List<OrderLineDTO> convertToOrderLineDTOS(final Collection<OrderLine> orderLines) {
        return orderLines.stream().map(orderLine -> {
            final Product product = orderLine.getProduct();
            final Double orderLinePrice = product.getPrice() * orderLine.getQuantity();
            return new OrderLineDTO(product.getName(), orderLine.getQuantity(), orderLinePrice);
        }).toList();
    }
}
