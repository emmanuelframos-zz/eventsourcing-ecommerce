package com.ecommerce.stores.api;

import com.ecommerce.commons.exceptions.exception.EntityNotFoundException;
import com.ecommerce.stores.api.dto.StoreDTO;
import com.ecommerce.stores.api.dto.StoreFilterDTO;
import com.ecommerce.stores.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/stores",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StoreAPI {

    private final StoreService storeService;

    public StoreAPI(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StoreDTO findByFilter(StoreFilterDTO storeFilterDTO) throws EntityNotFoundException {
        return storeService.findByFilter(storeFilterDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody StoreDTO storeDTO){
        return storeService.create(storeDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String storeId, @RequestBody StoreDTO storeDTO){
        storeDTO.id = storeId;
        storeService.update(storeDTO);
    }
}