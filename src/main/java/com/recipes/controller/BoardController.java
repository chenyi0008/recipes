package com.recipes.controller;


import com.recipes.common.BaseContext;
import com.recipes.common.R;
import com.recipes.entity.Board;
import com.recipes.entity.Category;
import com.recipes.entity.Store;
import com.recipes.service.BoardService;
import com.recipes.service.CategoryService;
import com.recipes.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * board
 */
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StoreService storeService;

    /**
     * 添加餐桌
     * @param board
     * @return
     */
    @PostMapping()
    public R<String> add(@RequestBody Board board){
        boardService.save(board);
        return R.msg("添加成功");
    }

    /**
     * 根据id修改餐桌
     * @param board
     * @return
     */
    @PutMapping()
    public R<String> update(@RequestBody Board board){

        boardService.save(board);
        return R.msg("添加成功");
    }

    /**
     * 删除餐桌
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Integer id){
        boardService.delete(id);
        return R.msg("删除成功");
    }

    /**
     * 根据商店id查看餐桌列表
     * @param storeId
     * @return
     */
    @GetMapping("/{storeId}/{page}/{size}")
    public R<Page<Board>> query(@PathVariable Integer storeId, @PathVariable Integer page, @PathVariable Integer size){
        Page<Board> boards = boardService.queryByStoreId(storeId, page, size);
        return R.success(boards);
    }


    /**
     * 顾客绑定餐桌并且返回菜单分类
     * @return
     */
    @GetMapping("/{boardId}")
    public R<Page<Category>> bind(@PathVariable Integer boardId){
        Optional<Board> board;

        board = boardService.queryById(boardId);

        Integer storeId = null;
        if(board.isPresent()){
            Board b = board.get();
            storeId = b.getStoreId();
            boardService.update(b);
        }else return R.error("错误信息");
        Page<Category> page = categoryService.findByStoreId(storeId);
        R r = R.success(page);
        Store store = storeService.getById(storeId);
        Map map = r.getMap();
        map.put("store", store);
        return r;
    }





}
