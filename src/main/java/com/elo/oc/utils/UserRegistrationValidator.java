package com.elo.oc.utils;

import com.elo.oc.entity.User;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberOrNot", "NotEmpty");

        if (userService.findUserWithThisEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "registration.email.duplicate");
        }

        if (userService.findUserWithThisUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "registration.username.duplicate");
        }

        if(!user.getPassword().equals(user.getPasswordConfirm())){
            errors.rejectValue("passwordConfirm", "registration.password.noMatch");
        }
    }
}