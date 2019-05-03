package com.elo.oc.controller;

import com.elo.oc.entity.User;
import com.elo.oc.entity.User;
import com.elo.oc.service.RoleService;
import com.elo.oc.service.UserService;
import com.elo.oc.utils.UserLoginValidator;
import com.elo.oc.utils.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private UserLoginValidator userLoginValidator;

    @GetMapping("/register")
    public String showRegisterForm(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "user-register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult,
                           HttpServletRequest request, HttpSession session) {
        userRegistrationValidator.validate(theUser, theBindingResult);
        session = request.getSession();
        if (theBindingResult.hasErrors()) {
            return "user-register";
        }
        else{
            userService.saveUser(theUser);
            session.setAttribute("loggedInUserEmail", theUser.getEmail());
            return "redirect:profile/"+theUser.getId();
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model theModel){
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "user-login";
    }

    @PostMapping("/logUser")
    public String connectUser(@ModelAttribute("user") User theUser, BindingResult theBindingResult,
                              HttpServletRequest request, HttpSession session) {

        userLoginValidator.validate(theUser, theBindingResult);
        session = request.getSession();

        if (theBindingResult.hasErrors()) {
            return "user-login";
        }
        else{
            User userToLogIn = userService.findByUsername(theUser.getUsername());
            session.setAttribute("loggedInUserEmail", userToLogIn.getEmail());
            return "redirect:profile/"+userToLogIn.getId();
        }
    }

    @GetMapping("profile/{userId}")
    public String showUserProfile(@PathVariable("userId") int userId, User theUser, Model theModel, HttpServletRequest request) {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        } else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            if(!sessionEmail.equals(userService.findById(userId).getEmail())){
                return "redirect:/user/login";
            }
            else {
                theUser = userService.findById(userId);
                theModel.addAttribute("user", theUser);
                theModel.addAttribute("spots", theUser.getSpots());
                return "profile";
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {
        User theUser = userService.findById(theId);
        theModel.addAttribute("user", theUser);
        return "user-register";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int theId) {
        userService.deleteUser(theId);
        return "redirect:/home";
    }
}
