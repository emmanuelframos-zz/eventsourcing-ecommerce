package com.orders.service;

import com.orders.contract.domain.Order;
import com.orders.contract.domain.OrderItemStatus;
import com.orders.contract.domain.OrderStatus;
import com.orders.contract.dto.OrderDTO;
import com.orders.contract.dto.OrderFilterDTO;
import com.orders.contract.dto.OrderItemDTO;
import com.orders.contract.parser.OrderParser;
import com.orders.sender.OrderSender;
import com.orders.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderSender orderSender;
    private final OrderParser orderParser;
    private final OrderRepository orderRepository;

    public OrderService(OrderParser orderParser, OrderRepository orderRepository, OrderSender orderSender) {
        this.orderParser = orderParser;
        this.orderRepository = orderRepository;
        this.orderSender = orderSender;
    }

    public List<OrderDTO> findByFilter(OrderFilterDTO orderFilterDTO){
        if (orderFilterDTO.hasId()){
            Order order = orderRepository.findById(orderFilterDTO.getId());
            return Arrays.asList(orderParser.toDTO(order));
        }

        if (orderFilterDTO.hasStatus()){
            List<Order> orders = orderRepository.findByStatus(orderFilterDTO.getStatus());
            return orders
                    .stream()
                    .map(orderParser::toDTO)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    public void create(OrderDTO orderDTO){
        orderSender.send(orderDTO);
    }

    public void refundOrder(ObjectId id){
        Order order = orderRepository.findById(id);
        order.setStatus(OrderStatus.REFUNDED);
        order.getOrderItems()
                .stream()
                .forEach(orderItem -> orderItem.setStatus(OrderItemStatus.REFUNDED)
        );
        orderRepository.save(order);
    }

    public void refundOrderItems(ObjectId id, List<OrderItemDTO> orderItems){
        Order order = orderRepository.findById(id);
        order.getOrderItems()
                .stream()
                .forEach(orderItem -> {
                    if (containsOrderItem(orderItem.getCode(), orderItems))
                        orderItem.setStatus(OrderItemStatus.REFUNDED);
                }
        );
        orderRepository.save(order);
    }

    private boolean containsOrderItem(String code, List<OrderItemDTO> orderItems){
        return orderItems
                .stream()
                .anyMatch(orderItemDTO -> orderItemDTO.code.equals(code)
        );
    }
}