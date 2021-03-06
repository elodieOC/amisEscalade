package com.elo.oc.utils;

import com.elo.oc.dto.LengthForm;
import com.elo.oc.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <p>Class validates the add and update forms for Lengths</p>
 */
@Component
public class LengthFormValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(LengthFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * <p>Method adds error to bindingresult if height isn't numeric with a '.' separator, or if bolts aren't integers</p>
     * @param o the Length being added or updated
     * @param errors error values will be added to bindingresult for messages on form
     */
    @Override
    public void validate(Object o, Errors errors) {
        LengthForm form = (LengthForm) o;
        String formHeight = ((LengthForm) o).getHeight();
        String formBolts = ((LengthForm) o).getBolts();
            if (!formHeight.matches("^\\d*\\.?\\d*$") && !formHeight.equals("")) {
                logger.info("entered data for height: "+formHeight);
                errors.rejectValue("height", "route.height.error");
            }
            if(!formBolts.matches("^\\d+$") && !formBolts.equals("")){
                logger.info("entered data for bolts: "+formBolts);
                errors.rejectValue("bolts", "route.bolts.error");
            }
        }
    }
