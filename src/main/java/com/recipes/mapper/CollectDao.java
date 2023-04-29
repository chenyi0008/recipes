package com.recipes.mapper;

import com.recipes.entity.Category;
import com.recipes.entity.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface CollectDao extends JpaRepository<Collect,Integer>, JpaSpecificationExecutor<Collect> {

    public Page<Collect> findAllByUserId(Integer userId, Pageable pageable);


}
