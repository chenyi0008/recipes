package com.recipes.mapper;

import com.recipes.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public interface OrdersDao extends JpaRepository<Orders,Integer>, JpaSpecificationExecutor<Orders> {

    public Page<Orders> findOrdersByUserIdAndStoreId(Pageable pageable, Integer userId, Integer storeId);

    public Page<Orders> findAllByStoreId(Pageable pageable, Integer storeId);

    @Transactional
    @Modifying
    @Query(value = "update orders set is_payed = 1 where id = ?1", nativeQuery = true)
    public void update(int id);

    public Page<Orders> findAllByUserId(Pageable pageable, Integer userId);

}
