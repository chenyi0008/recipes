package com.recipes.service;

import com.recipes.entity.Employee;
import com.recipes.entity.User;

public interface EmployeeService {
    public Employee findByUsername(String username);

    public void save(Employee employee);

}
