package com.neutron.crm.web.controller;

import com.neutron.crm.model.Order;
import com.neutron.crm.model.OrderFollowUp;
import com.neutron.crm.service.OrderFollowUpService;
import com.neutron.crm.service.OrderService;
import com.neutron.crm.web.dto.FollowUpsDTO;
import com.neutron.crm.web.dto.OrderDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderFollowUpService orderFollowUpService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrderDTOS();
    }

    @RequestMapping(path = "/follow-up", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FollowUpsDTO getAllFollowUpOrders() {
        return orderFollowUpService.getOrderFollowUpsDTO();
    }

    @RequestMapping(path = "/follow-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFollowUpOrder(@RequestParam Long orderId) {
        final Optional<Order> optionalOrder = orderService.findById(orderId);
        final Order order = optionalOrder.orElseThrow(EntityNotFoundException::new);
        final OrderFollowUp orderFollowUp = new OrderFollowUp(order.getId(), order);
        orderFollowUpService.save(orderFollowUp);
        return ResponseEntity.ok().build();
    }
}
