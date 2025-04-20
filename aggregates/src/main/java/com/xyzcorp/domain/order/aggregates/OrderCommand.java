package com.xyzcorp.domain.order.aggregates;


public sealed interface OrderCommand permits AddOrderItem, SubmitOrder, CancelOrder {
}
