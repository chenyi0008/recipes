package com.recipes.service;


import com.recipes.entity.User;

import java.util.Optional;

public interface UserService {

    public User findByUsername(String username);

    public void save(User user);

    public Optional<User> findById(Integer id);
}
