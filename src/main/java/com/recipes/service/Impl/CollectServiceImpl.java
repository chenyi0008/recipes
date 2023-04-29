package com.recipes.service.Impl;

import com.recipes.entity.Collect;
import com.recipes.mapper.CollectDao;
import com.recipes.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectDao collectDao;

    public void save(Collect collect){
        collect.setId(null);
        collectDao.save(collect);
    }

    public void update(Collect collect){
        collectDao.save(collect);
    }

    public void delete(Integer id) {
        collectDao.deleteById(id);
    }

    public Page<Collect> findAllByUserId(Integer userId, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return collectDao.findAllByUserId(userId, pageable);
    }

}
