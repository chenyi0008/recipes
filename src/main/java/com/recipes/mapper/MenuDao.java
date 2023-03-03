package com.recipes.mapper;


import com.recipes.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuDao extends JpaRepository<Menu,Long>, JpaSpecificationExecutor<Menu> {

    public Page<Menu> findByNameLike(String name,Pageable pageable);

}
