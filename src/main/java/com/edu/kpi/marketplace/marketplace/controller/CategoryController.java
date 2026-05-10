package com.edu.kpi.marketplace.marketplace.controller;

import com.edu.kpi.marketplace.marketplace.dto.CategoryDto;
import com.edu.kpi.marketplace.marketplace.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        return service.save(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto update(
            @PathVariable String id,
            @RequestBody CategoryDto categoryDto
    ) {
        categoryDto.setId(id);
        return service.save(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
