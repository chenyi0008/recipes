package com.recipes.mapper;

import com.recipes.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface CategoryDao  extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {

        Page<Category> findAllByStoreId(Integer storeId, Pageable pageable);

}
