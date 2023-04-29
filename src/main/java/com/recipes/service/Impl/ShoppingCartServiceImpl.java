package com.recipes.service.Impl;

import com.recipes.entity.ShoppingCart;
import com.recipes.mapper.ShoppingCartDao;
import com.recipes.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Override
    public Page<ShoppingCart> getAll(Integer userId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return shoppingCartDao.findByUserId(userId, pageable);
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCart.setId(null);
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id, Integer userId) {
        return shoppingCartDao.removeShoppingCartByUserIdAndId(userId, id);
    }

    @Override
    public Integer deleteAll(Integer userId) {
        return shoppingCartDao.deleteAllByUserId(userId);
    }

    public Optional<ShoppingCart> getById(Integer id){
        return shoppingCartDao.findById(id);
    }

    public void update(ShoppingCart shoppingCart){
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    public Page<ShoppingCart> findAll(Integer page, Integer size, Integer storeId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return shoppingCartDao.findAllByStoreId(pageable, storeId);
    }

    @Override
    public Page<ShoppingCart> findAllByStatus(Integer status, Integer page, Integer size, Integer storeId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return shoppingCartDao.findAllByStatusAndStoreId(pageable, status,storeId);
    }

    public Page<ShoppingCart> findAllByStatusAndUserId(Integer status, Integer page, Integer size, Integer userId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return shoppingCartDao.findAllByStatusAndUserId(pageable, status, userId);
    }

    public Integer deleteAllByUserIdAndStatusNot(Integer userId, Integer status){
        return shoppingCartDao.deleteAllByUserIdAndStatusNot(userId, status);
    }

    public void saveList(List<ShoppingCart> content){
        shoppingCartDao.saveAll(content);
    }

    public Page<ShoppingCart> findAllByStatusAndUserIdAndIdIn(Integer status, Integer page, Integer size, Integer userId, List<Integer> list){
        Pageable pageable = PageRequest.of(page - 1, size);
        return shoppingCartDao.findAllByStatusAndUserIdAndIdIn(pageable,status, userId, list);
    }


}
