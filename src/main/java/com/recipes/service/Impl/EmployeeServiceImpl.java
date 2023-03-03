package com.recipes.service.Impl;

import com.recipes.entity.Employee;
import com.recipes.entity.User;
import com.recipes.mapper.EmployeeDao;
import com.recipes.mapper.UserDao;
import com.recipes.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeDao.findByUsername(username);
    }
}
