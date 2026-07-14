package com.pongs.inventory_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongs.inventory_service.model.dto.request.InventoryRequest;
import com.pongs.inventory_service.model.dto.response.InventoryResponse;
import com.pongs.inventory_service.model.entity.Inventory;
import com.pongs.inventory_service.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PutMapping
    public ResponseEntity<InventoryResponse> deductStock(
            @Valid @RequestBody InventoryRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(InventoryResponse.from(
                    inventoryService.deductStock(request.getProductId().toString(), request.getQuantity())
                        ? inventoryService.getStock(request.getProductId().toString())
                        : null
                ));
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> addStock( @Valid @RequestBody InventoryRequest request) {
                return ResponseEntity.status(HttpStatus.CREATED)
                .body(InventoryResponse.from(
                    inventoryService.addProduct(request.getProductId().toString(), request.getQuantity()) 
                ));
        }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getStock(@PathVariable String productId) {
        Inventory inventory = inventoryService.getStock(productId);
        return ResponseEntity.ok(InventoryResponse.from(inventory));
    }
}
