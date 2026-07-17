package com.pongs.order_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.pongs.order_service.config.RabbitMQConfig;
import com.pongs.order_service.model.event.StockReservationResult;
import com.pongs.order_service.service.OrderService;

@Component
public class OrderResultListener {

    private final OrderService orderService;

    public OrderResultListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_RESULT_QUEUE)
    public void handleStockReservationResult(StockReservationResult result) {
        String newStatus = result.isSuccess() ? "CONFIRMED" : "CANCELLED";
        orderService.updateStatus(result.getOrderId(), newStatus);

        System.out.println("[Order] Order " + result.getOrderId() + " updated to " + newStatus);
    }
}