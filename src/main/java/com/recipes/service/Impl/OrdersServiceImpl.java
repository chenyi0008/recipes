package com.recipes.service.Impl;

import com.recipes.entity.Orders;
import com.recipes.mapper.OrdersDao;
import com.recipes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDao ordersDao;

    public Page<Orders> findAll(int page, int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
        Pageable pageable = PageRequest.of(0, 10);

        return ordersDao.findAll(pageable);
    }

    public void save(Orders orders) {
        ordersDao.save(orders);
    }

    @Override
    public Page<Orders> findByUserIdAndStoreId(Integer userId, Integer storeId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ordersDao.findOrdersByUserIdAndStoreId(pageable, userId, storeId);
    }

    public Page<Orders> findByStoreId(Integer storeId, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return ordersDao.findAllByStoreId(pageable, storeId);
    }

}
