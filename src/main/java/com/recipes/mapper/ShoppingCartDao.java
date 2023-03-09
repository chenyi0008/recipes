package com.recipes.mapper;

import com.recipes.entity.Menu;
import com.recipes.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface ShoppingCartDao extends JpaRepository<ShoppingCart,Long>, JpaSpecificationExecutor<ShoppingCart> {

    public Page<ShoppingCart> findByUserId(Long userId, Pageable pageable);

    public Integer deleteShoppingCartByUserIdAndId(Long id, Long userId);

    public Integer deleteAllByUserId(Long userId);



}
