package com.elo.oc.service;

import com.elo.oc.dao.UserDAO;
import com.elo.oc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired

    private UserDAO userDAO;

    @Override
    public List <User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void saveUser(User theUser) {
        userDAO.saveUser(theUser);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void adminSaveUser(User theUser) {
        userDAO.adminSaveUser(theUser);
    }

    @Override
    public User findUserByIdWithSpots(Integer id) {
       return   userDAO.findUserByIdWithAllInfos(id);
    }

    @Override
    public User findUserById(Integer theId) {
        return userDAO.findUserById(theId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public List<User> findUserByUsername(String username) {
        List<User> userList = userDAO.findUserByUsername(username);
        return userList;
    }

    @Override
    public List<User> findUserByRole(Integer id) {
        return userDAO.findUserByRole(id);
    }

    @Override
    public void deleteUser(Integer theId) {
        userDAO.deleteUser(theId);
    }


    @Override
    public Optional<User> findUserWithThisEmail(String email) {
        return userDAO.getUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<User> findUserWithThisUsername(String username) {
        return userDAO.getUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }


}
