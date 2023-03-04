package com.recipes.service;

import com.recipes.entity.Employee;
import com.recipes.entity.User;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    public Employee findByUsername(String username);

    public void save(Employee employee);

    public Page<User> getUserDate(Integer page, Integer pageSize);

    public void deleteUserById(Long id);

}
