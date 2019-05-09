package com.elo.oc.service;


import com.elo.oc.entity.User;

import java.util.List;
import java.util.Optional;

public  interface UserService {

    void saveUser(User user);
    void adminSaveUser(User user);
    void deleteUser(Integer id);

    List < User > getUsers();
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User findUserById(Integer id);
    User findUserByIdWithSpots(Integer id);
    List<User> findUserByRole(Integer id);

    Optional<User> findUserWithThisEmail(String email);
    Optional<User> findUserWithThisUsername(String username);
}