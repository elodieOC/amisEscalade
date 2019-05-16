package com.elo.oc.controller;

import com.elo.oc.dto.LengthForm;
import com.elo.oc.entity.*;
import com.elo.oc.entity.Sector;
import com.elo.oc.service.*;
import com.elo.oc.utils.LengthFormValidator;
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
    private GradeService gradeService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private LengthService lengthService;
    @Autowired
    private SpotRegistrationValidator spotRegistrationValidator;
    @Autowired
    private LengthFormValidator lengthFormValidator;

    @GetMapping("/list")
    public String listSpots(Model theModel) {
        List<Spot> theSpots = spotService.getSpots();
        theModel.addAttribute("spots", theSpots);
        return "list-spots";
    }

    /*
    **************************************
    * Spots
    * ************************************
     */
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
                return "redirect:/spots/spot/"+theSpot.getId();
            }
        }
    }

    @GetMapping("/spot/{spotId}")
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

    @GetMapping("/spot/{spotId}/updateFormSpot")
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

    @PostMapping("/spot/{spotId}/updateSpot")
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
            return "redirect:/spots/spot/"+spotId;
        }
    }
    @GetMapping("/spot/{spotId}/delete")
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
    /*
     **************************************
     * Sectors
     * ************************************
     */

    @GetMapping("/spot/{spotId}/ajoutSecteur")
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

    @PostMapping("/spot/{spotId}/saveSector")
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

                String redirectingString = "/spots/spot/"+spotId+"/sector/"+theSector.getId();
                return "redirect:"+redirectingString;
            }
        }
    }


    @GetMapping("/spot/{spotId}/sector/{sectorId}")
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

    @GetMapping("/spot/{spotId}/sector/{sectorId}/updateFormSector")
    public String formForSectorUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId, Model theModel,
                                      HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Spot theSpot = spotService.findSpotById(theSpotId);
            Sector theSector = sectorService.findSectorById(theSectorId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getId() != theSector.getUser().getId()){
                System.out.println("User trying to update the sector is neither the owner of the sector or an admin");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("sector", theSector);
            return "sector-edit";
        }
    }


    @PostMapping("/spot/{spotId}/sector/{sectorId}/updateSector")
    public String updateSector(@PathVariable("spotId") Integer spotId,  @PathVariable("sectorId") Integer theSectorId,@Valid @ModelAttribute("sector") Sector theSector, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            System.out.println("form has errors");
            return "sector-edit";
        } else {
            System.out.println("form is validated");
            Sector theSectorToUpdate = sectorService.findSectorById(theSectorId);
            theSector.setSpot(theSectorToUpdate.getSpot());
            theSector.setUser(theSectorToUpdate.getUser());
            sectorService.updateSector(theSector);

            String redirectingString = "/spots/spot/"+spotId+"/sector/"+theSector.getId();
            return "redirect:"+redirectingString;
        }
    }
    @GetMapping("/spot/{spotId}/sector/{sectorId}/deleteSector")
    public String deleteSectorFromSpot(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,
                                       HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        Spot theSpot = spotService.findSpotById(theSpotId);
        Sector theSector = sectorService.findSectorById(theSectorId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getId() != theSector.getUser().getId()){
            System.out.println("User trying to delete sector is neither the owner of the comment or an admin");
            System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        sectorService.deleteSector(theSectorId);
        return "redirect:/spots/spot/"+theSpotId;
    }

    /*
     **************************************
     * Comments
     * ************************************
     */

    @GetMapping("/spot/{spotId}/commenter")
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

    @PostMapping("/spot/{spotId}/saveComment")
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
                return "redirect:/spots/spot/"+spotId;
            }
        }
    }
    @GetMapping("/spot/{spotId}/comment/{commentId}/updateFormComment")
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

    @PostMapping("/spot/{spotId}/comment/{commentId}/updateComment")
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
            return "redirect:/spots/spot/"+spotId;
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
        return "redirect:/spots/spot/"+theSpotId;
    }

    /*
     **************************************
     * Routes
     * ************************************
     */
    @GetMapping("/spot/{spotId}/sector/{sectorId}/ajoutVoie")
    public String addRouteToSector(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId,
                                  Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Spot theSpot = spotService.findSpotById(spotId);
            List<Grade>grades = gradeService.getGrades();
            Sector theSector = sectorService.findSectorById(sectorId);
            Route form = new Route();

            theModel.addAttribute("grades", grades );
            theModel.addAttribute("spot", theSpot);
            theModel.addAttribute("sector", theSector);
            theModel.addAttribute("routeForm", form);

            return "add-route-toSector";
        }
    }

    @PostMapping("/spot/{spotId}/sector/{sectorId}/saveRoute")
    public String saveRoute(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId,
                            @Valid @ModelAttribute("routeForm") Route theRoute,  BindingResult theBindingResult,
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
                return "add-route-toSector";
            } else {
                System.out.println("form is validated");

                Route routeToSave = new Route();
                routeToSave.setUser(userService.findUserByEmail(sessionEmail));
                routeToSave.setSector(sectorService.findSectorById(sectorId));
                routeToSave.setName(theRoute.getName());

                routeService.saveRoute(routeToSave);

                String redirectingString = "/spots/spot/"+spotId+"/sector/"+sectorId;
                return "redirect:"+redirectingString;
            }
        }
    }


    @GetMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}")
    public String viewRoute(@PathVariable("spotId") Integer spotId,@PathVariable("sectorId") Integer sectorId, @PathVariable("routeId") Integer routeId,
                            Model theModel, HttpServletRequest request){
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") != null) {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUser = userService.findUserByEmail(sessionEmail);
            theModel.addAttribute("user", theUser);
        }
        Spot theSpot = spotService.findSpotById(spotId);
        Sector theSector = sectorService.findSectorById(sectorId);
        Route theRoute = routeService.findRouteById(routeId);

        theModel.addAttribute("route", theRoute);
        theModel.addAttribute("sector", theSector);
        theModel.addAttribute("spot", theSpot);
        return "view-route";
    }


    @GetMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/updateFormRoute")
    public String formForRouteUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,@PathVariable("routeId") Integer theRouteId,
                                     Model theModel,  HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Spot theSpot = spotService.findSpotById(theSpotId);
            Sector theSector = sectorService.findSectorById(theSectorId);
            Route theRoute = routeService.findRouteById(theRouteId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getId() != theRoute.getUser().getId()){
                System.out.println("User trying to update the route is neither the owner of the sector or an admin");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("route", theRoute);
            return "route-edit";
        }
    }


    @PostMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/updateRoute")
    public String updateRoute(@PathVariable("spotId") Integer theSpotId,  @PathVariable("sectorId") Integer theSectorId, @PathVariable("routeId") Integer theRouteId,
                              @Valid @ModelAttribute("route") Route theRoute, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            System.out.println("form has errors");
            return "route-edit";
        } else {
            System.out.println("form is validated");
            Route theRouteToUpdate = routeService.findRouteById(theRouteId);
            theRoute.setUser(theRouteToUpdate.getUser());
            theRoute.setSector(theRouteToUpdate.getSector());
            routeService.updateRoute(theRoute);

            String redirectingString = "/spots/spot/"+theSpotId+"/sector/"+theSectorId+"/route/"+theRouteId;
            return "redirect:"+redirectingString;
        }
    }
    @GetMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/deleteRoute")
    public String deleteRouteFromSector(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,@PathVariable("routeId") Integer theRouteId,
                                       HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        Spot theSpot = spotService.findSpotById(theSpotId);
        Sector theSector = sectorService.findSectorById(theSectorId);
        Route theRoute = routeService.findRouteById(theRouteId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getId() != theRoute.getUser().getId()){
            System.out.println("User trying to delete route is neither the owner of the comment or an admin");
            System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        routeService.deleteRoute(theRouteId);
        String redirectingString = "/spots/spot/"+theSpotId+"/sector/"+theSectorId;
        return "redirect:"+redirectingString;
    }

    /*
    ******************************************
    * Lengths
    ******************************************
     */
    @GetMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/ajoutLongueur")
    public String addLengthToRoute(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId,
                                  @PathVariable("routeId") Integer routeId,
                                  Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            List<Grade>grades = gradeService.getGrades();
            LengthForm form = new LengthForm();
            Route theRoute = routeService.findRouteById(routeId);
            Sector theSector = sectorService.findSectorById(sectorId);

            theModel.addAttribute("route", theRoute);
            theModel.addAttribute("sector", theSector);
            theModel.addAttribute("grades", grades );
            theModel.addAttribute("lengthForm", form);

            return "add-length-toRoute";
        }
    }

    @PostMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/saveLength")
    public String saveLength(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId, @PathVariable("routeId") Integer routeId,
                            @Valid @ModelAttribute("lengthForm") LengthForm theLengthForm,
                             BindingResult theBindingResult,
                            HttpServletRequest request, HttpSession session, Model theModel) {
        session = request.getSession();

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            lengthFormValidator.validate(theLengthForm, theBindingResult);

            if (theBindingResult.hasErrors()) {
                System.out.println("form has errors");
                List<Grade> grades = gradeService.getGrades();
                theModel.addAttribute("grades", grades);
                return "add-length-toRoute";
            } else {
                System.out.println("form is validated");

                Double height = Double.parseDouble(theLengthForm.getHeight());
                Integer bolts = Integer.parseInt(theLengthForm.getBolts());

                Length theLength = new Length();
                theLength.setUser(userService.findUserByEmail(sessionEmail));
                theLength.setRoute(routeService.findRouteById(routeId));
                theLength.setBolts(bolts);
                theLength.setHeight(height);
                theLength.setGrade(gradeService.findById(theLengthForm.getGrade()));

                lengthService.saveLength(theLength);

                String redirectingString = "/spots/spot/"+spotId+"/sector/"+sectorId+"/route/"+routeId;
                return "redirect:"+redirectingString;
            }
        }
    }

    @GetMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/length/{lengthId}/updateFormLength")
    public String formForLengthUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,
                                      @PathVariable("routeId") Integer theRouteId,@PathVariable("lengthId") Integer theLengthId,
                                     Model theModel,  HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Length theLength = lengthService.findLengthById(theLengthId);
            LengthForm form = new LengthForm();
            List<Grade>grades = gradeService.getGrades();

            form.setBolts(String.valueOf(theLength.getBolts()));
            form.setHeight(String.valueOf(theLength.getHeight()));
            form.setGrade(theLength.getGrade().getId());

            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getId() != theLength.getUser().getId()){
                System.out.println("User trying to update the length is neither the owner of the sector or an admin");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("lengthForm", form);
            theModel.addAttribute("grades", grades);
            return "length-edit";
        }
    }

    @PostMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/length/{lengthId}/updateLength")
    public String updateLength(@PathVariable("spotId") Integer theSpotId,  @PathVariable("sectorId") Integer theSectorId,
                               @PathVariable("routeId") Integer theRouteId,@PathVariable("lengthId") Integer theLengthId,
                              @Valid @ModelAttribute("lengthForm") LengthForm theLengthForm, BindingResult theBindingResult, Model theModel) {
        lengthFormValidator.validate(theLengthForm, theBindingResult);

        if (theBindingResult.hasErrors()) {
            System.out.println("form has errors");
            List<Grade> grades = gradeService.getGrades();
            theModel.addAttribute("grades", grades);
            return "length-edit";
        } else {
            System.out.println("form is validated");
            Length theLengthToUpdate = lengthService.findLengthById(theLengthId);

            Double height = Double.parseDouble(theLengthForm.getHeight());
            Integer bolts = Integer.parseInt(theLengthForm.getBolts());

           // theLength.setUser(theLengthToUpdate.getUser());
            //theLength.setRoute(theLengthToUpdate.getRoute());
            theLengthToUpdate.setBolts(bolts);
            theLengthToUpdate.setHeight(height);
            theLengthToUpdate.setGrade(gradeService.findById(theLengthForm.getGrade()));

            lengthService.updateLength(theLengthToUpdate);

            String redirectingString = "/spots/spot/"+theSpotId+"/sector/"+theSectorId+"/route/"+theRouteId;
            return "redirect:"+redirectingString;
        }
    }
    @GetMapping("/spot/{spotId}/sector/{sectorId}/route/{routeId}/length/{lengthId}/deleteLength")
    public String deleteLengthFromRoute(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,
                                        @PathVariable("routeId") Integer theRouteId,@PathVariable("lengthId") Integer theLengthId,
                                        HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        Spot theSpot = spotService.findSpotById(theSpotId);
        Sector theSector = sectorService.findSectorById(theSectorId);
        Route theRoute = routeService.findRouteById(theRouteId);
        Length theLength = lengthService.findLengthById(theLengthId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getId() != theLength.getUser().getId()){
            System.out.println("User trying to delete length is neither the owner of the comment or an admin");
            System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        lengthService.deleteLength(theLengthId);
        String redirectingString = "/spots/spot/"+theSpotId+"/sector/"+theSectorId+"/route/"+theRouteId;
        return "redirect:"+redirectingString;
    }

}