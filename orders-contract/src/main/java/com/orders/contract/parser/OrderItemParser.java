package com.orders.contract.parser;

import com.orders.contract.dto.OrderItemDTO;
import com.orders.contract.domain.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemParser {

    public OrderItem toDomain(OrderItemDTO orderItemDTO){
        OrderItem orderItem = new OrderItem();
        orderItem.setDescription(orderItemDTO.description);
        orderItem.setQuantity(orderItemDTO.quantity);
        orderItem.setUnitPrice(orderItemDTO.unitPrice);
        orderItem.setCode(orderItemDTO.code);
        orderItem.setStatus(orderItemDTO.status);
        return  orderItem;
    }

    public OrderItemDTO toDTO(OrderItem orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.description = orderItem.getDescription();
        orderItemDTO.quantity = orderItem.getQuantity();
        orderItemDTO.unitPrice = orderItem.getUnitPrice();
        orderItemDTO.code = orderItem.getCode();
        orderItemDTO.status = orderItem.getStatus();
        return  orderItemDTO;
    }
}