package com.xyzcorp.domain.order.aggregates;


public record OrderCanceled(java.time.LocalDateTime order) implements OrderEvent {
}
