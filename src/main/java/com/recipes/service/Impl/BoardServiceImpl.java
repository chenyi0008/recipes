package com.recipes.service.Impl;

import com.recipes.entity.Board;
import com.recipes.mapper.BoardDao;
import com.recipes.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;


    @Override
    public void save(Board board) {
        board.setId(null);
        boardDao.save(board);
    }

    @Override
    public void update(Board board) {
        boardDao.save(board);
    }

    @Override
    public void delete(Integer id) {
        boardDao.deleteById(id);
    }

    @Override
    public Page<Board> queryByStoreId(Integer storeId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1,size);
        return boardDao.findAllByStoreId(storeId,pageable);
    }

    @Override
    public Optional<Board> queryById(Integer boardId){
        return boardDao.findById(boardId);
    }

}
