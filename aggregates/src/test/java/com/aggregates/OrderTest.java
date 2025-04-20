package com.aggregates;

import com.xyzcorp.domain.order.aggregates.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderTest {
    @Test
    void testCreateOrderAggregate() {
        Order order = Order.of(new OrderId(10202L), new CustomerId(1000L));
        order.execute(new AddOrderItem(new ProductId(1000L), 10, 100));
        order.execute(new AddOrderItem(new ProductId(1020L), 4, 10));
        order.execute(new AddOrderItem(new ProductId(1030L), 1, 120));

        order.getOrderEventList().forEach(System.out::println);
    }

    @Test
    void testSubmittedOrderAggregate() {
        Order order = Order.of(new OrderId(10202L), new CustomerId(1000L));
        order.execute(new AddOrderItem(new ProductId(1000L), 10, 100));
        order.execute(new AddOrderItem(new ProductId(1020L), 4, 10));
        order.execute(new AddOrderItem(new ProductId(1030L), 1, 120));
        order.getOrderEventList().forEach(System.out::println);

        order.execute(new SubmitOrder());
        OrderEvent state = order.getState();
        assertThat(state).isInstanceOf(OrderSubmitted.class);
    }

    @Test
    void testACanceledOrderCanNoLongerBeSubmitted() {
        Order order = Order.of(new OrderId(10202L), new CustomerId(1000L));
        order.execute(new AddOrderItem(new ProductId(1000L), 10, 100));
        order.execute(new AddOrderItem(new ProductId(1020L), 4, 10));
        order.execute(new AddOrderItem(new ProductId(1030L), 1, 120));
        order.execute(new CancelOrder());
        assertThatThrownBy(() -> order.execute(new SubmitOrder()))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(Order.CANCELED_STATEMENT);
    }
}
