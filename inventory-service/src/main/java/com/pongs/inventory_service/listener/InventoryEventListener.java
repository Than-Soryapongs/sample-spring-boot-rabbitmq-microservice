package com.pongs.inventory_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.pongs.inventory_service.config.RabbitMQConfig;
import com.pongs.inventory_service.model.event.OrderCreatedEvent;
import com.pongs.inventory_service.model.event.StockReservationResult;
import com.pongs.inventory_service.service.InventoryService;

@Component
public class InventoryEventListener {

    private final InventoryService inventoryService;
    private final RabbitTemplate rabbitTemplate;

    public InventoryEventListener(InventoryService inventoryService, RabbitTemplate rabbitTemplate) {
        this.inventoryService = inventoryService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.INVENTORY_QUEUE)
    public void handleOrderCreated(OrderCreatedEvent event) {
        boolean success = inventoryService.deductStock(event.getProductId(), event.getQuantity());

        StockReservationResult result = StockReservationResult.of(event.getOrderId(), success);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.ORDER_RESULT_ROUTING_KEY,
                result);

        System.out.println("[Inventory] Reservation for order " + event.getOrderId()
                + " -> " + (success ? "SUCCESS" : "FAILED") + ", result published.");
    }
}