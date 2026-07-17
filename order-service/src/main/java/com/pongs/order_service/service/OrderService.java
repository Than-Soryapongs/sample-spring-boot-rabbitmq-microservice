package com.pongs.order_service.service;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.pongs.order_service.client.UserServiceClient;
import com.pongs.order_service.config.RabbitMQConfig;
import com.pongs.order_service.exception.UserNotFoundException;
import com.pongs.order_service.mapper.OrderMapper;
import com.pongs.order_service.model.entity.Order;
import com.pongs.order_service.model.event.OrderCreatedEvent;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final UserServiceClient userServiceClient;
    private final RabbitTemplate rabbitTemplate;

    public OrderService(OrderMapper orderMapper,
                         UserServiceClient userServiceClient,
                         RabbitTemplate rabbitTemplate) {
        this.orderMapper = orderMapper;
        this.userServiceClient = userServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Order createOrder(String username, String productId, Integer quantity) {
        if (!userServiceClient.userExists(username)) {
            throw new UserNotFoundException(username);
        }

        Order order = new Order();
        order.setUsername(username);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        orderMapper.insert(order);

        OrderCreatedEvent event = OrderCreatedEvent.of(order.getId(), productId, quantity);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.INVENTORY_ROUTING_KEY,
                event);

        System.out.println("[Order] Order " + order.getId() + " created with status PENDING, event published.");

        return order;
    }

    public Order getOrder(Long orderId) {
        return orderMapper.findById(orderId);
    }

    public void updateStatus(Long orderId, String status) {
        orderMapper.updateStatus(orderId, status);
    }
}