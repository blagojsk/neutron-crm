package com.neutron.crm.repository;

import com.neutron.crm.model.Order;
import com.neutron.crm.model.OrderFollowUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFollowUpRepository extends JpaRepository<OrderFollowUp, Order> {
}
