package com.orders.api.dto;

import com.orders.domain.OrderItemStatus;

import java.math.BigDecimal;

public class OrderItemDTO {

    public String code;
    public String description;
    public BigDecimal unitPrice;
    public Integer quantity;
    public OrderItemStatus status;

}
