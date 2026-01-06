package com.smartorder.order_service.service;
import com.smartorder.order_service.dto.OrderRequest;
import com.smartorder.order_service.dto.OrderDTO;
import com.smartorder.order_service.entity.Order;
import com.smartorder.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
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

        // Read by ID
        public OrderDTO getOrderById(Long id) {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
            return mapToDTO(order);
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
        // Update
        public OrderDTO updateOrder(Long id, OrderRequest request) {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
            order.setProductName(request.getProductName());
            order.setQuantity(request.getQuantity());
            Order updated = orderRepository.save(order);
            return mapToDTO(orderRepository.save(order));

        }

        // Read orders
        public List<OrderDTO> getAllOrders() {
            return orderRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }
        // Delete
        public void deleteOrder(Long id) {
            if (!orderRepository.existsById(id)) {
                throw new RuntimeException("Order not found with id: " + id);
            }
            orderRepository.deleteById(id);
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
