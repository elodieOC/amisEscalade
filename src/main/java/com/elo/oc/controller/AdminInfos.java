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
    public String createInit(Model theModel) {

       /* if(!roleService.findRole("admin").isPresent() ) {
            Role admin = new Role();
            admin.setRoleName("admin");
            roleService.saveRole(admin);
        }
        if(!roleService.findRole("membre asso").isPresent() ) {
            Role assoMember = new Role();
            assoMember.setRoleName("membre asso");
            roleService.saveRole(assoMember);
        }
        if(!roleService.findRole("non membre").isPresent()) {
            Role nonMember = new Role();
            nonMember.setRoleName("non membre");
            roleService.saveRole(nonMember);
        }*/

        List<User> theUsers = userService.getUsers();
        List<Role> theRoles = roleService.getRoles();

        theModel.addAttribute("roles", theRoles);
        theModel.addAttribute("users", theUsers);

        return "admin-infos";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {
        User theUser = userService.findById(theId);
        List<Role> roles = roleService.getRoles();
        theModel.addAttribute("roles", roles);
        theModel.addAttribute("user", theUser);
        return "admin-user-update";
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
    public String deleteCustomer(@RequestParam("id") int theId) {
        userService.deleteUser(theId);
        return "redirect:infos";
    }
}
