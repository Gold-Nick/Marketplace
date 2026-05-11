package com.edu.kpi.marketplace.marketplace.controller;

import com.edu.kpi.marketplace.marketplace.dto.ProductDto;
import com.edu.kpi.marketplace.marketplace.service.ProductService;
import jakarta.validation.Valid;
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
    public List<ProductDto> getByBrand(@PathVariable String brand) {
        return service.findByBrand(brand);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getByCategory(@PathVariable String categoryId) {
        return service.findByCategory(categoryId);
    }

    @PostMapping
    public ProductDto create(@Valid @RequestBody ProductDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable String id, @Valid @RequestBody ProductDto dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @GetMapping("/search")
    public List<ProductDto> search(@RequestParam String q) {
        return service.search(q);
    }

    @GetMapping("/compatibility")
    public List<ProductDto> searchByCompatibility(@RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year
    ) {
        return service.findByCompatibility(make, model, year);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
