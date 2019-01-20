package com.orders.service;

import com.orders.repository.OrderRepository;
import com.orders.contract.domain.Order;
import com.orders.contract.dto.OrderDTO;
import com.orders.contract.parser.OrderParser;
import org.springframework.stereotype.Service;

@Service
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