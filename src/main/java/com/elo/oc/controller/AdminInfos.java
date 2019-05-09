package com.elo.oc.controller;

import com.elo.oc.entity.Role;
import com.elo.oc.entity.User;
import com.elo.oc.service.RoleService;
import com.elo.oc.service.UserService;
import com.elo.oc.utils.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminInfos {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @GetMapping("/infos")
    public String createInit(Model theModel, HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        } else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User adminUser = userService.findByEmail(sessionEmail);
            if(adminUser.getUserRole().getId()!=1){
                return "redirect:/user/profile";
            }
            else {
                List<User> theUsers = userService.getUsers();
                List<Role> theRoles = roleService.getRoles();
                theModel.addAttribute("roles", theRoles);
                theModel.addAttribute("users", theUsers);
                return "admin-infos";
            }
        }
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("id") int theId, Model theModel, HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        } else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User adminUser = userService.findByEmail(sessionEmail);
            if (adminUser.getUserRole().getId() != 1) {
                return "redirect:/user/profile";
            } else {
                User theUser = userService.findById(theId);
                List<Role> roles = roleService.getRoles();
                theModel.addAttribute("roles", roles);
                theModel.addAttribute("user", theUser);
                return "admin-user-update";
            }
        }
    }

    @PostMapping("/saveUser")
    public String updateUser(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "admin-user-update";
        }
        else {
            userService.adminSaveUser(theUser);
            return "redirect:infos";
        }
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int theId, HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        } else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User adminUser = userService.findByEmail(sessionEmail);
            if (adminUser.getUserRole().getId() != 1) {
                return "redirect:/user/profile";
            } else {
                userService.deleteUser(theId);
                return "redirect:infos";
            }
        }
    }
}
