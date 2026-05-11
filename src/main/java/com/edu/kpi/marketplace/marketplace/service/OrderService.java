package com.edu.kpi.marketplace.marketplace.service;

import com.edu.kpi.marketplace.marketplace.dto.OrderDto;
import com.edu.kpi.marketplace.marketplace.dto.OrderItemDto;
import com.edu.kpi.marketplace.marketplace.model.Order;
import com.edu.kpi.marketplace.marketplace.model.OrderItem;
import com.edu.kpi.marketplace.marketplace.model.OrderStatus;
import com.edu.kpi.marketplace.marketplace.model.Product;
import com.edu.kpi.marketplace.marketplace.repository.OrderRepository;
import com.edu.kpi.marketplace.marketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderDto create(OrderDto orderDto) {

        Order order = toEntity(orderDto);

        order.setCreatedAt(Instant.now());
        order.setStatus(OrderStatus.PENDING);

        double total = 0.0;

        for (OrderItem item : order.getItems()) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new IllegalStateException(
                        "Not enough stock for product: " + product.getId()
                );
            }

            double price = product.getPrice();
            item.setPriceAtPurchase(price);

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            total += price * item.getQuantity();
        }

        order.setTotalSum(total);

        return toDto(repository.save(order));
    }

    private boolean isValidTransition(OrderStatus current, OrderStatus next) {

        if (current == null || next == null) {
            return false;
        }

        return switch (current) {

            case PENDING -> next == OrderStatus.SHIPPED ||
                    next == OrderStatus.CANCELLED;

            case SHIPPED -> next == OrderStatus.COMPLETED;

            case COMPLETED, CANCELLED -> false;
        };
    }

    public OrderDto updateStatus(String orderId, OrderStatus newStatus) {

        Order order = repository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));

        OrderStatus currentStatus = order.getStatus();

        if (!isValidTransition(currentStatus, newStatus)) {
            throw new IllegalStateException(
                    "Invalid status transition from "
                            + currentStatus +
                            " to " +
                            newStatus
            );
        }

        order.setStatus(newStatus);

        return toDto(repository.save(order));
    }

    public List<OrderDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public OrderDto findById(String id) {
        return toDto(repository.findById(id).orElseThrow());
    }

    public List<OrderDto> findByUserId(String userId) {
        return repository.findByUserId(userId).stream().map(this::toDto).toList();
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
