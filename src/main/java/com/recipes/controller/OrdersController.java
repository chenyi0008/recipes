package com.recipes.controller;


import com.recipes.common.R;
import com.recipes.entity.Menu;
import com.recipes.entity.Orders;
import com.recipes.mapper.OrdersDao;
import com.recipes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    public R<Page<Orders>> findAll(int page, int pageSize){
        return R.success(ordersService.findAll(page - 1, pageSize));
    }


}
