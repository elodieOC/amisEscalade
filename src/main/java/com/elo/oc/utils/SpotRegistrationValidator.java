package com.elo.oc.utils;

import com.elo.oc.entity.Spot;
import com.elo.oc.entity.User;
import com.elo.oc.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


@Component
public class SpotRegistrationValidator implements Validator {


    @Autowired
    private SpotService spotService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Spot.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        Spot spot = (Spot) o;

        if (spotService.findSpotWithThisName(spot.getName()).isPresent()) {
            System.out.println("spot already exists in database");
            errors.rejectValue("name", "registration.spot.duplicate");
        } else {
            System.out.println("spot not found in database, ready to validate");
        }

        if(!ImageFileProcessing.checkIfImageSizeOk(spot.getImageFile())){
            errors.rejectValue("imageFile", "image.too.large");
        }

        if(!ImageFileProcessing.checkIfImageIsRightFormat(spot.getImageFile()) && spot.getImageFile().getSize() > 0){
            System.out.println(spot.getImageFile());
            errors.rejectValue("imageFile", "image.wrong.format");
        }

    }
}