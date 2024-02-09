package com.neutron.crm.converter;

import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.web.dto.OrderFollowUpsDTO;

import java.util.Collection;

public interface OrderFollowUpConverter {
    OrderFollowUpsDTO convertToOrderFollowUpsDTO(Collection<OrderFollowUp> orderFollowUps);
}
