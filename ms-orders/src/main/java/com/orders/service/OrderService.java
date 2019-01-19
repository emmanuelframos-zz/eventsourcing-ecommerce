package com.orders.service;

import com.orders.api.dto.OrderDTO;
import com.orders.api.dto.OrderFilterDTO;
import com.orders.api.dto.OrderItemDTO;
import com.orders.domain.Order;
import com.orders.domain.OrderItemStatus;
import com.orders.domain.OrderStatus;
import com.orders.parser.OrderParser;
import com.orders.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderParser orderParser;
    private final OrderRepository orderRepository;

    public OrderService(OrderParser orderParser, OrderRepository orderRepository) {
        this.orderParser = orderParser;
        this.orderRepository = orderRepository;
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

    public String create(OrderDTO orderDTO){
        Order order = orderParser.toDomain(orderDTO);
        return orderRepository.save(order).getId().toHexString();
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