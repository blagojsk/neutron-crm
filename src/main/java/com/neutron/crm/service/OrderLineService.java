package com.neutron.crm.service;

import com.neutron.crm.model.OrderLine;

import java.util.List;

public interface OrderLineService {
    List<OrderLine> findAll();
}
