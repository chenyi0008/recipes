package com.recipes.service.Impl;

import com.recipes.entity.Category;
import com.recipes.mapper.CategoryDao;
import com.recipes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public List<Category> findAll(){
        return categoryDao.findAll();
    }

    public void save(Category category){
        category.setId(null);
        categoryDao.save(category);
    }

    public void update(Category category){
        categoryDao.save(category);
    }

    public void deleteById(Long id){
        categoryDao.deleteById(id);
    }

}
