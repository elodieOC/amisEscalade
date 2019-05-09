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

/**
 *<h2>Controller for Admin purposes</h2>
 */
@Controller
@RequestMapping("/admin")
public class AdminInfos {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    /**
     * <p>Page admin/infos</p>
     * <p>Where an admin can see lists of different infos (list of users, list of roles...)</p>
     * @param theModel attribute passed to jsp page
     * @param request servlet request
     * @param session servlet session
     * @return page to show depending on user on the page
     */
    @GetMapping("/infos")
    public String createInit(Model theModel, HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        //if no user is connected: back to login page
        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        }
        //if a user is connected:
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theAccessor = userService.findByEmail(sessionEmail);
            //if the user is not an admin: back to homepage
            if(theAccessor.getUserRole().getId()!=1){
                System.out.println("User trying to access the admin infos is not an admin");
                System.out.println("User is: ["+theAccessor.getId()+ ", "+theAccessor.getUsername()+"]");
                return "redirect:/home";
            }
            else {
                //fetches the lists in Services
                List<User> theUsers = userService.getUsers();
                List<Role> theRoles = roleService.getRoles();
                //adding attributes to Model to display on jsp
                theModel.addAttribute("roles", theRoles);
                theModel.addAttribute("users", theUsers);
                return "admin-infos";
            }
        }
    }

    /**
     *<p>Page admin/user/{userId}/profile</p>
     * <p>Where an admin can access the profile of a user, update and delete it
     * <p>after clicking "update" on a user line in the list of users appearing in admin/infos page</p>
     * @param userId theId id of the user transmitted by the clicking on 'view' in admin/infos page
     * @param theModel attribute passed to jsp page
     * @param request servlet request
     * @param session servlet session
     * @return page to show depending on user on the page
     */
    @GetMapping("/user/{userId}/profile")
    public String showUserProfile(@PathVariable("userId") int userId, Model theModel,
                                  HttpServletRequest request, HttpSession session) {
            session = request.getSession();
        //if no user is connected: back to login page
        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        }
        //if a user is connected:
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theAccessor = userService.findByEmail(sessionEmail);
            //if the user is not an admin: back to homepage
            if(theAccessor.getUserRole().getId()!=1){
                System.out.println("User trying to access the user profile is not an admin");
                System.out.println("User is: ["+theAccessor.getId()+ ", "+theAccessor.getUsername()+"]");
                return "redirect:/home";
            }
            //fetches the User with its id, and adds attributes to Model to display on jsp
            else {
                    User theUser = userService.findByIdWithSpots(userId);
                    theModel.addAttribute("user", theUser);
                    theModel.addAttribute("spots", theUser.getSpots());
                    return "admin-user-profile";
                }
            }
        }

    /**
     * <p>Page admin//user/{userId}/updateForm</p>
     * <p>Where an admin can update the Role of a user
     * <p>after clicking "update" on a user's profile (admin/user/{userId}/profile page)</p>
     * @param userId id of the user transmitted by the clicking on 'update'
     * @param theModel attribute passed to jsp page
     * @param request servlet request
     * @param session servlet session
     * @return page to show depending on user on the page
     */
    @GetMapping("/user/{userId}/updateForm")
    public String showFormForUpdate(@PathVariable("userId") int userId, Model theModel,
                                    HttpServletRequest request, HttpSession session) {
        session = request.getSession();
//if no user is connected: back to login page
        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        }  //if a user is connected:
         else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUpdater = userService.findByEmail(sessionEmail);
            //if the user is not an admin: back to homepage
            if (theUpdater.getUserRole().getId() != 1) {
                System.out.println("User trying to update is not an admin");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            //fetches the User with its id, and adds attributes to Model to display on jsp
            else {
                User theUser = userService.findById(userId);
                List<Role> roles = roleService.getRoles();
                theModel.addAttribute("roles", roles);
                theModel.addAttribute("user", theUser);
                return "admin-user-update";
            }
        }
    }

    /**
     * <p>Page admin/user/{userId}/saveUser</p>
     * <p>Where the app confirms or not the update
     * <p>after confirming updating (admin/user/{userId}/updateForm page)</p>
     * @param theUser the user being updated
     * @param theBindingResult the result of validation of the update
     * @return profile page if update valid, stays on updateForm if not
     */
    @PostMapping("/user/{userId}/saveUser")
    public String updateUser(@PathVariable("userId") int userId, @Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            //TODO la liste des roles ne se recharge pas lorsqu'il y a erreur
            return "admin-user-update";
        }
        else {
            userService.adminSaveUser(theUser);
            return "redirect:profile";
        }
    }

    /**
     * <p>Page admin/user/{userId}/delete</p>
     * <p>Where an admin can delete a user
     * <p>after clicking "delete" on a user's profile (admin/user/{userId}/profile page)</p>
     * @param userId id of the user transmitted by the clicking on 'update'
     * @param request servlet request
     * @param session servlet session
     * @return page to show depending on user on the page, admin/infos if deletion successful
     */
    @GetMapping("/user/{userId}/delete")
    public String deleteCustomer(@PathVariable("userId") int userId, HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        if (session.getAttribute("loggedInUserEmail") == null) {
            return "redirect:/user/login";
        } else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theDeleter = userService.findByEmail(sessionEmail);
            if (theDeleter.getUserRole().getId() != 1) {
                System.out.println("User trying to delete is not an admin");
                System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
                return "redirect:/home";
            } else {
                userService.deleteUser(userId);
                return "redirect:infos";
            }
        }
    }
}
