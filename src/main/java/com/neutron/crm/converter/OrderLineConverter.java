package com.neutron.crm.converter;

import com.neutron.crm.model.OrderLine;
import com.neutron.crm.web.dto.OrderLineDTO;

import java.util.Collection;
import java.util.List;

public interface OrderLineConverter {
    List<OrderLineDTO> convertToOrderLineDTOS(Collection<OrderLine> orderLines);
}
