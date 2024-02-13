package com.neutron.crm.covnerter.impl;

import com.neutron.crm.converter.OrderLineConverter;
import com.neutron.crm.converter.impl.OrderConverterImpl;
import com.neutron.crm.web.dto.OrderDTO;
import com.neutron.crm.web.dto.OrderDetailsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderConverterImplUnitTest {

    @Mock
    private OrderLineConverter orderLineConverter;

    @InjectMocks
    private OrderConverterImpl orderConverter;

    @Test
    void testConvertToOrderDTOS_WithEmptyOrders_ReturnsEmptyList() {
        List<OrderDTO> orderDTOS = orderConverter.convertToOrderDTOS(Collections.emptyList());
        assertEquals(0, orderDTOS.size());
    }

    @Test
    void testConvertToOrderDetailsDTOS_WithEmptyOrders_ReturnsEmptyList() {
        List<OrderDetailsDTO> orderDetailsDTOS = orderConverter.convertToOrderDetailsDTOS(Collections.emptyList());
        assertEquals(0, orderDetailsDTOS.size());
    }
}
