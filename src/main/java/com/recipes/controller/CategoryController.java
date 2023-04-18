package com.recipes.controller;


import com.recipes.common.R;
import com.recipes.entity.Category;
import com.recipes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * category
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

//    @Autowired
//    DishService dishService;

    /**
     * 添加分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.msg("添加成功");
    }

    /**
     * 根据storeId查询分类
     * @return
     */
    @GetMapping("/{storeId}")
    public R<Page<Category>> findAll(@PathVariable Integer storeId){
        Page<Category> page = categoryService.findByStoreId(storeId);
        return R.success(page);
    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){

        categoryService.update(category);
        return R.msg("更新成功");
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> deleteById(Integer id){
        categoryService.deleteById(id);
        return R.msg("删除成功");
    }


//
//    /**
//     * 添加菜品加入到分类
//     * @param categoryId
//     * @param menuId
//     * @return
//     */
//    @GetMapping("add")
//    public R<String> addMenu(Integer categoryId, Integer menuId){
//
//        Optional<Dish> menu = dishService.getMenuById(menuId);
//        Dish menu1 = menu.get();
//        if(!menu.isPresent())return R.error("不存在此菜品");
//        menu1.setCategoryId(categoryId);
//        dishService.save(menu1);
//        return R.msg("添加成功");
//    }



}
