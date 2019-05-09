package com.elo.oc.controller;

import com.elo.oc.entity.Spot;
import com.elo.oc.entity.User;
import com.elo.oc.entity.Comment;
import com.elo.oc.service.CommentService;
import com.elo.oc.service.SpotService;
import com.elo.oc.service.UserService;
import com.elo.oc.utils.SpotRegistrationValidator;
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
@RequestMapping("/spots")
public class SpotController {

    @Autowired
    private SpotService spotService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private SpotRegistrationValidator spotRegistrationValidator;

    @GetMapping("/list")
    public String listSpots(Model theModel) {
        List<Spot> theSpots = spotService.getSpots();
        theModel.addAttribute("spots", theSpots);
        return "list-spots";
    }

    @GetMapping("/ajoutSpot")
    public String showFormForAdd(Model theModel, HttpServletRequest request) {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if (session.getAttribute("loggedInUserEmail") == null) {
            System.out.println("user not logged in, redirect to login");
            return "redirect:/user/login";
        } else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            System.out.println("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");
            Spot theSpot = new Spot();
            theModel.addAttribute("spot", theSpot);
            return "spot-register";}
    }

    @GetMapping("/{spotId}")
    public String viewSpot(@PathVariable("spotId") Integer spotId, Model theModel, HttpServletRequest request){
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") != null) {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUser = userService.findUserByEmail(sessionEmail);
            theModel.addAttribute("user", theUser);
        }
        Spot theSpot = spotService.findSpotById(spotId);
        theModel.addAttribute("spot", theSpot);
        theModel.addAttribute("comments", theSpot.getComments());
        return "view-spot";
    }


    @GetMapping("/{spotId}/commenter")
    public String addCommentToSpot(@PathVariable("spotId") Integer spotId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loggedInUserEmail") == null) {
            System.out.println("user not logged in, redirect to login");
            return "redirect:/user/login";
        } else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            Spot theSpot = spotService.findSpotById(spotId);
            Comment theComment = new Comment();
            theComment.setSpot(theSpot);
            User theUser = userService.findUserByEmail(sessionEmail);
            theModel.addAttribute("spot", theSpot);
            theModel.addAttribute("comment", theComment);
            theModel.addAttribute("user", theUser);
            return "add-comment-toSpot";
        }
    }



    @PostMapping("/saveSpot")
    public String saveSpot(@Valid @ModelAttribute("spot") Spot theSpot, BindingResult theBindingResult,
                           HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        if(session.getAttribute("loggedInUserEmail") == null){
            System.out.println("user not logged in, redirect to login");
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            System.out.println("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");

            spotRegistrationValidator.validate(theSpot, theBindingResult);

            if (theBindingResult.hasErrors()) {
                System.out.println("form has errors");
                return "spot-register";
            } else {
                System.out.println("form is validated");
                theSpot.setUser(userService.findUserByEmail(sessionEmail));
                System.out.println(theSpot.toString());
                spotService.saveSpot(theSpot);
                return "redirect:list";
            }
        }
    }


    @PostMapping("{spotId}/saveComment")
    public String saveComment(@PathVariable("spotId") Integer spotId, @Valid @ModelAttribute("comment") Comment theComment, BindingResult theBindingResult,
                           HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        if(session.getAttribute("loggedInUserEmail") == null){
            System.out.println("user not logged in, redirect to login");
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            System.out.println("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");

            if (theBindingResult.hasErrors()) {
                System.out.println("form has errors");
                return "add-comment-toSpot";
            } else {
                System.out.println("form is validated");
                theComment.setUser(userService.findUserByEmail(sessionEmail));
                theComment.setSpot(spotService.findSpotById(spotId));
                commentService.saveComment(theComment);
                return "redirect:/spots/"+spotId;
            }
        }
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("spotId") Integer theId, Model theModel,
                                    HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") == null){
            System.out.println("user not logged in, redirect to login");
            return "redirect:/user/login";
        }
        else {
            Spot theSpot = spotService.findSpotById(theId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 || theUpdater.getId() != theSpot.getUser().getId()){
                System.out.println("User trying to update is neither the owner of the spot, or an admin");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("spot", theSpot);
            return "spot-register";
        }
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("spotId") Integer theId, HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") == null){
            System.out.println("user not logged in, redirect to login");
            return "redirect:/user/login";
        }
        Spot theSpot = spotService.findSpotById(theId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 || theDeleter.getId() != theSpot.getUser().getId()){
            System.out.println("User trying to delete is neither the owner of the spot, or an admin");
            System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        spotService.deleteSpot(theId);
        return "redirect:list";
    }
}