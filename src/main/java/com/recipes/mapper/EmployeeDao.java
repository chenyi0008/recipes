package com.recipes.mapper;

import com.recipes.entity.Employee;
import com.recipes.entity.User;
import com.recipes.service.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeDao extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

    public Employee findByUsername(String username);


}
