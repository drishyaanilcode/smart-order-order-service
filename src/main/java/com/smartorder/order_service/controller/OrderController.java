package com.smartorder.order_service.controller;

import com.smartorder.order_service.dto.OrderRequest;
import org.springframework.web.bind.annotation.*;
import com.smartorder.order_service.service.OrderService;
import com.smartorder.order_service.dto.OrderRequest;
import com.smartorder.order_service.dto.OrderDTO;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Read by ID
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    //Create
    @PostMapping("/order")
    public OrderDTO createOrder(@RequestBody OrderRequest request) {
        System.out.println("Received OrderRequest: " + request.getProductName() + ", " + request.getQuantity());
        OrderDTO saved = orderService.createOrder(request);
        System.out.println("Saved OrderDTO: " + saved.getId());
        return saved;
    }
    // Update
    @PutMapping("order/{id}")
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        return orderService.updateOrder(id, request);
    }

    //Read
    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
    // Delete
    @DeleteMapping("order/{id}")
    public Map<String, String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return Map.of("message", "Order deleted successfully");
    }
    //Health
    @GetMapping("/health")
    public String health() {
        return orderService.healthStatus();
    }
    @GetMapping("/health-json")
    public Map<String, String> healthJson() {
        return Map.of("status", orderService.healthStatus(), "env", "local");
    }
}

