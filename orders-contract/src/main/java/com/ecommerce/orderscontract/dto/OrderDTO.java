package com.ecommerce.orderscontract.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ecommerce.orderscontract.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    public String id;
    public String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy@HH:mm:ss")
    public LocalDateTime confirmationDate;
    public OrderStatus status;
    public List<OrderItemDTO> orderItems = new ArrayList<>();

}