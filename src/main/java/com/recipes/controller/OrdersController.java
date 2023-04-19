package com.recipes.controller;


import com.recipes.common.R;
import com.recipes.entity.Orders;
import com.recipes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/{page}/{size}")
    public R<Page<Orders>> findAll(@PathVariable int page,@PathVariable int size){
        return R.success(ordersService.findAll(page - 1, size));
    }


}
