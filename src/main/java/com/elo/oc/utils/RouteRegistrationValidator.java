package com.elo.oc.utils;

import com.elo.oc.entity.Route;
import com.elo.oc.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <p>Class validates the add and update forms for Routes</p>
 */
@Component
public class RouteRegistrationValidator implements Validator {


    @Autowired
    private RouteService routeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Route.class.equals(aClass);
    }

    /**
     * <p>Method adds error to bindingresult if image is too large or in wrong format</p>
     * @param o the Route being added or updated
     * @param errors error values will be added to bindingresult for messages on form
     */
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