package com.recipes.controller;


import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Category;
import com.recipes.entity.Collect;
import com.recipes.entity.Dish;
import com.recipes.service.CategoryService;
import com.recipes.service.CollectService;
import com.recipes.service.DishService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;


/**
 * dish
 */
@RestController
@RequestMapping("dish")
public class DishController {

    @Value("${recipes.url}")
    private String url;

    @Autowired
    DishService dishService;

    @Autowired
    CollectService collectService;

    /**
     * 添加菜品
     * @param dish
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Dish dish){
        dish.setId(null);
        String image = "https://pic.ntimg.cn/20100320/4229075_134644046569_2.jpg";
        if(Strings.isEmpty(dish.getImage()) || dish.getImage() == null){
            dish.setImage(image);
        }
        dishService.save(dish);
        return R.msg("添加成功");
    }

    /**
     * 更新菜品
     * @param dish
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Dish dish){
        String image = "https://pic.ntimg.cn/20100320/4229075_134644046569_2.jpg";
        if(Strings.isEmpty(dish.getImage())|| dish.getImage() == null){
            dish.setImage(image);
        }
        dishService.save(dish);
        return R.msg("更新成功");
    }

    /**
     * 删除菜品
     */
    @DeleteMapping
    public R<String> delete(Integer id){
        dishService.delete(id);
        return R.msg("删除成功");
    }

//    /**
//     * 查询菜品
//     */
//    @GetMapping
//    public R<Page<Dish>> query(String name, Integer page, Integer pageSize){
//        Page<Dish> query = dishService.query(name, page, pageSize);
//        return R.success(query);
//    }


    /**
     * 根据categoryId查询菜品
     * @param categoryId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{categoryId}/{page}/{size}")
    public R<Page<Dish>> getByCategoryId(@PathVariable Integer categoryId, @PathVariable Integer page, @PathVariable Integer size){
        Page<Dish> res = dishService.getByCategoryId(categoryId, page, size);
        List<Dish> content = res.getContent();
        for (int i = 0; i < content.size(); i++) {
            Dish dish = content.get(i);
            String image = dish.getImage();
            if(!image.startsWith("http")){
                image = url + "common/download/" + image;
                dish.setImage(image);
            }
        }
        return R.success(res);
    }

    /**
     * 获取所有菜品
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}")
    public R<Page<Dish>> findAll(@PathVariable Integer page, @PathVariable Integer size){
        Integer userId = BaseContext.getUserId();
        Page<Collect> collects = collectService.findAllByUserId(userId, 1, Integer.MAX_VALUE);
        Page<Dish> all = dishService.findAll(page, size);
        List<Dish> dishList = all.getContent();

        for (int i = 0; i < dishList.size(); i++) {
            Dish dish = dishList.get(i);
            Integer id = dish.getId();
            for (Collect collect : collects) {
                dish.setIsCollected(false);
                if (collect.getDishId().equals(id)){
                    dish.setIsCollected(true);
                    continue;
                }
            }
            String image = dish.getImage();
            if(!image.startsWith("http")){
                image = url + "common/download/" + image;
                dish.setImage(image);
            }

        }

        Page<Dish> newPage = new PageImpl<>(dishList, PageRequest.of(page, size), all.getTotalElements());
        return R.success(newPage);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R<Dish> getById(@PathVariable("id") int id){
        Optional<Dish> dish = dishService.getDishById(id);
        return R.success(dish.get());
    }

    @Autowired
    CategoryService categoryService;

//    @GetMapping("/list")
//    public R<Page<Dish>> getAll(@RequestParam("storeId")int storeId, @RequestParam("page")int page, @RequestParam("size")int size){
//        Page<Category> p = categoryService.findByStoreId(storeId);
//        List<Dish> list = new ArrayList<>();
//
//        List<Category> content = p.getContent();
//        for (Category category : content) {
//            Set<Dish> dishSet = category.getDishSet();
//            for (Dish dish : dishSet) {
//                list.add(dish);
//            }
//        }
//
//
//    }
}
