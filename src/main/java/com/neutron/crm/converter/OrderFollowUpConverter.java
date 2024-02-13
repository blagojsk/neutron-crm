package com.neutron.crm.converter;

import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.web.dto.FollowUpsDTO;

import java.util.Collection;

public interface OrderFollowUpConverter {
    FollowUpsDTO convertToOrderFollowUpsDTO(Collection<OrderFollowUp> orderFollowUps);
}
