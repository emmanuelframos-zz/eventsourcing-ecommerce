package com.ecommerce.payments.service;

import com.ecommerce.commons.contract.dto.IdDTO;
import com.ecommerce.payments.api.dto.PaymentDTO;
import com.ecommerce.payments.api.dto.PaymentFilterDTO;
import com.ecommerce.payments.domain.Payment;
import com.ecommerce.payments.parser.PaymentParser;
import com.ecommerce.payments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentParser paymentParser;

    public PaymentService(PaymentRepository paymentRepository, PaymentParser paymentParser) {
        this.paymentRepository = paymentRepository;
        this.paymentParser = paymentParser;
    }

    public List<PaymentDTO> findByFilter(PaymentFilterDTO paymentFilterDTO){
        if (paymentFilterDTO.hasOrderId()) {
            List<Payment> payments = paymentRepository.findByOrderId(paymentFilterDTO.getOrderId());
            return payments
                    .stream()
                    .map(paymentParser::toDTO)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    public IdDTO create(PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.save(paymentParser.toDomain(paymentDTO));
        return new IdDTO(payment.getId().toHexString());
    }

    public void update(PaymentDTO paymentDTO) {
        paymentRepository.save(paymentParser.toDomain(paymentDTO));
    }
}