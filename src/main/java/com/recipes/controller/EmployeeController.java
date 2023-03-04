package com.recipes.controller;

import com.recipes.common.R;
import com.recipes.entity.Employee;
import com.recipes.entity.User;
import com.recipes.service.EmployeeService;
import com.recipes.util.CodeUtil;
import com.recipes.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * employee
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public R<String> register(@RequestBody Employee user){
        String username = user.getUsername();
        Employee tmp = employeeService.findByUsername(username);
        if(tmp != null)return R.error("账号已存在");
        String s = CodeUtil.encodeToString(user.getPassword());
        user.setPassword(s);
        user.setId(null);
        employeeService.save(user);
        return R.msg("注册成功");
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public R<String> login(@RequestBody Employee user){
        Employee tmp = employeeService.findByUsername(user.getUsername());
        if(tmp == null || user.getPassword() == null)return R.error("该账号不存在");
        String password = tmp.getPassword();
        password = CodeUtil.decode(password);
        if(user.getPassword().equals(password)){
            String token = JwtUtil.createToken(tmp.getUsername(), tmp.getId(),"employee");
            return R.success(token, "登录成功");
        }
        return R.error("账号或密码输入有误");
    }




}
