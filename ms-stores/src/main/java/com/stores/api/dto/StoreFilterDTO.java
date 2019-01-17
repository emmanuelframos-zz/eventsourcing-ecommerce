package com.stores.api.dto;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import java.util.Objects;

public class StoreFilterDTO {

    private ObjectId id;
    private String name;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasId(){
        return Objects.nonNull(id);
    }

    public boolean hasName(){
        return StringUtils.isNotEmpty(name);
    }
}