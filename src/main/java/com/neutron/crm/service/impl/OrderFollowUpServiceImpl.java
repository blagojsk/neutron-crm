package com.neutron.crm.service.impl;

import com.neutron.crm.converter.OrderFollowUpConverter;
import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.repository.OrderFollowUpRepository;
import com.neutron.crm.service.OrderFollowUpService;
import com.neutron.crm.web.dto.FollowUpsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFollowUpServiceImpl implements OrderFollowUpService {

    private final OrderFollowUpRepository orderFollowUpRepository;
    private final OrderFollowUpConverter orderFollowUpConverter;

    @Override
    public FollowUpsDTO getOrderFollowUpsDTO() {
        List<OrderFollowUp> orderFollowUps = orderFollowUpRepository.findAll();
        return orderFollowUpConverter.convertToOrderFollowUpsDTO(orderFollowUps);
    }

    @Override
    public void save(OrderFollowUp orderFollowUp) {
        orderFollowUpRepository.save(orderFollowUp);
    }
}
