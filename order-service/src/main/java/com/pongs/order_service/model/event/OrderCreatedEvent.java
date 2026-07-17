package com.pongs.order_service.model.event;

public class OrderCreatedEvent {

    private Long orderId;
    private String productId;
    private Integer quantity;

    public OrderCreatedEvent() {}

    public static OrderCreatedEvent of(Long orderId, String productId, Integer quantity) {
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.orderId = orderId;
        event.productId = productId;
        event.quantity = quantity;
        return event;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}