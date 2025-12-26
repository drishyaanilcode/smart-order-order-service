package com.smartorder.order_service.service;
import org.springframework.stereotype.Service;

    @Service
    public class OrderService {

        public String healthStatus() {
            return "Order Service is running";
        }
    }
