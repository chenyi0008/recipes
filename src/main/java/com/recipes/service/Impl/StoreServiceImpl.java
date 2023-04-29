package com.recipes.service.Impl;

import com.recipes.common.BaseContext;
import com.recipes.entity.Store;
import com.recipes.mapper.StoreDao;
import com.recipes.service.EmployeeService;
import com.recipes.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreDao storeDao;

    public void save(Store store){
        Integer userId = BaseContext.getUserId();
        store.setEmployeeId(userId);
        store.setId(null);
        storeDao.save(store);
    }

    public void update(Store store){
        Integer userId = BaseContext.getUserId();
        store.setEmployeeId(userId);
        storeDao.save(store);
    }

    public void deleteById(Integer id){
        storeDao.deleteById(id);
    }

    public Page<Store> query(Integer storeId, int page, int size){
        Pageable pageable = PageRequest.of(page - 1 , size);
        return storeDao.queryAllByEmployeeId(storeId, pageable);
    }

    public Store getById(Integer id){
        return storeDao.getById(id);
    }

    public Page<Store> getRandomStore(int size){
        long count = storeDao.count();
        int page = (int)(Math.random() * count / size);
        Pageable pageable = PageRequest.of(page, size);
        return storeDao.findAll(pageable);
    }

}
