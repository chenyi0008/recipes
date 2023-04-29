package com.recipes.service;

import com.recipes.entity.Dish;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DishService {
    public void save(Dish dish);

    public void delete(Integer id);

    public Page<Dish> query(String name, Integer page, Integer pageSize);

    public Page<Dish> findByNameLikeAndStatus(String name, Integer page, Integer pageSize);

    public Optional<Dish> getDishById(Integer id);

    public Page<Dish> getByCategoryId(Integer categoryId, Integer page, Integer pageSize);

    public Page<Dish> findAll(Integer page, Integer size);
}
