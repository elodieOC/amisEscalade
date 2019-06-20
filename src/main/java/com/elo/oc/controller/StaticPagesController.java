package com.elo.oc.controller;

import com.elo.oc.entity.NewsletterSuscriber;
import com.elo.oc.service.NewletterSuscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * <p>Controller for static pages</p>
 */
@Controller
@RequestMapping("/")
public class StaticPagesController {

    @Autowired
    private NewletterSuscriberService suscriberService;

    /**
     * <p>Homepage</p>
     * @return Homepage
     */
    @GetMapping("home")
    public String home(){
        return "home";
    }
    /**
     * <p>about page</p>
     * @return about page
     */
    @GetMapping("about")
    public String about(){
        return "about";
    }
    /**
     * <p>contact page</p>
     * @return contact page
     */
    @GetMapping("contact")
    public String contact(Model theModel){
        NewsletterSuscriber newsletterSuscriber = new NewsletterSuscriber();
        theModel.addAttribute("suscriber", newsletterSuscriber);
        return "contact";
    }

    @PostMapping("news")
    public String newsSuscribe(@Valid @ModelAttribute("suscriber") NewsletterSuscriber newsletterSuscriber, BindingResult theBindingResult, Model theModel){
        int emailSuscribed = 0;
        if (suscriberService.findUserWithThisEmail(newsletterSuscriber.getEmail()).isPresent()) {
          emailSuscribed = 1;
        }
        int success = 0;
        if (theBindingResult.hasErrors() || emailSuscribed == 1) {
            System.out.println("form has errors");
            System.out.println(theBindingResult);
            theModel.addAttribute("warning", emailSuscribed);
            return "contact";
        }
        else{
            System.out.println("form is validated");
            success = 1;
            suscriberService.saveNewsletterSuscriber(newsletterSuscriber);
            theModel.addAttribute("success", success);
        }
        return "contact";
    }
}
