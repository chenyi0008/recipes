package com.recipes.service;

import com.recipes.entity.Board;
import com.recipes.entity.Employee;
import org.springframework.data.domain.Page;

public interface BoardService {

    public void save(Board board);

    public void update(Board board);

    public void delete(Integer id);

    public Page<Board> queryByStoreId(Integer storeId, int page, int size);

}
