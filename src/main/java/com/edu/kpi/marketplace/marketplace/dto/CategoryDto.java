package com.edu.kpi.marketplace.marketplace.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

    private String id;

    @NotBlank
    private String name;

    private String parentId;

    private String slug;
}
