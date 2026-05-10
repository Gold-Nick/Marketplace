package com.edu.kpi.marketplace.marketplace.controller;

import com.edu.kpi.marketplace.marketplace.dto.ProductDto;
import com.edu.kpi.marketplace.marketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/brand/{brand}")
    public List<ProductDto> getByBrand(
            @PathVariable String brand
    ) {
        return service.findByBrand(brand);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getByCategory(
            @PathVariable String categoryId
    ) {
        return service.findByCategory(categoryId);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return service.save(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto update(
            @PathVariable String id,
            @RequestBody ProductDto productDto
    ) {
        productDto.setId(id);
        return service.save(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
