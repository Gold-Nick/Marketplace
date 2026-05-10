package com.edu.kpi.marketplace.marketplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String brand;

    @NotBlank
    private String sku;

    @PositiveOrZero
    private double price;

    @PositiveOrZero
    private int stock;

    private String description;

    private String categoryId;

    private List<String> images;
}
