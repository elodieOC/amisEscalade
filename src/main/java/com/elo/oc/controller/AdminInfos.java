package com.elo.oc.controller;

import com.elo.oc.entity.Role;
import com.elo.oc.entity.User;
import com.elo.oc.entity.UserAdmin;
import com.elo.oc.service.RoleService;
import com.elo.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminInfos {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping("/infos")
    public String createInit(Model theModel) {

        if(!roleService.findRole("admin").isPresent() ) {
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
        }
        if(!userService.findUserWithThisUsername("hellojito").isPresent()){
            User user = new User();
            user.setEmail("hellojito@gmail.com");
            user.setPassword("kignou");
            user.setPasswordConfirm("kignou");
            user.setUsername("hellojito");
            user.setUserRole(roleService.findById(1));
            userService.saveUser(user);

            User user2 = new User();
            user2.setEmail("cookie@gmail.com");
            user2.setPassword("kignou");
            user2.setPasswordConfirm("kignou");
            user2.setUsername("cookie");
            user2.setUserRole(roleService.findById(2));
            userService.saveUser(user2);

            User user3 = new User();
            user3.setEmail("loki@gmail.com");
            user3.setPassword("kignou");
            user3.setPasswordConfirm("kignou");
            user3.setUsername("loki");
            user3.setUserRole(roleService.findById(3));
            userService.saveUser(user3);

        }


        List<User> theUsers = userService.getUsers();
        List<Role> theRoles = roleService.getRoles();

        theModel.addAttribute("roles", theRoles);
        theModel.addAttribute("users", theUsers);

        return "admin-infos";
    }
}
