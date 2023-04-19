package com.recipes.service;

import com.recipes.entity.ShoppingCart;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ShoppingCartService {

    public Page<ShoppingCart> getAll(Integer userId, Integer page, Integer pageSize);

    public void save(ShoppingCart shoppingCart);

    public Integer deleteById(Integer id, Integer userId);

    public Integer deleteAll(Integer userId);

    public Optional<ShoppingCart> getById(Integer id);

    public void update(ShoppingCart shoppingCart);

}
