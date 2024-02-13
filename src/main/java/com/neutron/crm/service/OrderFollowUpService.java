package com.neutron.crm.service;

import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.web.dto.FollowUpsDTO;

public interface OrderFollowUpService {
    FollowUpsDTO getOrderFollowUpsDTO();

    void save(OrderFollowUp orderFollowUp);
}
