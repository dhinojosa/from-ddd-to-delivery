package com.xyzcorp.domain.order.aggregates;


import java.time.LocalDateTime;

public record OrderCreated(OrderId orderId, LocalDateTime now) implements OrderEvent {
}
