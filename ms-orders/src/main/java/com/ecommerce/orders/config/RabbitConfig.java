package com.ecommerce.orders.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.order.queue.name}")
    private String orderQueue;

    @Value("${rabbitmq.order.exchange.name}")
    private String orderExchange;

    @Value("${rabbitmq.order.routingKey.name}")
    private String orderRoutingKey;

    @Bean
    public Queue queue() {
        return new Queue(orderQueue, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(orderExchange);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(orderRoutingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }
}