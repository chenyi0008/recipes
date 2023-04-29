package com.recipes.mapper;

import com.recipes.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ShoppingCartDao extends JpaRepository<ShoppingCart,Integer>, JpaSpecificationExecutor<ShoppingCart> {

    public Page<ShoppingCart> findByUserId(Integer userId, Pageable pageable);

    public Integer deleteShoppingCartByIdAndUserId(Integer id, Integer userId);

    public Integer removeShoppingCartByUserIdAndId(Integer userId, Integer id);

    public Integer deleteAllByUserId(Integer userId);

    public Page<ShoppingCart> findAllByStoreId(Pageable pageable, Integer storeId);

    public Page<ShoppingCart> findAllByStatusAndStoreId(Pageable pageable,Integer status, Integer storeId);

    public Page<ShoppingCart> findAllByStatusAndUserId(Pageable pageable, Integer status, Integer userId);

    public Page<ShoppingCart> findAllByStatusAndUserIdAndIdIn(Pageable pageable, Integer status, Integer userId, List<Integer> ids);

    public Integer deleteAllByUserIdAndStatusNot(Integer userId, Integer status);





}
