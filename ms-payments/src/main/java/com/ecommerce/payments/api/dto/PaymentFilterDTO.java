package com.ecommerce.payments.api.dto;

import org.apache.commons.lang3.StringUtils;

public class PaymentFilterDTO {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean hasOrderId(){
        return StringUtils.isNotEmpty(orderId);
    }
}
