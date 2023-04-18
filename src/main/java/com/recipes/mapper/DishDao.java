package com.recipes.mapper;


import com.recipes.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface DishDao extends JpaRepository<Dish,Integer>, JpaSpecificationExecutor<Dish> {

    public Page<Dish> findByNameLike(String name, Pageable pageable);

    public Page<Dish> findByNameLikeAndStatus(String name, Integer status, Pageable pageable);

    public Page<Dish> findByCategoryId(Integer categoryId, Pageable pageable);

    public Page<Dish> findByNameLikeAndStatusAndCategoryId(String name, Integer status, Pageable pageable,Integer categoryId);

}
