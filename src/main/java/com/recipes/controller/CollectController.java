package com.recipes.controller;

import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Collect;
import com.recipes.entity.Dish;
import com.recipes.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * collect
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectService collectService;



    /**
     * 添加收藏
     * @param dishId
     * @return
     */
    @GetMapping("/{dishId}")
    public R<String> save(@PathVariable Integer dishId){
        Integer userId = BaseContext.getUserId();
        Collect collect = new Collect();
        collect.setDishId(dishId);
        collect.setUserId(userId);
        collectService.save(collect);
        return R.msg("添加成功");
    }

    /**
     * 删除收藏
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Integer id){
        collectService.delete(id);
        return R.msg("删除成功");
    }

    /**
     * 获取收藏列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}")
    public R<Page<Collect>> getCollectList(@PathVariable Integer page, @PathVariable Integer size){
        Integer userId = BaseContext.getUserId();
        Page<Collect> res = collectService.findAllByUserId(userId, page, size);
        List<Collect> content = res.getContent();
        List<Collect> newContent = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            Collect collect = content.get(i);
            Dish dish = collect.getDish();
            dish.setIsCollected(true);
            collect.setDish(dish);
            newContent.add(i, collect);
        }
        return R.success(res);
    }



}
