package com.xyzcorp.order.appservice;

import com.xyzcorp.domain.order.aggregates.CustomerId;
import com.xyzcorp.domain.order.aggregates.Order;
import com.xyzcorp.domain.order.aggregates.OrderId;
import com.xyzcorp.domain.order.appservice.OrderApplicationService;
import com.xyzcorp.domain.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderApplicationServiceTest {

    @Test
    void testCreationOfOrder() {
        OrderRepository orderRepositoryStub = new OrderRepository() {
            Map<OrderId, Order> orders = new HashMap<>();

            @Override
            public void submit(Order order) {
                this.orders.put(order.getOrderId(), order);
            }

            @Override
            public Order getById(OrderId orderId) {
                return this.orders.get(orderId);
            }
        };

        OrderApplicationService orderApplicationService = new OrderApplicationService(orderRepositoryStub, null);
        orderApplicationService.createOrder(Order.of(new OrderId(1L), new CustomerId(1L)));
        assertNotNull(orderRepositoryStub.getById(new OrderId(1L)));
    }
}
