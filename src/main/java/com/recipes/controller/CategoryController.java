package com.recipes.controller;


import com.recipes.common.R;
import com.recipes.entity.Category;
import com.recipes.entity.Menu;
import com.recipes.service.CategoryService;
import com.recipes.service.MenuService;
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

    @Autowired
    MenuService menuService;

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
     * 查询所有
     * @return
     */
    @GetMapping
    public R<List<Category>> findAll(){
        List<Category> all = categoryService.findAll();
        return R.success(all);
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
    public R<String> deleteById(Long id){
        categoryService.deleteById(id);
        return R.msg("删除成功");
    }


    /**
     * 根据categoryId查询菜品
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("menu")
    public R<Page<Menu>> getByCategoryId(Long categoryId, Integer page, Integer pageSize){
        Page<Menu> res = menuService.getByCategoryId(categoryId, page, pageSize);
        return R.success(res);
    }

    /**
     * 添加菜品加入到分类
     * @param categoryId
     * @param menuId
     * @return
     */
    @GetMapping("add")
    public R<String> addMenu(Long categoryId, Long menuId){

        Optional<Menu> menu = menuService.getMenuById(menuId);
        Menu menu1 = menu.get();
        if(!menu.isPresent())return R.error("不存在此菜品");
        menu1.setCategoryId(categoryId);
        menuService.save(menu1);
        return R.msg("添加成功");
    }



}
