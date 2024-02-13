package com.neutron.crm.web.dto;

public record OrderLineDTO(Long id, String productName, Integer quantity, Double price) {
}
