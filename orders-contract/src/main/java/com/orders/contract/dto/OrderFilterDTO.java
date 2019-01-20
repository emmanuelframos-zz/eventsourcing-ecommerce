package com.orders.contract.dto;

import com.orders.contract.domain.OrderStatus;
import org.bson.types.ObjectId;

public class OrderFilterDTO {

    private ObjectId id;
    private OrderStatus status;

    public boolean hasId(){
        return id != null;
    }

    public boolean hasStatus(){
        return status != null;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}