package com.pongs.inventory_service.model.dto.response;

import com.pongs.inventory_service.model.entity.Inventory;

public record InventoryResponse (
    Long id,
    String productId,
    Integer quantity
) {
    public static InventoryResponse from(Inventory inventory) {
        return new InventoryResponse(
            inventory.getId(),
            inventory.getProductId(),
            inventory.getQuantity()
        );
    }
}
