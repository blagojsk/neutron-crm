package com.neutron.crm.web.dto;


import java.time.LocalDate;
import java.util.List;

public record OrderDTO(Long id, String customerName, List<OrderLineDTO> orderLines, LocalDate date) {
}
