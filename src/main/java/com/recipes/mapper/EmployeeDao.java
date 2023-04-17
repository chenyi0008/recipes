package com.recipes.mapper;

import com.recipes.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeDao extends JpaRepository<Employee,Integer>, JpaSpecificationExecutor<Employee> {

    public Employee findByUsername(String username);


}
