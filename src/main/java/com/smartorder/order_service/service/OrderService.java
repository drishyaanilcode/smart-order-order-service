package com.smartorder.order_service.service;
import com.smartorder.order_service.dto.OrderRequest;
import com.smartorder.order_service.dto.OrderDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
    public class OrderService {
    private final List<OrderDTO> orders = new ArrayList<>();
    private Long currentId = 1L;

    public String healthStatus() {
        return "Order Service is running";
    }

    public OrderDTO createOrder(OrderRequest request) {
        OrderDTO order = new OrderDTO(
                currentId++,
                request.getProductName(),
                request.getQuantity(),
                "CREATED"
        );
        orders.add(order);
        return order;
    }

    public List<OrderDTO> getAllOrders() {
        return orders;
    }
}