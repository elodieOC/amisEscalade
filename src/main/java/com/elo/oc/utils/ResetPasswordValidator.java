package com.elo.oc.utils;

import com.elo.oc.dto.ResetPassForm;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <p>Class validates the reset password form</p>
 */
@Component
public class ResetPasswordValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return ResetPassForm.class.equals(aClass);
    }

    /**
     * <p>Method is called in Controller for validation of form</p>
     * @param o the resetpassword form
     * @param errors error values will be added to bindingresult for messages on form
     */
    @Override
    public void validate(Object o, Errors errors) {
        ResetPassForm form = (ResetPassForm) o;
        String formEmail = form.getEmail();
            if (!formEmail.equals("") && !userService.findUserWithThisEmail(formEmail).isPresent()) {
                System.out.println("Email does not exists in database");
                errors.rejectValue("email", "user.email.unknown");
            }

        }
    }
