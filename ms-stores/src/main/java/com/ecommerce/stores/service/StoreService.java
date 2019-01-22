package com.ecommerce.stores.service;

import com.ecommerce.commons.contract.dto.IdDTO;
import com.ecommerce.commons.exceptions.exception.EntityNotFoundException;
import com.ecommerce.commons.exceptions.exception.ExceptionMessages;
import com.ecommerce.stores.api.dto.StoreDTO;
import com.ecommerce.stores.api.dto.StoreFilterDTO;
import com.ecommerce.stores.domain.Store;
import com.ecommerce.stores.parser.StoreParser;
import com.ecommerce.stores.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreParser storeParser;

    public StoreService(StoreRepository storeRepository, StoreParser storeParser) {
        this.storeRepository = storeRepository;
        this.storeParser = storeParser;
    }

    public StoreDTO findByFilter(StoreFilterDTO storeFilterDTO) throws EntityNotFoundException {
        if (storeFilterDTO.hasId())
            return storeParser.toDTO(storeRepository.findById(storeFilterDTO.getId()));

        if (storeFilterDTO.hasName())
            return storeParser.toDTO(storeRepository.findByName(storeFilterDTO.getName()));

        throw new EntityNotFoundException(ExceptionMessages.STORE_NOT_FOUND);
    }

    public IdDTO create(StoreDTO storeDTO){
        Store store =  storeParser.toDomain(storeDTO);
        storeRepository.save(store);
        return new IdDTO(store.getId().toHexString());
    }

    public void update(StoreDTO storeDTO){
        storeRepository.save(storeParser.toDomain(storeDTO));
    }
}

