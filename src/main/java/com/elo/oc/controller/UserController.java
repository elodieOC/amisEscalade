package com.elo.oc.controller;

import com.elo.oc.entity.User;
import com.elo.oc.service.RoleService;
import com.elo.oc.service.UserService;
import com.elo.oc.utils.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @GetMapping("/register")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "user-register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult) {
        userRegistrationValidator.validate(theUser, theBindingResult);

        if (theBindingResult.hasErrors()) {
            return "user-register";
        }
        else{
            if(theUser.getMemberOrNot().equals("no")) {
                theUser.setUserRole(roleService.findById(3));
            }
            else{
                theUser.setUserRole(roleService.findById(2));
            }
            userService.saveUser(theUser);
            return "redirect:register";
        }
    }
}
