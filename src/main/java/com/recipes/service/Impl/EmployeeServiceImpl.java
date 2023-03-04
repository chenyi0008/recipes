package com.recipes.service.Impl;

import com.recipes.entity.Employee;
import com.recipes.entity.User;
import com.recipes.mapper.EmployeeDao;
import com.recipes.mapper.UserDao;
import com.recipes.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    UserDao userDao;

    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    public Page<User> getUserDate(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<User> list = userDao.findAll(pageable);
        for (User user : list) {
            user.setPassword(null);
        }
        return list;
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    public Employee findByUsername(String username) {
        return employeeDao.findByUsername(username);
    }


}
