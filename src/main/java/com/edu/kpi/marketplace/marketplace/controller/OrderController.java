package com.edu.kpi.marketplace.marketplace.controller;

import com.edu.kpi.marketplace.marketplace.dto.OrderDto;
import com.edu.kpi.marketplace.marketplace.dto.UpdateOrderStatusDto;
import com.edu.kpi.marketplace.marketplace.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public List<OrderDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderDto> getByUser(@PathVariable String userId) {
        return service.findByUserId(userId);
    }

    @PostMapping
    public OrderDto create(@Valid @RequestBody OrderDto dto) {
        return service.create(dto);
    }

    @PatchMapping("/{id}/status")
    public OrderDto updateStatus(@PathVariable String id, @Valid @RequestBody UpdateOrderStatusDto dto) {
        return service.updateStatus(id, dto.getStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
