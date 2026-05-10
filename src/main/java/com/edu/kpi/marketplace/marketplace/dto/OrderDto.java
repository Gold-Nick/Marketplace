package com.edu.kpi.marketplace.marketplace.dto;

import com.edu.kpi.marketplace.marketplace.model.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private String id;

    @NotBlank
    private String userId;

    @NotEmpty
    private List<OrderItemDto> items;

    private OrderStatus status;

    private Double totalSum;
}
