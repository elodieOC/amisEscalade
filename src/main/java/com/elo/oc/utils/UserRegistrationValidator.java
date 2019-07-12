package com.elo.oc.utils;

import com.elo.oc.entity.User;
import com.elo.oc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/**
 * <p>Class validates the User registration form</p>
 */
@Component
public class UserRegistrationValidator implements Validator {
    private static final Logger logger = LogManager.getLogger(UserRegistrationValidator.class);
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * <p>Method adds error to bindingresult if username or email already exists in db,
     * if password contains whitespaces or is shorter than 8 characters</p>
     * @param o the user
     * @param errors error values will be added to bindingresult for messages on form
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.findUserWithThisEmail(user.getEmail()).isPresent()) {
            logger.info("email already exists in database");
            errors.rejectValue("email", "registration.email.duplicate");
        }
        if (userService.findUserWithThisUsername(user.getUsername()).isPresent()) {
            logger.info("username already exists in database");
            errors.rejectValue("username", "registration.username.duplicate");
        }
        if(user.getPassword().contains(" ")){
            logger.info("password contains whitespace");
            errors.rejectValue("password", "registration.password.whitespace");
        }
        if(user.getPassword().length() < 8 && !user.getPassword().isEmpty()){
            logger.info("password < 8");
            errors.rejectValue("password", "registration.password.tooshort");
        }
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            logger.info("passwords don't match");
            errors.rejectValue("passwordConfirm", "registration.password.noMatch");
        }
    }
}