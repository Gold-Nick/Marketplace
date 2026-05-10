package com.edu.kpi.marketplace.marketplace.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderItemDto {

    @NotBlank
    private String productId;

    @Min(1)
    private int quantity;
}
