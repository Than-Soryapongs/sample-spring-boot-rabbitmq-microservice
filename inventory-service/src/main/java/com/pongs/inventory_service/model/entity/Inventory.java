package com.pongs.inventory_service.model.entity;

public class Inventory {
    private Long id;
    private String productId;
    private Integer quantity;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getProductId() {return productId;}
    public void setProductId(String productId) {this.productId = productId;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}
}
