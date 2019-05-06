package com.elo.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Statics {

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }

    @GetMapping("contact")
    public String contact(){
        return "contact";
    }
}
