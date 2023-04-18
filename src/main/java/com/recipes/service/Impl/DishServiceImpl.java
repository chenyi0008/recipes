package com.recipes.service.Impl;

import com.recipes.entity.Dish;
import com.recipes.mapper.DishDao;
import com.recipes.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishDao dishDao;

    public void save(Dish dish) {
        dishDao.save(dish);
    }

    public void delete(Integer id) {
        dishDao.deleteById(id);
    }

    public Page<Dish> query(String name, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1,pageSize);
        if(name == null) name = "%%";
        else name = "%" + name + "%";
        return dishDao.findByNameLike(name, pageable);
    }

    @Override
    public Optional<Dish> getDishById(Integer id) {
        return dishDao.findById(id);
    }

    @Override
    public Page<Dish> getByCategoryId(Integer categoryId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return dishDao.findByCategoryId(categoryId, pageable);
    }


    public Page<Dish> findByNameLikeAndStatus(String name, Integer page, Integer pageSize){
        Pageable pageable = PageRequest.of(page - 1,pageSize);
        if(name == null) name = "%%";
        else name = "%" + name + "%";
        return dishDao.findByNameLikeAndStatus(name, 1,pageable);
    }






}
