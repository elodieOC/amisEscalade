package com.elo.oc.utils;

import com.elo.oc.dto.ResetPassForm;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResetPasswordValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return ResetPassForm.class.equals(aClass);
    }


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
