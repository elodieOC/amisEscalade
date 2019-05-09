package com.elo.oc.dao;

import com.elo.oc.entity.User;

import java.util.List;

public interface UserDAO {

    void saveUser(User user);
    void adminSaveUser(User user);
    void deleteUser(Integer id);

    List < User > getUsers();
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User findUserById(Integer id);
    User findUserByIdWithAllInfos(Integer id);
    List<User> findUserByRole(Integer id);



}
