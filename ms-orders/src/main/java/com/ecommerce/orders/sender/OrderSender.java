package com.ecommerce.orders.sender;

import com.google.gson.Gson;
import com.ecommerce.orderscontract.dto.OrderDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final Gson gson;

    public OrderSender(RabbitTemplate rabbitTemplate, Queue queue, Gson gson) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.gson = gson;
    }

    public void send(OrderDTO orderDTO) {
        rabbitTemplate.convertAndSend(this.queue.getName(), gson.toJson(orderDTO, OrderDTO.class));
    }
}
