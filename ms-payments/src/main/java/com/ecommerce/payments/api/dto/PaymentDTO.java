package com.ecommerce.payments.api.dto;

import com.ecommerce.payments.domain.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PaymentDTO {

    public String id;
    public PaymentStatus status;
    public String creditCardNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy@HH:mm:ss")
    public LocalDateTime date;
    public String orderId;

}
