package com.recipes.mapper;


import com.recipes.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface BoardDao extends JpaRepository<Board,Integer>, JpaSpecificationExecutor<Board> {

    public Page<Board> findAllByStoreId(Integer storeId, Pageable pageable);

}
