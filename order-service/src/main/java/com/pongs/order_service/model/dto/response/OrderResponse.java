package com.pongs.order_service.model.dto.response;

import java.time.LocalDateTime;

import com.pongs.order_service.model.entity.Order;

public record OrderResponse(
    Long id,
    String username,
    String productId,
    Integer quantity,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getUsername(),
            order.getProductId(),
            order.getQuantity(),
            order.getStatus(),
            order.getCreatedAt(),
            order.getUpdatedAt()
        );
    }
}