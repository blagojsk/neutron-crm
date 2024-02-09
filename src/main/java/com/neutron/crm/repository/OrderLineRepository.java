package com.neutron.crm.repository;

import com.neutron.crm.model.Order;
import com.neutron.crm.model.OrderLine;
import com.neutron.crm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    Optional<OrderLine> findByOrderAndProduct(Order order, Product product);
}
