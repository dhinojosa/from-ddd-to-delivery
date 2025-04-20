package com.xyzcorp.adapter.out.order;


import com.xyzcorp.domain.order.aggregates.Order;
import com.xyzcorp.domain.order.aggregates.OrderId;
import com.xyzcorp.domain.order.repository.OrderRepository;

public class OracleOrderRepository implements OrderRepository {
    @Override
    public void submit(Order order) {
        System.out.println("Saving order to Oracle");
        order.getOrderItemList().forEach(oi ->
            System.out.printf("Storing %s", oi));
    }

    @Override
    public Order getById(OrderId orderId) {
        return null;
    }
}
