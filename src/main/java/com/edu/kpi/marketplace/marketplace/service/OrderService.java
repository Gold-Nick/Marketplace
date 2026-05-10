package com.edu.kpi.marketplace.marketplace.service;

import com.edu.kpi.marketplace.marketplace.dto.OrderDto;
import com.edu.kpi.marketplace.marketplace.dto.OrderItemDto;
import com.edu.kpi.marketplace.marketplace.model.Order;
import com.edu.kpi.marketplace.marketplace.model.OrderItem;
import com.edu.kpi.marketplace.marketplace.model.OrderStatus;
import com.edu.kpi.marketplace.marketplace.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public List<OrderDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public OrderDto findById(String id) {
        return toDto(repository.findById(id).orElseThrow());
    }

    public List<OrderDto> findByUserId(String userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = toEntity(orderDto);
        order.setCreatedAt(Instant.now());

        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.PENDING);
        }
        double total = order.getItems().stream()
                .mapToDouble(i -> i.getPriceAtPurchase() * i.getQuantity())
                .sum();

        order.setTotalSum(total);
        return toDto(repository.save(order));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    private Order toEntity(OrderDto dto) {
        if (dto == null) return null;

        return Order.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .status(dto.getStatus())
                .items(dto.getItems() != null
                        ? dto.getItems().stream()
                          .map(this::mapItem)
                          .toList()
                        : null)
                .build();
    }

    private OrderDto toDto(Order entity) {
        if (entity == null) return null;

        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setStatus(entity.getStatus());
        dto.setTotalSum(entity.getTotalSum());

        dto.setItems(entity.getItems() != null
                ? entity.getItems().stream()
                  .map(this::mapItem)
                  .toList()
                : null);

        return dto;
    }

    private OrderItem mapItem(OrderItemDto dto) {
        OrderItem item = new OrderItem();
        item.setProductId(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        return item;
    }

    private OrderItemDto mapItem(OrderItem entity) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductId(entity.getProductId());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
}
