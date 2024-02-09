package com.neutron.crm.service.impl;

import com.neutron.crm.model.OrderLine;
import com.neutron.crm.repository.OrderLineRepository;
import com.neutron.crm.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;

    @Override
    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }
}
