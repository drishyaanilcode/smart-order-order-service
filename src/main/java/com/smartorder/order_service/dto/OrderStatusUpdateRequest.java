package com.smartorder.order_service.dto;
import com.smartorder.order_service.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public class OrderStatusUpdateRequest {

        @NotNull(message = "Status is required")
        private OrderStatus status;

        public OrderStatusUpdateRequest() {}

        public OrderStatusUpdateRequest(OrderStatus status) {
            this.status = status;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public void setStatus(OrderStatus status) {
            this.status = status;
        }
    }


