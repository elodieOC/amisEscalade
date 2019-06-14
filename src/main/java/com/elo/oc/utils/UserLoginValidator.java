package com.elo.oc.utils;

import com.elo.oc.entity.User;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/**
 * <p>Class validates the User login form</p>
 */
@Component
public class UserLoginValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * <p>Method adds error to bindingresult if username doesn't exist or password doesn't match username</p>
     * @param o the user
     * @param errors error values will be added to bindingresult for messages on form
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        String formUsername = user.getUsername();
        String formPassword = user.getPassword();
            if (!formUsername.equals("") && !userService.findUserWithThisUsername(formUsername).isPresent()) {
                System.out.println("Username does not exists in database");
                errors.rejectValue("username", "login.username.unknown");
            }
            if (!formPassword.equals("")&& userService.findUserWithThisUsername(formUsername).isPresent()) {
                System.out.println("Username exists in database");
                User toBeChecked = userService.findUserByUsername(formUsername);
                String loginPassword = Encryption.encrypt(formPassword);
                if (!loginPassword.equals(toBeChecked.getPassword())) {
                    System.out.println("password doesn't match username");
                    errors.rejectValue("password", "login.password.noMatch");
                }
            }
        }
    }
