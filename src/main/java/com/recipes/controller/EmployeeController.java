package com.recipes.controller;

import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Employee;
import com.recipes.entity.dto.EmployeeDto;
import com.recipes.service.EmployeeService;
import com.recipes.util.CodeUtil;
import com.recipes.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * employee
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
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
    @PostMapping("/login")
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

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping
    public R<EmployeeDto> getInformation(){
        Integer userId = BaseContext.getUserId();
        String username = BaseContext.getUsername();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setUserId(userId);
        employeeDto.setUsername(username);
        employeeDto.setDesc("餐厅管理员，职责包括但不限于：全面负责餐饮部的经营管理和日常运转工作，确保各营业目标的圆满实现；根据总经理室的工作方针制定部门内的具体实施计划；掌握市场行情，挖掘新品，确定饮食推销策略；组织实施员工培训；定期检查卫生法的执行情况等1");
        employeeDto.setAvatar("https://pic.616pic.com/ys_img/00/17/05/kh6uenZdNE.jpg");
        employeeDto.setRoles(new String[]{"admin"});
        return R.success(employeeDto);
    }




}
