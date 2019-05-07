package com.elo.oc.dao;

import com.elo.oc.entity.User;

import java.util.List;

public interface UserDAO {

    void saveUser(User user);
    void adminSaveUser(User user);
    void deleteUser(int id);

    List < User > getUsers();
    User findByEmail (String email);
    User findByUsername(String username);
    User findById(int id);
    User findByIdWithSpots(int id);
    List<User> findByRole(int id);



}
