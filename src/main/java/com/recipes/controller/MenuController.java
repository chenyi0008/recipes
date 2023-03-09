package com.recipes.controller;

import com.recipes.common.R;
import com.recipes.entity.Menu;
import com.recipes.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


/**
 * menu
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 添加菜品
     * @param menu
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Menu menu){
        menu.setId(null);
        menuService.save(menu);
        return R.msg("添加成功");
    }

    /**
     * 更新菜品
     * @param menu
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Menu menu){
        menuService.save(menu);
        return R.msg("更新成功");
    }

    /**
     * 删除菜品
     */
    @DeleteMapping
    public R<String> delete(Long id){
        menuService.delete(id);
        return R.msg("删除成功");
    }

    /**
     * 查询菜品
     */
    @GetMapping
    public R<Page<Menu>> query(String name, Integer page, Integer pageSize){
        Page<Menu> query = menuService.query(name, page, pageSize);
        return R.success(query);
    }





}
