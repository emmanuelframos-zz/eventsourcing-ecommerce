package com.ecommerce.ordersconsumer.repository;

import com.ecommerce.orderscontract.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> { }