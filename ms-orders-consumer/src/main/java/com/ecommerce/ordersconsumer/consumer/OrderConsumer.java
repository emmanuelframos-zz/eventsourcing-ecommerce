package com.ecommerce.ordersconsumer.consumer;

import com.google.gson.Gson;
import com.ecommerce.orderscontract.dto.OrderDTO;
import com.ecommerce.ordersconsumer.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderService orderService;
    private final Gson gson;

    public OrderConsumer(OrderService orderService, Gson gson) {
        this.orderService = orderService;
        this.gson = gson;
    }


    @RabbitListener(queues="${rabbitmq.order.queue.name}")
    public void receivedMessage(String order) {
        logger.info("Consuming order {} ", order);
        orderService.create(gson.fromJson(order, OrderDTO.class));
    }
}