package com.ecommerce.ordersconsumer.service;

import com.ecommerce.ordersconsumer.repository.OrderRepository;
import com.ecommerce.orderscontract.domain.Order;
import com.ecommerce.orderscontract.dto.OrderDTO;
import com.ecommerce.orderscontract.parser.OrderParser;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.ecommerce.orderscontract.parser")
public class OrderService {

    private final OrderParser orderParser;
    private final OrderRepository orderRepository;

    public OrderService(OrderParser orderParser, OrderRepository orderRepository) {
        this.orderParser = orderParser;
        this.orderRepository = orderRepository;
    }

    public String create(OrderDTO orderDTO){
        Order order = orderParser.toDomain(orderDTO);
        return orderRepository.save(order).getId().toHexString();
    }
}