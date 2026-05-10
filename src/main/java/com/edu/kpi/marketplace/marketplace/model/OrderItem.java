package com.edu.kpi.marketplace.marketplace.model;

import lombok.Data;

@Data
public class OrderItem {

    private String productId;

    private Integer quantity;

    private Double priceAtPurchase;
}
