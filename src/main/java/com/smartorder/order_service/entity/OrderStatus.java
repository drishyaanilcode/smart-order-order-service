package com.smartorder.order_service.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
public enum OrderStatus {
    CREATED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
