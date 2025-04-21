package com.xyzcorp.domain.order.appservice;


import com.xyzcorp.domain.customer.Customer;
import com.xyzcorp.domain.order.repository.CustomerRepository;
import com.xyzcorp.domain.order.aggregates.*;
import com.xyzcorp.domain.domainservice.OrderDomainService;
import com.xyzcorp.domain.order.repository.OrderRepository;

public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderApplicationService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public void createOrder(Order order) {
        Customer customer = customerRepository.getCustomer(order.getCustomerId());
        OrderDomainService.checkCredit(order, customer);
        //transaction
        orderRepository.submit(order);
        //commit transaction
        order.getOrderEventList().forEach(System.out::println);
    }

    public void addOrderItem(OrderId orderId, OrderItem orderItem) {
        Order order = orderRepository.getById(orderId);
        order.execute(new AddOrderItem(new ProductId(120L), 20, 1120));
    }
}
