package com.smartorder.order_service.repository;

import com.smartorder.order_service.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

