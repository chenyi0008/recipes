package com.recipes.mapper;

import com.recipes.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;


@Component
public interface ShoppingCartDao extends JpaRepository<ShoppingCart,Integer>, JpaSpecificationExecutor<ShoppingCart> {

    public Page<ShoppingCart> findByUserId(Integer userId, Pageable pageable);

    public Integer deleteShoppingCartByIdAndUserId(Integer id, Integer userId);

    public Integer removeShoppingCartByUserIdAndId(Integer userId, Integer id);

    public Integer deleteAllByUserId(Integer userId);



}
