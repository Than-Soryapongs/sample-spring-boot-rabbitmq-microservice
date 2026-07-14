package com.pongs.inventory_service.service;

import org.springframework.stereotype.Service;

import com.pongs.inventory_service.mapper.InventoryMapper;
import com.pongs.inventory_service.model.entity.Inventory;
import com.pongs.inventory_service.exception.ProductAlreadyExistsException;

@Service
public class InventoryService {
    private final InventoryMapper inventoryMapper;

    public InventoryService(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    public Inventory getStock(String productId) {
        return inventoryMapper.findByProductId(productId);
    }

    public boolean deductStock(String productId, Integer quantity) {
        
        Inventory inventory = inventoryMapper.findByProductId(productId);

        if (inventory == null) {
            System.out.println("[Inventory] Product not found: " + productId);
            return false;
        }

        if (inventory.getQuantity() < quantity) {
            System.out.println("[Inventory] Insufficient stock for: " + productId
                + " | available: " + inventory.getQuantity()
                + " | requested: " + quantity);
            return false;
        }

        inventoryMapper.deductStock(productId, quantity);
        System.out.println("[Inventory] Stock deducted for: " + productId
            + " | deducted: " + quantity
            + " | remaining: " + (inventory.getQuantity() - quantity));

        return true;
    }

    public Inventory addProduct(String productId, Integer quantity) {
        Inventory inventory = inventoryMapper.findByProductId(productId);

        if (inventory != null) {
            throw new ProductAlreadyExistsException(productId);
        }

        System.out.println("[Inventory] Product not found: " + productId + ". Creating new inventory record.");
        inventory = new Inventory();
        inventory.setProductId(productId);
        inventory.setQuantity(quantity);
        inventoryMapper.insert(inventory);

        return inventoryMapper.findByProductId(productId);
    }

    
}
