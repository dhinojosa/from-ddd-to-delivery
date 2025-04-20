package com.xyzcorp.adapter.out.order;


import com.xyzcorp.domain.order.aggregates.Order;
import com.xyzcorp.domain.order.aggregates.OrderId;
import com.xyzcorp.domain.order.repository.OrderRepository;

public class KafkaOrderRepository implements OrderRepository {
    @Override
    public void submit(Order order) {
       order.getOrderEventList().forEach(oe ->
           System.out.printf("Sending %s to Kafka", oe));

    }

    @Override
    public Order getById(OrderId orderId) {
        return null;
    }
}
