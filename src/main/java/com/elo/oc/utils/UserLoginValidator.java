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
 * <p>Class validates the User login form</p>
 */
@Component
public class UserLoginValidator implements Validator {
    private static final Logger logger = LogManager.getLogger(UserLoginValidator.class);
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
            if (formUsername.equals("")) {
                logger.info("login empty");
                errors.rejectValue("username", "NotEmpty");
            }
            if (formPassword.equals("")) {
                logger.info("password empty");
                errors.rejectValue("password", "NotEmpty");
            }
            if (!formUsername.equals("") && !userService.findUserWithThisUsername(formUsername).isPresent()) {
                logger.info("Username does not exists in database");
                errors.rejectValue("username", "login.username.unknown");
            }
            if (!formPassword.equals("")&& userService.findUserWithThisUsername(formUsername).isPresent()) {
                logger.info("Username exists in database");
                User toBeChecked = userService.findUserByUsername(formUsername).get(0);
                System.out.println("USERTOLOGIN"+toBeChecked);
                String loginPassword = Encryption.encrypt(formPassword);
                if (!loginPassword.equals(toBeChecked.getPassword())) {
                    logger.info("password doesn't match username");
                    errors.rejectValue("password", "login.password.noMatch");
                }
            }
        }
    }
