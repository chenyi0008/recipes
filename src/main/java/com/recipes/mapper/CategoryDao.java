package com.recipes.mapper;

import com.recipes.entity.AddressBook;
import com.recipes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface CategoryDao  extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {



}
