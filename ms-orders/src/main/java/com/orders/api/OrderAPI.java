package com.orders.api;

import com.orders.api.dto.OrderDTO;
import com.orders.api.dto.OrderFilterDTO;
import com.orders.api.dto.OrderItemDTO;
import com.orders.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderAPI {

    private final OrderService orderService;

    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> findByFilter(OrderFilterDTO orderFilterDTO){
        return orderService.findByFilter(orderFilterDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody OrderDTO orderDTO){
        return orderService.create(orderDTO);
    }

    @PutMapping("/{id}/refund")
    @ResponseStatus(HttpStatus.OK)
    public void refundOrder(@PathVariable("id") ObjectId id){
        orderService.refundOrder(id);
    }

    @PutMapping("/{id}/refundItems")
    @ResponseStatus(HttpStatus.OK)
    public void refundOrderItems(@PathVariable("id") ObjectId id, @RequestBody List<OrderItemDTO> orderItems){
        orderService.refundOrderItems(id, orderItems);
    }
}
