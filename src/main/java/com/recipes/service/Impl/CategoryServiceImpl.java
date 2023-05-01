package com.recipes.service.Impl;

import com.recipes.entity.Category;
import com.recipes.mapper.CategoryDao;
import com.recipes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Category> findByStoreId(Integer storeId){
        Pageable pageable = PageRequest.of(0, 1000);
        return categoryDao.findAllByStoreId(storeId, pageable);
    }

    public void update(Category category){
        categoryDao.save(category);
    }

    public void deleteById(Integer id){
        categoryDao.deleteById(id);
    }


}
