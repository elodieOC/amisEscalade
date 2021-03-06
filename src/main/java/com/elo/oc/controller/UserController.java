package com.elo.oc.controller;

import com.elo.oc.dto.ResetPassForm;
import com.elo.oc.utils.*;
import com.elo.oc.entity.Role;
import com.elo.oc.entity.User;
import com.elo.oc.service.EmailService;
import com.elo.oc.service.RoleService;
import com.elo.oc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static com.elo.oc.utils.GeneratePassword.getAlphaNumericString;

/**
 *<h2>Controller for User purposes</h2>
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;
    @Autowired
    private UserCheckPasswordValidator userCheckPasswordValidator;
    @Autowired
    private UserUpdateEmailValidator userUpdateEmailValidator;
    @Autowired
    private UserUpdateUserNameValidator userUpdateUserNameValidator;
    @Autowired
    private UserUpdateUserRoleValidator userUpdateUserRoleValidator;
    @Autowired
    private UserLoginValidator userLoginValidator;
    @Autowired
    private ResetPasswordValidator resetPasswordValidator;
    @Autowired
    private EmailService emailService;

    /*
     **************************************
     * Registration
     * ************************************
     */
    /**
     * <p>Page that displays a form to register</p>
     * @param theModel attribute passed to jsp page
     * @return register page
     */
    @GetMapping("/inscription")
    public String showRegisterForm(Model theModel) {
        User theUser = new User();
        List<Role> roles = roleService.getRolesPublic();
        theModel.addAttribute("roles", roles);
        theModel.addAttribute("user", theUser);
        return "add-user";
    }

    /**
     * <p>Process called after the submit button is clicked on register page</p>
     * @param theUser user being created
     * @param theBindingResult the result of validation of the form
     * @param request servlet request
     * @param theModel attribute passed to jsp page
     * @return page to show depending on result of process
     */
    @PostMapping("/add-user")
    public String saveUser(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult,
                           HttpServletRequest request, Model theModel) {
        userRegistrationValidator.validate(theUser, theBindingResult);
        HttpSession session = request.getSession();
        if (theBindingResult.hasErrors()) {
            List<Role> roles = roleService.getRolesPublic();
            theModel.addAttribute("roles", roles);
            return "add-user";
        }
        else{
            userService.saveUser(theUser);
            session.setAttribute("loggedInUserEmail", theUser.getEmail());
            session.setAttribute("loggedInUserId", theUser.getId());
            String redirectString = "/user/"+session.getAttribute("loggedInUserId");
            return "redirect:"+redirectString;
        }
    }
    /*
     **************************************
     * LOGIN
     * ************************************
     */
    /**
     * <p>Page that displays a form to login a user</p>
     * @param theModel attribute passed to jsp page
     * @return login page
     */
    @GetMapping("/login")
    public String showLoginForm(Model theModel){
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "login";
    }
    /**
     * <p>Process called after the submit button is clicked on login page</p>
     * @param theUser user being logged in
     * @param theBindingResult the result of validation of the form
     * @param request servlet request
     * @return page to show depending on process result
     */
    @PostMapping("/log-user")
    public String connectUser(@ModelAttribute("user") User theUser, BindingResult theBindingResult,  HttpServletRequest request) {

        userLoginValidator.validate(theUser, theBindingResult);
        HttpSession session = request.getSession();

        if (theBindingResult.hasErrors()) {
            return "login";
        }
        else{
            User userToLogIn = userService.findUserByUsername(theUser.getUsername()).get(0);
            System.out.println("USERTOLOGIN"+userToLogIn);
            session.setAttribute("loggedInUserEmail", userToLogIn.getEmail());
            session.setAttribute("loggedInUserId", userToLogIn.getId());
            session.setAttribute("loggedInUserRole", userToLogIn.getUserRole().getId());
            String redirectString = "/user/"+userToLogIn.getId();
            return "redirect:"+redirectString;
        }
    }
    /**
     * <p>Page that displays a form to reset a password for a user</p>
     * @param theModel attribute passed to jsp page
     * @return reset password page
     */
    @GetMapping("/mot-de-passe-reset")
    public String showFormResetPassword(Model theModel){
        ResetPassForm resetPassForm = new ResetPassForm();
        theModel.addAttribute("user", resetPassForm);
        return "reset-password-user";
    }

    /**
     * <p>Process for password resetting</p>
     * <p>New password is created ans sent to email in form. User is updated with generated password.</p>
     * @param resetPassForm form model on reset page
     * @param theBindingResult  the result of validation of the form
     * @return login page
     */
    @PostMapping("/reset-password")
    public String resetPassword(@Valid @ModelAttribute("user") ResetPassForm resetPassForm, BindingResult theBindingResult, Model theModel){
        resetPasswordValidator.validate(resetPassForm, theBindingResult);
        int success = 0;
        if (theBindingResult.hasErrors()) {
            return "reset-password-user";
        }
        else{
            User theUser = userService.findUserByEmail(resetPassForm.getEmail());

            String generatedString = getAlphaNumericString(8);

            String mailTo = theUser.getEmail();
            String subject = "Réinitialisation Mot de Passe";
            String text ="Bonjour ami grimpeur!" +
                    "\n\nVoici votre mot de passe temporaire: " + generatedString +
                    "\nPensez à le changer rapidement par souci de sécurité." +
                    "\n\nCordialement," +
                    "\nLes amis de l'escalade";
            emailService.sendSimpleMessage(mailTo, subject, text);

            theUser.setPassword(generatedString);
            theUser.setPasswordConfirm(generatedString);
            theUser.setMemberOrNot(theUser.getUserRole().getId());

            userService.updateUser(theUser);
            success = 1;
            theModel.addAttribute("success", success);
            return "reset-password-user";
        }
    }

    /*
     **************************************
     * User Infos
     * ************************************
     */
    /**
     * <p>Page that displays the profile of a user</p>
     * @param theModel attribute passed to jsp page
     * @param request servlet request
     * @return page to show depending on user on the page
     */
    @GetMapping("/{userId}")
    public String showUserProfile(@PathVariable("userId") Integer userId,Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUser = userService.findUserByIdWithSpots(userId);

            if (userService.findUserByEmail(sessionEmail).getId() != userId && theUser.getUserRole().getId()!= 1 ) {
                logger.warn("User trying to access prolie is neither the owner of the comment or an admin");
                logger.warn("User is: ["+theUser.getId()+ ", "+theUser.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("user", theUser);
            theModel.addAttribute("topos", theUser.getTopos());
            theModel.addAttribute("spots", theUser.getSpots());
            return "view-user";

        }
    }
    /*
     **************************************
     * User logout
     * ************************************
     */
    /**
     * Process called after the logout button is clicked in navbar
     * @param session session
     * @return homepage
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

    /*
     **************************************
     * User update and delete
     * ************************************
     */

    /**
     * <p>Page displaying the form to update a user</p>
     * @param theId id of user to update
     * @param theModel theModel attribute passed to jsp page
     * @param request servlet request
     * @return page to show depending on user on the page
     */
    @GetMapping("/{userId}/editer")
    public String showFormForUpdate(@PathVariable("userId") Integer theId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getId() != theId){
                logger.warn("User trying to update the user is neither the user");
                logger.warn("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            User theUser = userService.findUserById(theId);
            List<Role> roles = roleService.getRolesPublic();
            theModel.addAttribute("roles", roles);
            theModel.addAttribute("user", theUser);
            return "edit-user";
        }
    }

    /**
     * <p>Process called after the submit button is clicked on the updateFormUser page</p>
     * <p>Processes the updating of the user in db</p>
     * @param userId id of user to update
     * @param theUser Entity User
     * @param theBindingResult the result of validation of the form
     * @param theModel theModel attribute passed to jsp page
     * @param request servlet request
     * @return page to show depending on result of process
     */

    @PostMapping("/{userId}/update")
    public String updateUserProfile(@PathVariable("userId") Integer userId, @ModelAttribute("user") User theUser, Model theModel, BindingResult theBindingResult, HttpServletRequest request) {
        User theUserToUpdate = userService.findUserById(userId);
        if(!theUser.getEmail().equals(theUserToUpdate.getEmail())){
            userUpdateEmailValidator.validate(theUser, theBindingResult);
        }
         if(!theUser.getUsername().equals(theUserToUpdate.getUsername())){
            userUpdateUserNameValidator.validate(theUser, theBindingResult);
        }

            userUpdateUserRoleValidator.validate(theUser, theBindingResult);

        userCheckPasswordValidator.validate(theUser, theBindingResult);
        if (theBindingResult.hasErrors()) {
            logger.warn("form has errors");
            logger.warn(theBindingResult);
            List<Role> roles = roleService.getRolesPublic();
            theModel.addAttribute("roles", roles);
            return "edit-user";
        } else {
            logger.info("form is validated");
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUserEmail", theUser.getEmail());
            userService.updateUser(theUser);
            String redirectingString = "/user/"+theUser.getId();
            return "redirect:"+redirectingString;
        }
    }

    /**
     * <p>Process called after the Supprimer button is clicked on the user profile page</p>
     * @param theId of the user to delete
     * @param request servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{userId}/delete")
    public String deleteCustomer(@PathVariable("userId") Integer theId, HttpServletRequest request) {
        userService.deleteUser(theId);
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/home";
    }
}
