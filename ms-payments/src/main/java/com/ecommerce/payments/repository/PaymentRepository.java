package com.ecommerce.payments.repository;

import com.ecommerce.payments.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment,String> {

    List<Payment> findByOrderId(String orderId);

}
