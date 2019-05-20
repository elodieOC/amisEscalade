package com.elo.oc.utils;

import com.elo.oc.entity.User;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserUpdateUserNameValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (userService.findUserWithThisUsername(user.getUsername()).isPresent()) {
            System.out.println("username already exists in database");
            errors.rejectValue("username", "registration.username.duplicate");
        }

    }
}