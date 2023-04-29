package com.recipes.service;

import com.recipes.entity.Orders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrdersService {
    public Page<Orders> findAll(int page, int size);

    public void save(Orders orders);

    public Page<Orders> findByUserIdAndStoreId(Integer userId,Integer storeId, int page, int size);

    public Page<Orders> findByStoreId(Integer storeId, Integer page, Integer size);

}
