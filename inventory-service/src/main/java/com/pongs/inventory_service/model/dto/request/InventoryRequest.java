package com.pongs.inventory_service.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InventoryRequest {

    @NotBlank(message = "Product ID cannot be null")
    private String productId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    public String getProductId() {return productId;}
    public void setProductId(String productId) {this.productId = productId;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

}
