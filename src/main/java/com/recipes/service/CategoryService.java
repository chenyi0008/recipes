package com.recipes.service;

import com.recipes.entity.Category;

import java.util.List;

public interface CategoryService {

    public void deleteById(Long id);

    public void update(Category category);

    public void save(Category category);

    public List<Category> findAll();

}
