package com.xyzcorp.domain.order.aggregates;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/// Aggregate for Order
public class Order {
    public static final String CANCELED_STATEMENT = "You can't submit a canceled order";
    private OrderId orderId;
    private List<OrderItem> orderItemList;
    private List<OrderEvent> orderEventList;
    private CustomerId customer;


    protected Order(OrderId orderId, CustomerId customer, ArrayList<OrderEvent> orderEventList) {
        this.orderId = orderId;
        this.orderItemList = new ArrayList<>();
        this.orderEventList = orderEventList;
        this.customer = customer;
    }

    public static Order of(OrderId orderId, CustomerId customer) {
        ArrayList<OrderEvent> orderEventList = new ArrayList<>();
        orderEventList.add(new OrderCreated(orderId, LocalDateTime.now()));
        return new Order(orderId, customer, orderEventList);
    }

    public void execute(OrderCommand command) {
        switch(command) {
            case CancelOrder cancelOrder -> {
                orderEventList.add(new OrderCanceled(LocalDateTime.now()));
            }
            case SubmitOrder submitOrder -> {
                switch (getState()) {
                    case OrderCanceled orderCanceled ->
                        throw new IllegalStateException(CANCELED_STATEMENT);
                    default ->
                        orderEventList.add(new OrderSubmitted(LocalDateTime.now()));
                }
            }
            case AddOrderItem addOrderItem -> {
                OrderItem orderItem = new OrderItem(
                    addOrderItem.productId(), addOrderItem.quantity(), addOrderItem.price());
                orderItemList.add(orderItem);
                orderEventList.add(new OrderItemAdded(this.orderId, orderItem));
            }
        }
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public List<OrderEvent> getOrderEventList() {
        return orderEventList;
    }

    public OrderEvent getState() {
        return orderEventList.getLast();
    }

    public CustomerId getCustomerId() {
        return null;
    }

    public int total() {
        return 0;
    }
}
