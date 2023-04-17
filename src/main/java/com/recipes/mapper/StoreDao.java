package com.recipes.mapper;

import com.recipes.entity.Employee;
import com.recipes.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface StoreDao extends JpaRepository<Store,Integer>, JpaSpecificationExecutor<Employee> {

    Page<Store> queryAllByEmployeeId(Integer EmployeeId, Pageable pageable);

}
