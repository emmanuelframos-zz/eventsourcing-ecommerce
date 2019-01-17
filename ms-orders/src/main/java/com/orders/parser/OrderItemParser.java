package com.orders.parser;

import com.orders.api.dto.OrderItemDTO;
import com.orders.domain.OrderItem;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class OrderItemParser {

    public OrderItem toDomain(OrderItemDTO orderItemDTO){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDTO.id == null ? null : new ObjectId(orderItemDTO.id));
        orderItem.setDescription(orderItemDTO.description);
        orderItem.setQuantity(orderItemDTO.quantity);
        orderItem.setUnitPrice(orderItemDTO.unitPrice);
        return  orderItem;
    }
}
