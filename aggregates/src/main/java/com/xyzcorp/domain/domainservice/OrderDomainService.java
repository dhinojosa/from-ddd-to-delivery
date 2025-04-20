package com.xyzcorp.domain.domainservice;


import com.xyzcorp.domain.customer.Customer;
import com.xyzcorp.domain.order.aggregates.Order;

public class OrderDomainService {

    /* In this section, I am using both a customer, and an order aggregate */
    public static void checkCredit(Order order, Customer customer) {
        if (order.total() > customer.getCreditLimit()) {
            throw new IllegalStateException("Customer credit limit exceeded");
        }
    }
}
