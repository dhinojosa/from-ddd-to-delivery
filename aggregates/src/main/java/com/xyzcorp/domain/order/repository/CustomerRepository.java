package com.xyzcorp.domain.order.repository;


import com.xyzcorp.domain.customer.Customer;
import com.xyzcorp.domain.order.aggregates.CustomerId;

public interface CustomerRepository {
    Customer getCustomer(CustomerId customerId);
}
