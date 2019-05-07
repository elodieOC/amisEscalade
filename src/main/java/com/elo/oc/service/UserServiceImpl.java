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
    @Transactional
    public List <User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User theUser) {
        userDAO.saveUser(theUser);
    }
    @Override
    @Transactional
    public void adminSaveUser(User theUser) {
        userDAO.adminSaveUser(theUser);
    }

    @Override
    public User findByIdWithSpots(int id) {
       return   userDAO.findByIdWithSpots(id);
    }

    @Override
    @Transactional
    public User findById(int theId) {
        return userDAO.findById(theId);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        User u = userDAO.findByUsername(username);
        return u;
    }

    @Override
    public List<User> findByRole(int id) {
        return userDAO.findByRole(id);
    }

    @Override
    @Transactional
    public void deleteUser(int theId) {
        userDAO.deleteUser(theId);
    }

    @Override
    @Transactional
    public Optional<User> findUserWithThisEmail(String email) {
        return userDAO.getUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    @Transactional
    public Optional<User> findUserWithThisUsername(String username) {
        return userDAO.getUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }


}
