package com.edu.kpi.marketplace.marketplace.service;

import com.edu.kpi.marketplace.marketplace.dto.ProductDto;
import com.edu.kpi.marketplace.marketplace.model.Product;
import com.edu.kpi.marketplace.marketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<ProductDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ProductDto findById(String id) {
        return toDto(repository.findById(id).orElseThrow());
    }

    public ProductDto save(ProductDto productDto) {
        return toDto(repository.save(toEntity(productDto)));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<ProductDto> findByBrand(String brand) {
        return repository.findByBrand(brand)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<ProductDto> findByCategory(String categoryId) {
        return repository.findByCategoryId(categoryId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ProductDto decreaseStock(String productId, int qty) {

        Product product = repository.findById(productId)
                .orElseThrow();

        if (product.getStock() < qty) {
            throw new IllegalStateException("Not enough stock");
        }

        product.setStock(product.getStock() - qty);

        return toDto(repository.save(product));
    }

    public List<ProductDto> findByCompatibility(String make, String model, Integer year) {

        return repository.findByCompatibility(make, model, year)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<ProductDto> search(String query) {
        return repository.search(query)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private Product toEntity(ProductDto dto) {
        if (dto == null) return null;

        return Product.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .brand(dto.getBrand())
                .sku(dto.getSku())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .images(dto.getImages())
                .build();
    }

    private ProductDto toDto(Product entity) {
        if (entity == null) return null;

        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setBrand(entity.getBrand());
        dto.setSku(entity.getSku());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategoryId());
        dto.setImages(entity.getImages());
        return dto;
    }
}
