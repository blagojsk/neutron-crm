package com.neutron.crm.web.dto;

import java.time.LocalDate;

public record OrderDetailsDTO(Long id, String productName, LocalDate date, Integer quantity, Double price) {
}
