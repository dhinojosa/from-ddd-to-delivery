package com.xyzcorp.domain.order.aggregates;


public record OrderSubmitted(java.time.LocalDateTime order) implements OrderEvent {
}
