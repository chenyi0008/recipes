package com.recipes.controller;

import com.recipes.common.R;
import com.recipes.entity.Orders;
import com.recipes.entity.User;
import com.recipes.service.EmployeeService;
import com.recipes.service.OrdersService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;

/**
 * backStage
 */
@RestController
@RequestMapping("backStage")
public class BackStageController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    OrdersService ordersService;
    /**
     * 获取所有顾客信息
     */
    @GetMapping("user")
    public R<Page<User>> getUserDate(Integer page, Integer pageSize){
        return R.success(employeeService.getUserDate(page, pageSize));
    }

    /**
     * 删除顾客
     * @param id
     * @return
     */
    @DeleteMapping("user")
    public R<String> deleteUserById(Long id){
        employeeService.deleteUserById(id);
        return R.msg("删除成功");
    }

    /**
     * 获取所有订单信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("orderAll")
    public R<Page<Orders>> getOrderAll(int page, int size){
        Page<Orders> res = ordersService.findAll(page, size);
        return R.success(res);
    }

    /**
     * 根据id获取订单信息
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("order")
    public R<Page<Orders>> getOrderByUserId(Long userId, int page, int size){
        Page<Orders> res = ordersService.findByUserId(userId, page, size);
        return R.success(res);
    }

    






}
