package com.recipes.service.Impl;

import com.recipes.entity.ShoppingCart;
import com.recipes.mapper.ShoppingCartDao;
import com.recipes.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Override
    public Page<ShoppingCart> getAll(Long userId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return shoppingCartDao.findByUserId(userId, pageable);
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    public Integer deleteById(Long id, Long userId) {
        return shoppingCartDao.deleteShoppingCartByUserIdAndId(id, userId);
    }

    @Override
    public Integer deleteAll(Long userId) {
        return shoppingCartDao.deleteAllByUserId(userId);
    }
}
