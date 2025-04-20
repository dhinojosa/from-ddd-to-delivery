package com.xyzcorp.domain.order.aggregates;


public record OrderItem(ProductId productId,
                        int quantity, int price) {}
