package com.stores.repository;

import com.stores.domain.Store;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {

    Store findById(ObjectId id);

    Store findByName(String name);

}