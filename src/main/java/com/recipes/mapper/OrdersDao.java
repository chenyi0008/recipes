package com.recipes.mapper;

import com.recipes.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrdersDao extends JpaRepository<Orders,Long>, JpaSpecificationExecutor<Orders> {

    public Page<Orders> findOrdersByUserId(Pageable pageable, Long userId);

}
