package com.recipes.service.Impl;

import com.recipes.entity.User;
import com.recipes.mapper.UserDao;
import com.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public Optional<User> findById(Integer id){
        return userDao.findById(id);
    }

}
