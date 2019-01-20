package com.orders;


import com.google.gson.Gson;
import com.orders.consumer.OrderConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.orders.contract.parser"})
@ComponentScan({"com.orders.consumer"})
public class Application {

   @Autowired
   private OrderConsumer orderConsumer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }
}
