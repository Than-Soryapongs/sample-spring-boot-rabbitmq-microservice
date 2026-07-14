package com.pongs.order_service.service;

import org.springframework.stereotype.Service;

import com.pongs.order_service.client.InventoryServiceClient;
import com.pongs.order_service.client.UserServiceClient;
import com.pongs.order_service.exception.InsufficientStockException;
import com.pongs.order_service.exception.UserNotFoundException;
import com.pongs.order_service.mapper.OrderMapper;
import com.pongs.order_service.model.entity.Order;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final UserServiceClient userServiceClient;
    private final InventoryServiceClient inventoryServiceClient;

    public OrderService(OrderMapper orderMapper,
                         UserServiceClient userServiceClient,
                         InventoryServiceClient inventoryServiceClient) {
        this.orderMapper = orderMapper;
        this.userServiceClient = userServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public Order createOrder(String username, String productId, Integer quantity) {

        if (!userServiceClient.userExists(username)) {
            throw new UserNotFoundException("User not found: " + username);
        }

        boolean deducted = inventoryServiceClient.deductStock(productId, quantity);
        if (!deducted) {
            throw new InsufficientStockException("Could not deduct stock for product: " + productId);
        }

        Order order = new Order();
        order.setUsername(username);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus("CONFIRMED");

        orderMapper.insert(order);

        return orderMapper.findById(order.getId());
    }

    public Order getOrder(Long id) {
        return orderMapper.findById(id);
    }
}
