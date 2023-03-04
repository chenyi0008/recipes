package com.recipes.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.AddressBook;
import com.recipes.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * address
 */
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    AddressBookService addressBookService;

    /**
     * 添加地址
     * @param addressBook
     * @return
     */
    @PostMapping
    public R<String> add(@RequestBody AddressBook addressBook){
        addressBook.setId(null);
        Long userId = BaseContext.getUserId();
        addressBook.setUserId(userId);
        addressBookService.save(addressBook);

        return R.msg("添加成功");
    }

    /**
     * 修改地址
     * @param addressBook
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody AddressBook addressBook){
        Long userId = BaseContext.getUserId();
        addressBook.setUserId(userId);
        addressBookService.save(addressBook);
        return R.msg("修改成功");
    }

    /**
     * 查询地址
     * @return
     */
    @GetMapping
    public R<List<AddressBook>> getAll(){
        Long userId = BaseContext.getUserId();
        List<AddressBook> date = addressBookService.getAll(userId);
        return R.success(date);
    }

    /**
     * 删除地址
     * @return
     */
    @DeleteMapping
    @Transactional
    public R<String> deleteById(Long id){
        Long userId = BaseContext.getUserId();
        addressBookService.deleteById(id, userId);
        return R.msg("删除成功");
    }

}
