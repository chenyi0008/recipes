package com.recipes.service.Impl;

import com.recipes.entity.Menu;
import com.recipes.mapper.MenuDao;
import com.recipes.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    public void save(Menu menu) {
        menuDao.save(menu);
    }

    public void delete(Long id) {
        menuDao.deleteById(id);
    }

    public Page<Menu> query(String name, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1,pageSize);
        if(name == null) name = "%%";
        else name = "%" + name + "%";
        return menuDao.findByNameLike(name, pageable);
    }



}
