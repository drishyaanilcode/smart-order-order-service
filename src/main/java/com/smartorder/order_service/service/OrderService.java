package com.smartorder.order_service.service;
import com.smartorder.order_service.dto.OrderRequest;
import com.smartorder.order_service.dto.OrderDTO;
import com.smartorder.order_service.entity.Order;
import com.smartorder.order_service.entity.OrderStatus;
import com.smartorder.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.smartorder.order_service.exception.ResourceNotFoundException;
import java.util.List;

    @Service
    public class OrderService {
        private final OrderRepository orderRepository;

        public OrderService(OrderRepository orderRepository) {

            this.orderRepository = orderRepository;
        }

        // Read by ID
       public OrderDTO getOrderById(Long id) {
           Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
            return mapToDTO(order);
        }
        // Create order
        public OrderDTO createOrder(OrderRequest request) {
            if (request.getProductName() == null || request.getProductName().isBlank()) {
                throw new IllegalArgumentException("Product name must not be empty");
            }

            if (request.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }
            Order order = new Order();
            order.setProductName(request.getProductName());
            order.setQuantity(request.getQuantity());
            order.setStatus(OrderStatus.CREATED);
            return mapToDTO(orderRepository.save(order));
        }
        // Update
        @Transactional
            public OrderDTO updateOrderStatus(Long id, OrderStatus newStatus) {
            Order order = orderRepository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
            if (!isValidStatusChange(order.getStatus(), newStatus)) {
                throw new IllegalStateException(
                        "Invalid status transition from " + order.getStatus() + " to " + newStatus
                );
            }
            order.setStatus(newStatus);
            return mapToDTO(order);
        }

        // Read orders
        public List<OrderDTO> getAllOrders() {
            return orderRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }
        // Delete
       @Transactional
       public void deleteOrder(Long id) {
           if (!orderRepository.existsById(id)) {
                throw new ResourceNotFoundException("Order not found with id: " + id);
            }
            orderRepository.deleteById(id);
        }

        // Entity → DTO
        private OrderDTO mapToDTO(Order order) {
            return new OrderDTO(
                    order.getId(),
                    order.getProductName(),
                    order.getQuantity(),
                    order.getStatus().name()
            );
        }

        private boolean isValidStatusChange(OrderStatus current, OrderStatus next) {

            if (current == OrderStatus.CREATED) {
                return next == OrderStatus.CONFIRMED || next == OrderStatus.CANCELLED;
            }

            if (current == OrderStatus.CONFIRMED) {
                return next == OrderStatus.SHIPPED || next == OrderStatus.CANCELLED;
            }

            if (current == OrderStatus.SHIPPED) {
                return next == OrderStatus.DELIVERED;
            }

            return false;
        }

    }
