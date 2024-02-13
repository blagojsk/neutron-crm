package com.neutron.crm.web.dto;

import java.util.List;

public record FollowUpsDTO(List<OrderDetailsDTO> orders, Integer totalQuantity, Double totalPrice) {
}
