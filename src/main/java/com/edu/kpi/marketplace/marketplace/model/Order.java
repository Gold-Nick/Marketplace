package com.edu.kpi.marketplace.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection  = "Orders")
public class Order {

    @Id
    private String id;

    private String userId;

    private List<OrderItem> items;

    private Double totalSum;

    private OrderStatus status;
    
    private Instant createdAt;
}
