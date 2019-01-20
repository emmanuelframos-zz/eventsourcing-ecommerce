package com.orders.contract.parser;

import com.orders.contract.dto.OrderDTO;
import com.orders.contract.domain.Order;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderParser {

    private final OrderItemParser orderItemParser;

    public OrderParser(OrderItemParser orderItemParser){
        this.orderItemParser = orderItemParser;
    }

    public Order toDomain(OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.id == null ? null : new ObjectId(orderDTO.id));
        order.setAddress(orderDTO.address);
        order.setConfirmationDate(orderDTO.confirmationDate);
        order.setStatus(orderDTO.status);
        order.setOrderItems(orderDTO.orderItems
                .stream()
                .map(orderItemParser::toDomain)
                .collect(Collectors.toList())
        );
        return order;
    }

    public OrderDTO toDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.id = order.getId().toHexString();
        orderDTO.address = order.getAddress();
        orderDTO.confirmationDate = order.getConfirmationDate();
        orderDTO.status = order.getStatus();
        orderDTO.orderItems = order.getOrderItems()
                .stream()
                .map(orderItemParser::toDTO)
                .collect(Collectors.toList());
        return orderDTO;
    }
}