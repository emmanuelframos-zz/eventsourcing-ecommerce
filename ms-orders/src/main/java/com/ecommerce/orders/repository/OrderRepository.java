package com.ecommerce.orders.repository;

import com.ecommerce.orderscontract.domain.Order;
import com.ecommerce.orderscontract.domain.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Order findById(ObjectId id);

    List<Order> findByStatus(OrderStatus orderStatus);

}