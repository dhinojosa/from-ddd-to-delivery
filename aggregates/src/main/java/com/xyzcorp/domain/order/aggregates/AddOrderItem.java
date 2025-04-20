package com.xyzcorp.domain.order.aggregates;


public record AddOrderItem(ProductId productId, int quantity, int price) implements OrderCommand {
}
