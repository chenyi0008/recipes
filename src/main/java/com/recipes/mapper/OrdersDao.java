package com.recipes.mapper;

import com.recipes.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface OrdersDao extends JpaRepository<Orders,Integer>, JpaSpecificationExecutor<Orders> {

    public Page<Orders> findOrdersByUserIdAndStoreId(Pageable pageable, Integer userId, Integer storeId);

    public Page<Orders> findAllByStoreId(Pageable pageable, Integer storeId);

}
