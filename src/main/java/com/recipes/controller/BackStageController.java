package com.recipes.controller;

import com.recipes.common.R;
import com.recipes.entity.User;
import com.recipes.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * backStage
 */
@RestController
@RequestMapping("backStage")
public class BackStageController {
    @Autowired
    EmployeeService employeeService;

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




}
