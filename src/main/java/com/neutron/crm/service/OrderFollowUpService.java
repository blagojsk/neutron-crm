package com.neutron.crm.service;

import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.web.dto.OrderFollowUpsDTO;

public interface OrderFollowUpService {
    OrderFollowUpsDTO getOrderFollowUpsDTO();

    void save(OrderFollowUp orderFollowUp);
}
