package com.xyzcorp.domain.order.repository;


import com.xyzcorp.domain.order.aggregates.Order;
import com.xyzcorp.domain.order.aggregates.OrderId;

public interface OrderRepository {
    public void submit(Order order);
    public Order getById(OrderId orderId);
}


