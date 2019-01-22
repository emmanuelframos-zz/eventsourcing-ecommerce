package com.ecommerce.payments.parser;

import com.ecommerce.payments.api.dto.PaymentDTO;
import com.ecommerce.payments.domain.Payment;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class PaymentParser {

    public Payment toDomain(PaymentDTO paymentDTO){
        Payment payment = new Payment();
        payment.setId(paymentDTO.id == null ? null : new ObjectId(paymentDTO.id));
        payment.setStatus(paymentDTO.status);
        payment.setDate(paymentDTO.date);
        payment.setCreditCardNumber(paymentDTO.creditCardNumber);
        payment.setOrderId(paymentDTO.orderId);
        return payment;
    }

    public PaymentDTO toDTO(Payment payment){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.id = (payment.getId() == null ? null : payment.getId().toHexString());
        paymentDTO.status = payment.getStatus();
        paymentDTO.date = payment.getDate();
        paymentDTO.creditCardNumber = payment.getCreditCardNumber();
        paymentDTO.orderId = payment.getOrderId();
        return paymentDTO;
    }
}
