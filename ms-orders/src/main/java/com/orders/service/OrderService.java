package com.orders.service;

import com.orders.api.dto.OrderDTO;
import com.orders.domain.Order;
import com.orders.parser.OrderParser;
import com.orders.repository.OrderItemRepository;
import com.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderParser orderParser;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderParser orderParser, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderParser = orderParser;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDTO findByFilter(){
        return null;
    }

    public String create(OrderDTO orderDTO){
        Order order = orderParser.toDomain(orderDTO);

        orderItemRepository.saveAll(order.getOrderItems());

        order = orderRepository.save(order);

        return order.getId().toHexString();
    }

    public void refund(){

    }
}