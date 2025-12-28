package com.smartorder.order_service.service;
import com.smartorder.order_service.dto.OrderRequest;
import com.smartorder.order_service.dto.OrderDTO;

import com.smartorder.order_service.entity.Order;
import com.smartorder.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



    @Service
    public class OrderService {
        private final OrderRepository orderRepository;

        public OrderService(OrderRepository orderRepository) {
            this.orderRepository = orderRepository;
        }

        // Health check
        public String healthStatus() {
            return "Order Service is running";
        }

        // Create order
        public OrderDTO createOrder(OrderRequest request) {
            Order order = new Order();
            order.setProductName(request.getProductName());
            order.setQuantity(request.getQuantity());
            order.setStatus("CREATED");

            Order savedOrder = orderRepository.save(order);

            return mapToDTO(savedOrder);
        }

        // Get all orders
        public List<OrderDTO> getAllOrders() {
            return orderRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }

        // Entity → DTO
        private OrderDTO mapToDTO(Order order) {
            return new OrderDTO(
                    order.getId(),
                    order.getProductName(),
                    order.getQuantity(),
                    order.getStatus()
            );
        }
    }
}