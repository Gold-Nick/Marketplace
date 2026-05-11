package com.edu.kpi.marketplace.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection  = "Products")
public class Product {

    @Id
    private String id;

    private List<String> images;

    private String oemCode;

    private Double price;

    private String sku;

    private Integer stock;

    private String brand;

    private String title;

    private String categoryId;

    private List<Compatibility> compatibility;

    private String description;
}
