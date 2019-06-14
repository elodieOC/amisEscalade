package com.elo.oc.utils;

import com.elo.oc.entity.Topo;
import com.elo.oc.service.SpotService;
import com.elo.oc.service.TopoService;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
/**
 * <p>Class validates the add and update forms for topos</p>
 */
@Component
public class TopoRegistrationValidator implements Validator {


    @Autowired
    private TopoService topoService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Topo.class.equals(aClass);
    }

    /**
     * <p>Method adds error to bindingresult if image is too large or in wrong format or if date isn't valid</p>
     * @param o the topo being added or updated
     * @param errors error values will be added to bindingresult for messages on form
     */
    @Override
    public void validate(Object o, Errors errors) {
        Topo topo = (Topo) o;
        String dateForm = topo.getDateRelease();
        //date regex for valid format dd-mm-yyyy, accounts for number of days in months including february
        String datecheckRegex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        if (!dateForm.matches(datecheckRegex)) {
            System.out.println("Date format invalid");
            errors.rejectValue("dateRelease", "registration.topo.date");
        }
        if(!ImageFileProcessing.checkIfImageSizeOk(topo.getImageFile())){
            errors.rejectValue("imageFile", "image.too.large");
        }
        if(!ImageFileProcessing.checkIfImageIsRightFormat(topo.getImageFile()) && topo.getImageFile().getSize() > 0){
            errors.rejectValue("imageFile", "image.wrong.format");
        }
    }
}