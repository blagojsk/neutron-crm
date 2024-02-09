package com.neutron.crm.web.dto;

public record OrderLineDTO(String productName, Integer quantity, Double orderLinePrice) {
}
