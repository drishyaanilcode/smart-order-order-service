package com.smartorder.order_service.controller;

import com.smartorder.order_service.dto.OrderRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/health")
    public String health() {
        return "Order Service is UP";
    }
    @PostMapping
    public String createOrder(@RequestBody OrderRequest request) {
        return "Order received for product "
                + request.getProductId()
                + " with quantity "
                + request.getQuantity();
    }
}
