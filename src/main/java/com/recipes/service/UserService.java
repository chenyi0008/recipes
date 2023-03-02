package com.recipes.service;


import com.recipes.entity.User;

public interface UserService {

    public User findByUsername(String username);

    public void save(User user);
}
