package com.recipes.mapper;

import com.recipes.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderDetailDao  extends JpaRepository<OrderDetail,Integer>, JpaSpecificationExecutor<OrderDetail> {

    public List<OrderDetail> findOrderDetailById(Integer id);


}
