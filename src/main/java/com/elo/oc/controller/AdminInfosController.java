package com.elo.oc.controller;

import com.elo.oc.entity.*;
import com.elo.oc.service.*;
import com.elo.oc.utils.SessionCheck;
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
public class AdminInfosController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private SpotService spotService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    /**
     * <p>Page admin/infos</p>
     * <p>Where an admin can see lists of different infos (list of users, list of roles...)</p>
     * @param theModel attribute passed to jsp page
     * @param request servlet request
     * @return page to show depending on user on the page
     */
    @GetMapping("/infos")
    public String createInit(Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();

        //if no user is connected: back to login page
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        //if a user is connected:
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theAccessor = userService.findUserByEmail(sessionEmail);
            //if the user is not an admin: back to homepage
            if(!SessionCheck.checkIfUserIsAdmin(theAccessor)){
                return "redirect:/home";
            }
            else {
                //fetches the lists in Services
                List<User> theUsers = userService.getUsers();
                List<Role> theRoles = roleService.getRoles();
                List<Spot> theSpots = spotService.getSpots();
                List<Grade> theGrades = gradeService.getGrades();
                List<Rating> theRatings = ratingService.getRatings();
                //adding attributes to Model to display on jsp
                theModel.addAttribute("roles", theRoles);
                theModel.addAttribute("users", theUsers);
                theModel.addAttribute("spots", theSpots);
                theModel.addAttribute("grades", theGrades);
                theModel.addAttribute("ratings", theRatings);
                return "admin-list-infos";
            }
        }
    }

    /**
     *<p>Page admin/user/{userId}</p>
     * <p>Where an admin can access the profile of a user, update and delete it
     * <p>after clicking "update" on a user line in the list of users appearing in admin/infos page</p>
     * @param userId theId id of the user transmitted by the clicking on 'view' in admin/infos page
     * @param theModel attribute passed to jsp page
     * @param request servlet request
     * @return page to show depending on user on the page
     */
    @GetMapping("/user/{userId}/profile")
    public String showUserProfile(@PathVariable("userId") Integer userId, Model theModel,
                                  HttpServletRequest request) {
           HttpSession session = request.getSession();
        //if no user is connected: back to login page
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        //if a user is connected:
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theAccessor = userService.findUserByEmail(sessionEmail);
            //if the user is not an admin: back to homepage
            if(!SessionCheck.checkIfUserIsAdmin(theAccessor)){
                return "redirect:/home";
            }
            //fetches the User with its id, and adds attributes to Model to display on jsp
            else {
                    User theUser = userService.findUserByIdWithSpots(userId);
                    theModel.addAttribute("user", theUser);
                    theModel.addAttribute("spots", theUser.getSpots());
                    theModel.addAttribute("topos", theUser.getTopos());
                    return "admin-view-user";
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
     * @return page to show depending on user on the page
     */
    @GetMapping("/user/{userId}/editer")
    public String showFormForUpdate(@PathVariable("userId") Integer userId, Model theModel,
                                    HttpServletRequest request) {
       HttpSession session = request.getSession();
        //if no user is connected: back to login page
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        //if a user is connected:
         else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            //if the user is not an admin: back to homepage
            if(!SessionCheck.checkIfUserIsAdmin(theUpdater)){
                return "redirect:/home";
            }
            //fetches the User with its id, and adds attributes to Model to display on jsp
            else {
                User theUser = userService.findUserById(userId);
                List<Role> roles = roleService.getRoles();
                theModel.addAttribute("roles", roles);
                theModel.addAttribute("user", theUser);
                return "admin-edit-user";
            }
        }
    }

    /**
     * <p>Page admin/user/{userId}/update</p>
     * <p>Where the app confirms or not the update
     * <p>after confirming updating (admin/user/{userId}/updateForm page)</p>
     * @param theUser the user being updated
     * @param theBindingResult the result of validation of the update
     * @return profile page if update valid, stays on updateForm if not
     */
    @PostMapping("/user/{userId}/update")
    public String updateUser(@PathVariable("userId") Integer userId, @Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult, Model theModel) {

        if (theBindingResult.hasErrors()) {
            List<Role> roles = roleService.getRoles();
            theModel.addAttribute("roles", roles);
            return "admin-edit-user";
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
     * @return page to show depending on user on the page, admin/infos if deletion successful
     */
    @GetMapping("/user/{userId}/delete")
    public String deleteCustomer(@PathVariable("userId") Integer userId, HttpServletRequest request) {
      HttpSession session = request.getSession();

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theDeleter = userService.findUserByEmail(sessionEmail);
            if(!SessionCheck.checkIfUserIsAdmin(theDeleter)){
                return "redirect:/home";
            }
            else {
                userService.deleteUser(userId);
                return "redirect:/admin/infos";
            }
        }
    }
}
