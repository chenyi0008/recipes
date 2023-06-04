package com.recipes.controller;


import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Category;
import com.recipes.entity.Store;
import com.recipes.mapper.StoreDao;
import com.recipes.service.CategoryService;
import com.recipes.service.StoreService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * store
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StoreDao storeDao;

    /**
     * 获取餐厅列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}")
    public R<Page<Store>> query(@PathVariable Integer page, @PathVariable Integer size){
        Integer employeeId = BaseContext.getUserId();
        Page<Store> query = storeService.query(employeeId, page, size);
        return R.success(query);
    }

    /**
     * 随机返回餐厅列表
     * @param size
     * @return
     */
    @GetMapping("/{size}")
    public R<Page<Store>> getRandomStore(@PathVariable Integer size){
        return R.success(storeService.getRandomStore(size));
    }

    /**
     * 添加餐厅
     * @param store
     * @return
     */
    @PostMapping
    public R<String> add(@RequestBody Store store){
        String image = "https://img.zcool.cn/community/01030e5bfdf7e0a801209252d9c560.jpg@1280w_1l_2o_100sh.jpg";
        if(Strings.isEmpty(store.getImage())) {
            store.setImage(image);
        }

        Integer userId = BaseContext.getUserId();
        store.setEmployeeId(userId);
        storeService.save(store);
        Integer maxId = storeDao.getMaxId();
        System.out.println("=========================");
        System.out.println(maxId);

        Category category1 = new Category();
        category1.setStoreId(maxId);
        category1.setName("川菜");
        categoryService.save(category1);

        Category category2 = new Category();
        category2.setStoreId(maxId);
        category2.setName("粤菜");
        categoryService.save(category2);

        Category category3 = new Category();
        category3.setStoreId(maxId);
        category3.setName("湘菜");
        categoryService.save(category3);


        return R.msg("添加成功");
    }


    /**
     * 更新餐厅
     * @param store
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Store store){
        String image = "https://img.zcool.cn/community/01030e5bfdf7e0a801209252d9c560.jpg@1280w_1l_2o_100sh.jpg";
        if(Strings.isEmpty(store.getImage()))store.setImage(image);
        storeService.update(store);
        return R.msg("更新成功");
    }

    /**
     * 删除餐厅
     * @param storeId
     * @return
     */
    @DeleteMapping("/{storeId}")
    public R<String> delete(@PathVariable Integer storeId){
        storeService.deleteById(storeId);
        return R.msg("删除成功");
    }

    /**
     * 根据id获取餐厅
     * @param id
     * @return
     */
    @GetMapping
    public R<Store> getById(@Param("id")int id){
        Store store = storeService.getById(id);
        return R.success(store);
    }






}
