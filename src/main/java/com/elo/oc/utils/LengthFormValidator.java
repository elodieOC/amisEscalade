package com.elo.oc.utils;

import com.elo.oc.dto.LengthForm;
import com.elo.oc.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LengthFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        LengthForm form = (LengthForm) o;

        String formHeight = ((LengthForm) o).getHeight();
        String formBolts = ((LengthForm) o).getBolts();


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
