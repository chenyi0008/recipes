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
}
