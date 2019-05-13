package com.elo.oc.utils;

import com.elo.oc.entity.RouteForm;
import com.elo.oc.entity.User;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RouteFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        RouteForm form = (RouteForm) o;

        String formHeight = ((RouteForm) o).getHeight();
        String formBolts = ((RouteForm) o).getBolts();


            if (!formHeight.matches("^\\d*\\.?\\d*$") && !formHeight.equals("")) {
                System.out.println("entered data for height: "+formHeight);
                errors.rejectValue("height", "route.height.error");
            }

            if(!formBolts.matches("^\\d+$")&& !formBolts.equals("")){
                System.out.println("entered data for bolts: "+formBolts);
                errors.rejectValue("bolts", "route.bolts.error");
            }

        }
    }
