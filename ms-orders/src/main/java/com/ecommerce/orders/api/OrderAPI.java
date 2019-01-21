package com.ecommerce.orders.api;

import com.ecommerce.orderscontract.dto.OrderDTO;
import com.ecommerce.orderscontract.dto.OrderFilterDTO;
import com.ecommerce.orderscontract.dto.OrderItemDTO;
import com.ecommerce.orders.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ordersconsumer",
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
    public void create(@RequestBody OrderDTO orderDTO){
        orderService.create(orderDTO);
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
