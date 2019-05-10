package com.elo.oc.controller;

import com.elo.oc.entity.Sector;
import com.elo.oc.entity.Spot;
import com.elo.oc.entity.User;
import com.elo.oc.entity.Comment;
import com.elo.oc.service.CommentService;
import com.elo.oc.service.SectorService;
import com.elo.oc.service.SpotService;
import com.elo.oc.service.UserService;
import com.elo.oc.utils.SessionCheck;
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
    private SectorService sectorService;
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

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
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
        Spot theSpot = spotService.findSpotWithAllInfosById(spotId);
        theModel.addAttribute("spot", theSpot);
        theModel.addAttribute("comments", theSpot.getComments());
        theModel.addAttribute("sectors", theSpot.getSectors());
        return "view-spot";
    }

    @GetMapping("/{spotId}/{sectorId}")
    public String viewSector(@PathVariable("spotId") Integer spotId,@PathVariable("sectorId") Integer sectorId, Model theModel, HttpServletRequest request){
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") != null) {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUser = userService.findUserByEmail(sessionEmail);
            theModel.addAttribute("user", theUser);
        }
        Spot theSpot = spotService.findSpotWithAllInfosById(spotId);
        Sector theSector = sectorService.findSectorById(sectorId);
        theModel.addAttribute("spot", theSpot);
        theModel.addAttribute("sector", theSector);
        return "view-sector";
    }


    @GetMapping("/{spotId}/commenter")
    public String addCommentToSpot(@PathVariable("spotId") Integer spotId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
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

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
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

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
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
    @GetMapping("/{spotId}/ajoutSecteur")
    public String addSectorToSpot(@PathVariable("spotId") Integer spotId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Spot theSpot = spotService.findSpotById(spotId);
            Sector theSector = new Sector();
            theModel.addAttribute("spot", theSpot);
            theModel.addAttribute("sector", theSector);
            return "add-sector-toSpot";
        }
    }
    @PostMapping("{spotId}/saveSector")
    public String saveSector(@PathVariable("spotId") Integer spotId, @Valid @ModelAttribute("sector") Sector theSector, BindingResult theBindingResult,
                              HttpServletRequest request, HttpSession session) {
        session = request.getSession();

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            System.out.println("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");

            if (theBindingResult.hasErrors()) {
                System.out.println("form has errors");
                return "add-sector-toSpot";
            } else {
                System.out.println("form is validated");
                theSector.setUser(userService.findUserByEmail(sessionEmail));
                theSector.setSpot(spotService.findSpotById(spotId));
                sectorService.saveSector(theSector);
                return "redirect:/spots/"+spotId;
            }
        }
    }

    @GetMapping("/deleteComment")
    public String deleteCommentFromSpot(@RequestParam("spotId") Integer theSpotId,@RequestParam("commentId") Integer theCommentId,
                                        HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        Spot theSpot = spotService.findSpotById(theSpotId);
        Comment theComment = commentService.findCommentById(theCommentId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getUserRole().getId()!= 2 && theDeleter.getId() != theComment.getUser().getId()){
            System.out.println("User trying to delete comment is neither the owner of the comment, an admin, or a member of the association");
            System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        commentService.deleteComment(theCommentId);
        return "redirect:/spots/"+theSpotId;
    }

    @GetMapping("{spotId}/updateFormSpot")
    public String formForSpotUpdate(@PathVariable("spotId") Integer theId, Model theModel,
                                    HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Spot theSpot = spotService.findSpotById(theId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getId() != theSpot.getUser().getId()){
                System.out.println("User trying to update is neither the owner of the spot, or an admin");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("spot", theSpot);
            return "spot-update";
        }
    }

    @PostMapping("{spotId}/updateSpot")
    public String updateSpot(@PathVariable("spotId") Integer spotId, @Valid @ModelAttribute("spot") Spot theSpot, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            System.out.println("form has errors");
            return "spot-update";
        } else {
            System.out.println("form is validated");
            Spot spotToUpdate = spotService.findSpotById(spotId);
            User spotUser =  userService.findUserById(spotToUpdate.getUser().getId());
            theSpot.setUser(spotUser);
            spotService.updateSpot(theSpot);
            return "redirect:/spots/"+spotId;
        }
    }


    @GetMapping("{spotId}/{commentId}/updateFormComment")
    public String formForCommentUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("commentId") Integer theCommentId, Model theModel,
                                       HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Spot theSpot = spotService.findSpotById(theSpotId);
            Comment theComment = commentService.findCommentById(theCommentId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getUserRole().getId()!= 2 && theUpdater.getId() != theSpot.getUser().getId()){
                System.out.println("User trying to update the comment is neither the owner of the spot, an admin or a member of the association");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("comment", theComment);
            return "comment-edit";
        }
    }

    @PostMapping("{spotId}/{commentId}/updateComment")
    public String updateComment(@PathVariable("spotId") Integer spotId,  @PathVariable("commentId") Integer theCommentId,@Valid @ModelAttribute("comment") Comment theComment, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            System.out.println("form has errors");
            return "comment-edit";
        } else {
            System.out.println("form is validated");
            Comment theCommentToUpdate = commentService.findCommentById(theCommentId);
            theComment.setSpot(theCommentToUpdate.getSpot());
            theComment.setDate(theCommentToUpdate.getDate());
            theComment.setUser(theCommentToUpdate.getUser());
            commentService.updateComment(theComment);
            return "redirect:/spots/"+spotId;
        }
    }

    @GetMapping("{spotId}/delete")
    public String deleteCustomer(@PathVariable("spotId") Integer theId, HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        Spot theSpot = spotService.findSpotById(theId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getId() != theSpot.getUser().getId()){
            System.out.println("User trying to delete is neither the owner of the spot, or an admin");
            System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        spotService.deleteSpot(theId);
        return "redirect:/spots/list";
    }
}