package com.recipes.service;

import com.recipes.entity.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CollectService {

    public void save(Collect collect);

    public void update(Collect collect);

    public void delete(Integer id);

    public Page<Collect> findAllByUserId(Integer userId, Integer page, Integer size);
}
