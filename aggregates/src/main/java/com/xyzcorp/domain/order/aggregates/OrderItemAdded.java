package com.xyzcorp.domain.order.aggregates;


public record OrderItemAdded(OrderId orderId, OrderItem orderItem) implements OrderEvent {
}
