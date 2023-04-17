package com.recipes.controller;


import com.recipes.common.R;
import com.recipes.entity.Board;
import com.recipes.entity.Employee;
import com.recipes.service.BoardService;
import com.recipes.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * board
 */
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

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





}
