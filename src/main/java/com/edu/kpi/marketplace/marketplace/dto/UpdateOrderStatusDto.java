package com.edu.kpi.marketplace.marketplace.dto;

import com.edu.kpi.marketplace.marketplace.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusDto {

    @NotNull
    private OrderStatus status;
}
