package com.elo.oc.utils;

import com.elo.oc.entity.Route;
import com.elo.oc.entity.Spot;
import com.elo.oc.service.RouteService;
import com.elo.oc.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class RouteRegistrationValidator implements Validator {


    @Autowired
    private RouteService routeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Route.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        Route route = (Route) o;

        if(!ImageFileProcessing.checkIfImageSizeOk(route.getImageFile())){
            errors.rejectValue("imageFile", "image.too.large");
        }

        if(!ImageFileProcessing.checkIfImageIsRightFormat(route.getImageFile()) && route.getImageFile().getSize() > 0){
            errors.rejectValue("imageFile", "image.wrong.format");
        }

    }
}