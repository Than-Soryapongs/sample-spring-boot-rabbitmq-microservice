package com.pongs.inventory_service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pongs.inventory_service.model.entity.Inventory;

@Mapper
public interface InventoryMapper {
    Inventory findByProductId(String productId);
    void insert(Inventory inventory);
    void deductStock(String productId, Integer quantity);
}
