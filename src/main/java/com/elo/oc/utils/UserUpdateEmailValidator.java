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
 * <p>Class validates the User update form when the email is changed</p>
 */
@Component
public class UserUpdateEmailValidator implements Validator {
    private static final Logger logger = LogManager.getLogger(UserUpdateEmailValidator.class);

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "NotBlank");
        if (userService.findUserWithThisEmail(user.getEmail()).isPresent()) {
            logger.info("email already exists in database");
            errors.rejectValue("email", "registration.email.duplicate");
        }

    }
}