package com.stores.service;

import com.ecommerce.exception.EntityNotFoundException;
import com.ecommerce.exception.ExceptionMessages;
import com.stores.api.dto.StoreDTO;
import com.stores.api.dto.StoreFilterDTO;
import com.stores.domain.Store;
import com.stores.parser.StoreParser;
import com.stores.repository.StoreRepository;
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

    public String create(StoreDTO storeDTO){
        Store store =  storeParser.toDomain(storeDTO);
        return storeRepository.save(store).getId().toHexString();
    }

    public void update(StoreDTO storeDTO){
        storeRepository.save(storeParser.toDomain(storeDTO));
    }
}

