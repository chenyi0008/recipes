package com.recipes.controller;


import com.recipes.common.R;
import com.recipes.entity.ShoppingCart;
import com.recipes.entity.User;
import com.recipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

/**
 * backstage
 */
@RestController
@RequestMapping("backstage")
public class BackstageController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    DishService dishService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;


    /**
     * 上菜
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<String> serve(@PathVariable Integer id){
        Optional<ShoppingCart> op = shoppingCartService.getById(id);
        if(!op.isPresent())return R.error("不存在此记录");
        ShoppingCart shoppingCart = op.get();
        shoppingCart.setStatus(1);
        shoppingCartService.update(shoppingCart);
        return R.msg("上菜成功");
    }

    /**
     * 获取订单数据
     * @param size
     * @param page
     * @return
     */
    @GetMapping("/{storeId}/{page}/{size}")
    public R<Page<ShoppingCart>> findAll(@PathVariable Integer size, @PathVariable Integer page, @PathVariable Integer storeId){
        return R.success(shoppingCartService.findAll(page, size, storeId));
    }

    /**
     * 根据状态获取订单数据
     * @param page
     * @param size
     * @param status
     * @return
     */
    @GetMapping("/{storeId}/{page}/{size}/{status}")
    public R<Page<ShoppingCart>> findByStatus(@PathVariable Integer page, @PathVariable Integer size, @PathVariable Integer status, @PathVariable Integer storeId){
        return R.success(shoppingCartService.findAllByStatus(status, page, size, storeId));
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public R<User> getUser(@PathVariable Integer id){
        Optional<User> optional = userService.findById(id);
        if(!optional.isPresent())return R.error("不存在此用户");
        User user = optional.get();
        user.setPassword("");
        return R.success(user);
    }

}
