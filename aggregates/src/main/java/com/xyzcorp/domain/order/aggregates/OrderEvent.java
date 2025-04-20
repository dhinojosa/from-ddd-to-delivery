package com.xyzcorp.domain.order.aggregates;


public sealed interface OrderEvent permits
    OrderCreated, OrderCanceled, OrderSubmitted, OrderItemAdded {
}
