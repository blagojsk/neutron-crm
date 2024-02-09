package com.neutron.crm.web.dto;

import java.util.List;

public record OrderFollowUpsDTO(List<OrderDTO> orders, Integer totalQuantity, Double totalPrice) {
}
