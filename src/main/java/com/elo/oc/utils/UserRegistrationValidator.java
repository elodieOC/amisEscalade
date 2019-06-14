package com.elo.oc.utils;

import com.elo.oc.entity.User;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (userService.findUserWithThisEmail(user.getEmail()).isPresent()) {
            System.out.println("email already exists in database");
            errors.rejectValue("email", "registration.email.duplicate");
        }

        if (userService.findUserWithThisUsername(user.getUsername()).isPresent()) {
            System.out.println("username already exists in database");
            errors.rejectValue("username", "registration.username.duplicate");
        }

        if(user.getPassword().contains(" ")){
            System.out.println("password contains whitespace");
            errors.rejectValue("password", "registration.password.whitespace");
        }
        if(user.getPassword().length() < 8){
            System.out.println("password < 8");
            errors.rejectValue("password", "registration.password.tooshort");
        }


        if(!user.getPassword().equals(user.getPasswordConfirm())){
            System.out.println("passwords don't match");
            errors.rejectValue("passwordConfirm", "registration.password.noMatch");
        }



    }
}