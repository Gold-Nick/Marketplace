package com.edu.kpi.marketplace.marketplace.controller;

import com.edu.kpi.marketplace.marketplace.dto.OrderDto;
import com.edu.kpi.marketplace.marketplace.service.OrderService;
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
    public List<OrderDto> getByUserId(
            @PathVariable String userId
    ) {
        return service.findByUserId(userId);
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderDto orderDto) {
        return service.create(orderDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
