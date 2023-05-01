package com.recipes.service;

import com.recipes.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    public Page<ShoppingCart> getAll(Integer userId, Integer page, Integer pageSize);

    public void save(ShoppingCart shoppingCart);

    public Integer deleteById(Integer id, Integer userId);

    public Integer deleteAll(Integer userId);

    public Optional<ShoppingCart> getById(Integer id);

    public void update(ShoppingCart shoppingCart);

    public Page<ShoppingCart> findAllByStatus(Integer status, Integer page, Integer size, Integer storeId);

    public Page<ShoppingCart> findAll(Integer page, Integer size, Integer storeId);

    public Page<ShoppingCart> findAllByStatusAndUserId(Integer status, Integer page, Integer size, Integer userId);

    public Integer deleteAllByUserIdAndStatusNot(Integer userId, Integer status);

    public void saveList(List<ShoppingCart> content);

    public Page<ShoppingCart> findAllByStatusAndUserIdAndIdIn(Integer status, Integer page, Integer size, Integer userId, List<Integer> list);

    public ShoppingCart getShoppingCart(Integer userId, Integer dishId, String dishFlavor);
}
