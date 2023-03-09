package com.recipes.service;

import com.recipes.entity.ShoppingCart;
import org.springframework.data.domain.Page;

public interface ShoppingCartService {

    public Page<ShoppingCart> getAll(Long userId, Integer page, Integer pageSize);

    public void save(ShoppingCart shoppingCart);

    public Integer deleteById(Long id, Long userId);

    public Integer deleteAll(Long userId);

}
