package com.smartorder.order_service.repository;

import com.smartorder.order_service.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();
    private Long idCounter = 1L;

    public Order save(Order order) {
        order.setId(idCounter++);
        orders.add(order);
        return order;
    }

    public List<Order> findAll() {
        return orders;
    }
}
