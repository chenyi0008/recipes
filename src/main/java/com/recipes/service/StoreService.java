package com.recipes.service;

import com.recipes.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface StoreService {

    public Page<Store> query(Integer storeId, int page, int size);

    public void deleteById(Integer id);

    public void update(Store store);

    public void save(Store store);

    public Store getById(Integer id);

    public Page<Store> getRandomStore(int size);
}
