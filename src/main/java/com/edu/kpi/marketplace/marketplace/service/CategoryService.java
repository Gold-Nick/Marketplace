package com.edu.kpi.marketplace.marketplace.service;

import com.edu.kpi.marketplace.marketplace.dto.CategoryDto;
import com.edu.kpi.marketplace.marketplace.model.Category;
import com.edu.kpi.marketplace.marketplace.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public CategoryDto findById(String id) {
        return toDto(repository.findById(id).orElseThrow());
    }

    public CategoryDto save(CategoryDto categoryDto) {
        return toDto(repository.save(toEntity(categoryDto)));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Category toEntity(CategoryDto dto) {
        if (dto == null) return null;

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .parentId(dto.getParentId())
                .slug(dto.getSlug())
                .build();
    }

    public CategoryDto toDto(Category entity) {
        if (entity == null) return null;

        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setParentId(entity.getParentId());
        dto.setSlug(entity.getSlug());
        return dto;
    }
}
