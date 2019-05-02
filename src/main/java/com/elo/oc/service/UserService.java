package com.elo.oc.service;


import com.elo.oc.entity.User;

import java.util.List;
import java.util.Optional;

public  interface UserService {

    void saveUser(User user);
    void deleteUser(int id);

    List < User > getUsers();
    User findByEmail (String email);
    User findByUsername(String username);
    User findById(int id);
    List<User> findByRole(int id);

    Optional<User> findUserWithThisEmail(String email);
    Optional<User> findUserWithThisUsername(String username);
}