package com.pongs.order_service.model.event;

public class StockReservationResult {

    private Long orderId;
    private boolean success;

    public StockReservationResult() {}

    public static StockReservationResult of(Long orderId, boolean success) {
        StockReservationResult result = new StockReservationResult();
        result.orderId = orderId;
        result.success = success;
        return result;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}