package com.recipes.mapper;

import com.recipes.entity.Menu;
import com.recipes.entity.OrderDetail;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderDetailDao  extends JpaRepository<OrderDetail,Long>, JpaSpecificationExecutor<OrderDetail> {

    public List<OrderDetail> findOrderDetailById(Long id);


}
