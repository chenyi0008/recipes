package com.recipes.service;

import com.recipes.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    public void deleteById(Integer id);

    public void update(Category category);

    public void save(Category category);

    public List<Category> findAll();

    public Page<Category> findByStoreId(Integer storeId);
}
