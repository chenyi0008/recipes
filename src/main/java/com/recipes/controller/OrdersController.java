package com.recipes.controller;


import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Orders;
import com.recipes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    /**
     * 查询订单
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{storeId}/{page}/{size}")
    public R<Page<Orders>> findAll(@PathVariable Integer page, @PathVariable Integer size, @PathVariable Integer storeId){
        return R.success(ordersService.findByStoreId(storeId, page, size));
    }

    /**
     * 根据id查询账单
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Orders> findById(@PathVariable int id){
        return R.success(ordersService.findById(id));
    }


    /**
     * 付款(生成订单）
     */
    @PutMapping("/{id}")
    public R<String> updateById(@PathVariable int id){
        ordersService.update(id);
        return R.success("成功生成订单");
    }

    /**
     * 根据userId获取订单
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public R<Page<Orders>> findByUserId(@RequestParam int page, @RequestParam int size){
        Integer userId = BaseContext.getUserId();
        Page<Orders> list = ordersService.findByUserId(userId, page, size);
        return R.success(list);
    }




}
