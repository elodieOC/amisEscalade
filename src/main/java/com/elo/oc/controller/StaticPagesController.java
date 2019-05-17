package com.elo.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Controller for static pages</p>
 */
@Controller
@RequestMapping("/")
public class StaticPagesController {

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
    public String contact(){
        return "contact";
    }
}
