package com.stores.parser;

import com.stores.api.dto.StoreDTO;
import com.stores.domain.Store;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class StoreParser {

    public Store toDomain(StoreDTO storeDTO){
        Store store = new Store();
        store.setId(storeDTO.id == null ? null : new ObjectId(storeDTO.id));
        store.setName(storeDTO.name);
        store.setAddress(storeDTO.address);
        return store;
    }

    public StoreDTO toDTO(Store store){
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.id = store.getId().toHexString();
        storeDTO.address = store.getAddress();
        storeDTO.name = store.getName();
        return storeDTO;
    }

}