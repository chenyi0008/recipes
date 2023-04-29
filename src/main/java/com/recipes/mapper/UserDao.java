package com.recipes.mapper;

import com.recipes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    public User findByUsername(String username);


}
