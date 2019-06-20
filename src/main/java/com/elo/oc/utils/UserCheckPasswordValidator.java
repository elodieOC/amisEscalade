package com.elo.oc.utils;

import com.elo.oc.entity.User;
import com.elo.oc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * <p>Class validates the password and confirm password in user registration and update forms are the same</p>
 */
@Component
public class UserCheckPasswordValidator implements Validator {
    private static final Logger logger = LogManager.getLogger(UserCheckPasswordValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * <p>Method adds error to bindingresult if password and password confirm don't match</p>
     * @param o the user
     * @param errors error values will be added to bindingresult for messages on form
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "NotBlank");
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            logger.info("passwords don't match");
            errors.rejectValue("passwordConfirm", "registration.password.noMatch");
        }

    }
}