package com.orders.api;

import com.orders.api.dto.OrderDTO;
import com.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/orders",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderAPI {

    private final OrderService orderService;

    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderDTO findByFilter(){
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody OrderDTO orderDTO){
        return orderService.create(orderDTO);
    }

    public void refund(){

    }
}
