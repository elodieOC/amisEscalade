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
    public void adminSaveUser(User theUser) {
        userDAO.adminSaveUser(theUser);
    }

    @Override
    public User findByIdWithSpots(int id) {
       return   userDAO.findByIdWithSpots(id);
    }

    @Override
    public User findById(int theId) {
        return userDAO.findById(theId);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        User u = userDAO.findByUsername(username);
        return u;
    }

    @Override
    public List<User> findByRole(int id) {
        return userDAO.findByRole(id);
    }

    @Override
    public void deleteUser(int theId) {
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
