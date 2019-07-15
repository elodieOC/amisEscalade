package com.elo.oc.utils;

import com.elo.oc.entity.Spot;
import com.elo.oc.service.SpotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <p>Class validates the add and update forms for Spots</p>
 */
@Component
public class SpotRegistrationValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(SpotRegistrationValidator.class);
    @Autowired
    private SpotService spotService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Spot.class.equals(aClass);
    }

    /**
     * <p>Method adds error to bindingresult if image is too large or in wrong format, or if spot's name already exists in db</p>
     * @param o the spot being added or updated
     * @param errors error values will be added to bindingresult for messages on form
     */
    @Override
    public void validate(Object o, Errors errors) {
        Spot spot = (Spot) o;
        if (spotService.findSpotWithThisName(spot.getName()).isPresent()) {
            logger.info("spot already exists in database");
            errors.rejectValue("name", "registration.spot.duplicate");
        } else {
            logger.info("spot not found in database, ready to validate");
        }
        if(!ImageFileProcessing.checkIfImageSizeOk(spot.getImageFile())){
            errors.rejectValue("imageFile", "image.too.large");
        }
        if(!ImageFileProcessing.checkIfImageIsRightFormat(spot.getImageFile()) && spot.getImageFile().getSize() > 0){
            logger.info(spot.getImageFile().getContentType());
            errors.rejectValue("imageFile", "image.wrong.format");
        }

    }
}