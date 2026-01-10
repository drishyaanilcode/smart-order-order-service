package com.smartorder.order_service.controller;

import com.smartorder.order_service.dto.OrderRequest;
import com.smartorder.order_service.dto.OrderStatusUpdateRequest;
import org.springframework.web.bind.annotation.*;
import com.smartorder.order_service.service.OrderService;
import com.smartorder.order_service.dto.OrderDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
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
    @PostMapping
    public OrderDTO createOrder(@Valid @RequestBody OrderRequest request) {
        System.out.println("Received OrderRequest: " + request.getProductName() + ", " + request.getQuantity());
        OrderDTO saved = orderService.createOrder(request);
        return saved;
    }

    // Update
    @PatchMapping("/{id}/status")
    public OrderDTO updateOrderStatus(@PathVariable Long id, @Valid @RequestBody OrderStatusUpdateRequest request) {
        return orderService.updateOrderStatus(id, request.getStatus());
    }

    //Read
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

   // Delete
    @DeleteMapping("/{id}")
   public Map<String, String> deleteOrder(@PathVariable Long id) {
       orderService.deleteOrder(id);
       return Map.of("message", "Order deleted successfully");
   }
}

