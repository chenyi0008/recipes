package com.recipes.controller;


import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Store;
import com.recipes.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * store
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

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
     * 添加餐厅
     * @param store
     * @return
     */
    @PostMapping
    public R<String> add(@RequestBody Store store){
        storeService.save(store);
        return R.msg("添加成功");
    }


    /**
     * 更新餐厅
     * @param store
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Store store){
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






}
