package com.ecommerce.payments.api;

import com.ecommerce.commons.contract.dto.IdDTO;
import com.ecommerce.payments.api.dto.PaymentDTO;
import com.ecommerce.payments.api.dto.PaymentFilterDTO;
import com.ecommerce.payments.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/payments",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PaymentAPI {

    private final PaymentService paymentService;

    public PaymentAPI(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDTO> findByFilter(PaymentFilterDTO paymentFilterDTO){
        return paymentService.findByFilter(paymentFilterDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdDTO create(@RequestBody PaymentDTO paymentDTO){
        return paymentService.create(paymentDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathParam("id") String id, @RequestBody PaymentDTO paymentDTO){
        paymentDTO.id = id;
        paymentService.update(paymentDTO);
    }
}
