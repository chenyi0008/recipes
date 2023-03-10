package com.recipes.service;

import com.recipes.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuService {
    public void save(Menu menu);

    public void delete(Long id);

    public Page<Menu> query(String name,Integer page,Integer pageSize);

    public Page<Menu> findByNameLikeAndStatus(String name,Integer page,Integer pageSize);

    public Optional<Menu> getMenuById(Long id);

    public Page<Menu> getByCategoryId(Long categoryId, Integer page, Integer pageSize);
}
