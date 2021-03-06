package com.elo.oc.controller;

import com.elo.oc.dto.LengthForm;
import com.elo.oc.dto.SearchForm;
import com.elo.oc.entity.*;
import com.elo.oc.service.*;
import com.elo.oc.utils.ImageFileProcessing;
import com.elo.oc.utils.LengthFormValidator;
import com.elo.oc.utils.SpotRegistrationValidator;
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
//TODO ajouter suppression image?
/**
 *<h2>Controller for all Spots</h2>
 * <p>Concerns spots, comments, routes and lengths</p>
 */
@Controller
@RequestMapping("/spots")
public class SpotController {

    private static final Logger logger = LogManager.getLogger(SpotController.class);

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
    @Autowired
    private RatingService ratingService;

    /**
     * <p>Page that displays a list of all the spots in database</p>
     * @param theModel attribute passed to jsp page 
     * @return page with a list of all the spots
     */
    @GetMapping("")
    public String listSpots(Model theModel) {
        List<Spot> theSpots = spotService.search("", "", "", null, "", "", "");
        List<Rating> theRatings = ratingService.getRatings();
        spotService.displayGrade(theSpots);
        ratingService.displayGradeList(theRatings);
        SearchForm searchForm = new SearchForm();
        theModel.addAttribute("spots", theSpots);
        theModel.addAttribute("grades", gradeService.getGrades());
        theModel.addAttribute("searchForm", searchForm);
        theModel.addAttribute("ratings", theRatings);
        return "list-spots";
    }

    /**
     * <p>Page displays the list of spots filtered with searched fields</p>
     * @param searchForm entity containing fields that can be searched
     * @param theModel attribute passed to jsp page
     * @return page with filtered list of spots
     */
    @PostMapping("/recherche")
    public String searchSpots(@ModelAttribute("searchForm") SearchForm searchForm, Model theModel) {
        Integer nbrSectors = null;
        if(!searchForm.getNbrSector().equals("")){
            nbrSectors = Integer.parseInt(searchForm.getNbrSector());
        }
        List<Rating> theRatings = ratingService.getRatings();
        List<Spot> theSpots = spotService.search(searchForm.getCity(), searchForm.getCounty(), searchForm.getName(), nbrSectors, searchForm.getUsername(), searchForm.getGradeMin(), searchForm.getGradeMax());
        spotService.displayGrade(theSpots);
        ratingService.displayGradeList(theRatings);
        theModel.addAttribute("spots", theSpots);
        theModel.addAttribute("grades", gradeService.getGrades());
        theModel.addAttribute("ratings", theRatings);
        return "list-spots";
    }

    /*
     **************************************
     * Spots CRUD
     * ************************************
     */

    /**
     * <p>Page that displays a form to add a new spot</p>
     * @param theModel attribute passed to jsp page 
     * @param request  servlet request 
     * @return page to show depending on user on user on the page
     */
    @GetMapping("/ajout-spot")
    public String showFormForSpotAdd(Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
        logger.info("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");
        Spot theSpot = new Spot();
        theModel.addAttribute("spot", theSpot);
        return "add-spot";
    }

    /**
     * <p>Process called after the submit button is clicked on the ajoutSpot page</p>
     * <p>Processes the saving of the spot in db</p>
     * @param theSpot the spot being added
     * @param theBindingResult the result of validation of the form 
     * @param request  servlet request  
     * @return page to show depending on user on the page
     */
    @PostMapping("/add-spot")
    public String saveSpot(@Valid @ModelAttribute("spot") Spot theSpot, BindingResult theBindingResult,
                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
        spotRegistrationValidator.validate(theSpot, theBindingResult);
        if (theBindingResult.hasErrors()) {
            logger.warn("form has errors");
            logger.warn(theBindingResult);
            return "add-spot";
        } else {
            logger.info("form is validated");
            theSpot.setImage(ImageFileProcessing.getImageForEntityAddFromForm(theSpot.getImageFile()));
            theSpot.setUser(userService.findUserByEmail(sessionEmail));
            logger.info(theSpot.toString());
            spotService.saveSpot(theSpot);
            return "redirect:/spots/"+theSpot.getId();
        }
    }

    /**
     * <p>Page displaying the details of a spot</p>
     * @param spotId the spot being displayed on the page
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page displaying the details of the spot
     */
    @GetMapping("/{spotId}")
    public String viewSpot(@PathVariable("spotId") Integer spotId, Model theModel, HttpServletRequest request){
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

    /**
     * <p>Page displaying the form to update a spot</p>
     * @param theSpotId param in address -> id of the spot being updated
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user on the page
     */
    @GetMapping("/{spotId}/editer")
    public String formForSpotUpdate(@PathVariable("spotId") Integer theSpotId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Spot theSpot = spotService.findSpotById(theSpotId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theUpdater = userService.findUserByEmail(sessionEmail);
        if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getUserRole().getId()!= 2 && theUpdater.getId() != theSpot.getUser().getId()){
            logger.warn("User trying to update is neither the owner of the spot, or a member or an admin");
            logger.warn("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
            return "redirect:/home";
        }
        theModel.addAttribute("spot", theSpot);
        return "edit-spot";
    }

    /**
     * <p>Process called after the submit button is clicked on the updateFormSpot page</p>
     * <p>Processes the updating of the spot in db</p>
     * @param spotId param in address -> id of the spot being updated
     * @param theSpot Entity spot
     * @param theBindingResult the result of validation of the form
     * @return page to show depending on result of process
     */
    @PostMapping("/{spotId}/update")
    public String updateSpot(@PathVariable("spotId") Integer spotId, @Valid @ModelAttribute("spot") Spot theSpot, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            logger.warn("form has errors");
            logger.warn(theBindingResult);
            return "edit-spot";
        } else {
            logger.info("form is validated");
            Spot spotToUpdate = spotService.findSpotById(spotId);
            User spotUser =  userService.findUserById(spotToUpdate.getUser().getId());
            theSpot.setUser(spotUser);
            if(!theSpot.getImageFile().isEmpty()){
                theSpot.setImage(ImageFileProcessing.getImageForEntityEditFromForm(theSpot.getImageFile()));
            }
            else {
                theSpot.setImage(spotToUpdate.getImage());
            }
            spotService.updateSpot(theSpot);
            return "redirect:/spots/"+spotId;
        }
    }

    /**
     * <p>Process called after the Supprimer button is clicked on the spotId page</p>
     * @param theSpotId param in address -> id of the spot being updated
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/delete")
    public String deleteCustomer(@PathVariable("spotId") Integer theSpotId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Spot theSpot = spotService.findSpotById(theSpotId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 &&theDeleter.getUserRole().getId()!= 2 && theDeleter.getId() != theSpot.getUser().getId()){
            logger.warn("User trying to delete is neither the owner of the spot, or a member or an admin");
            logger.warn("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        spotService.deleteSpot(theSpotId);
        return "redirect:/spots/";
    }
    /*
     **************************************
     * Sectors
     * ************************************
     */

    /**
     * <p>Page that displays a form to add a sector to a spot</p>
     * @param theSpotId  param in address -> id of the spot being added a sector to
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/ajout-secteur")
    public String addSectorToSpot(@PathVariable("spotId") Integer theSpotId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
            Spot theSpot = spotService.findSpotById(theSpotId);
            Sector theSector = new Sector();
            theModel.addAttribute("spot", theSpot);
            theModel.addAttribute("sector", theSector);
            return "add-sector-toSpot";

    }

    /**
     * <p>Process called after the submit button is clicked on the ajoutSecteur page</p>
     * @param theSpotId  param in address -> id of the spot being added a sector to
     * @param theSector Entity sector being created
     * @param theBindingResult the result of validation of the form
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @PostMapping("/{spotId}/add-sector")
    public String saveSector(@PathVariable("spotId") Integer theSpotId, @Valid @ModelAttribute("sector") Sector theSector, BindingResult theBindingResult,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            logger.info("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");
            if (theBindingResult.hasErrors()) {
                logger.warn("form has errors");
                logger.warn(theBindingResult);
                return "add-sector-toSpot";
            } else {
                logger.info("form is validated");
                theSector.setImage(ImageFileProcessing.getImageForEntityAddFromForm(theSector.getImageFile()));
                theSector.setUser(userService.findUserByEmail(sessionEmail));
                theSector.setSpot(spotService.findSpotById(theSpotId));
                sectorService.saveSector(theSector);

                String redirectingString = "/spots/"+theSpotId+"/sector/"+theSector.getId();
                return "redirect:"+redirectingString;
            }
    }

    /**
     * <p>Page that displays the details of a sector</p>
     * @param spotId param in address -> id of the spot owning the sector
     * @param sectorId param in address -> id of the sector being displayed
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page with the details of a sector
     */
    @GetMapping("/{spotId}/sector/{sectorId}")
    public String viewSector(@PathVariable("spotId") Integer spotId,@PathVariable("sectorId") Integer sectorId, Model theModel, HttpServletRequest request){
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") != null) {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            theModel.addAttribute("user", userService.findUserByEmail(sessionEmail));
        }
        Spot theSpot = spotService.findSpotWithAllInfosById(spotId);
        Sector theSector = sectorService.findSectorById(sectorId);
        List<Rating> theRatings = ratingService.getRatings();
        ratingService.displayGradeList(theRatings);
        theModel.addAttribute("ratings", theRatings);
        List<Route> routes = theSector.getRoutes();
        theModel.addAttribute("routes", routes);
        for(Route r : routes) {
            if(r.getLengths().size()>0){
                r.setGradeMax(routeService.getRouteGradeMax(r.getId()));
                r.setGradeMin(routeService.getRouteGradeMin(r.getId()));
            }
        }
        theModel.addAttribute("spot", theSpot);
        theModel.addAttribute("sector", theSector);
        return "view-sector";
    }

    /**
     * <p>Page that displays a form to update a sector</p>
     * @param theSpotId param in address -> id of the spot owning the sector
     * @param theSectorId param in address -> id of the sector to update
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return  page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/editer")
    public String formForSectorUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId, Model theModel,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
            Sector theSector = sectorService.findSectorById(theSectorId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getUserRole().getId()!= 2 && theUpdater.getId() != theSector.getUser().getId()){
                logger.warn("User trying to update the sector is neither the owner of the sector or a member or an admin");
                logger.warn("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("sector", theSector);
            return "edit-sector";
    }

    /**
     *  <p>Process called after the submit button is clicked on the updateFormSector page</p>
     * @param spotId param in address -> id of the spot owning the sector
     * @param theSectorId param in address -> id of the sector to update
     * @param theSector the sector being updated
     * @param theBindingResult the result of validation of the form
     * @return  page to show depending on result of the process
     */
    @PostMapping("/{spotId}/sector/{sectorId}/update")
    public String updateSector(@PathVariable("spotId") Integer spotId,  @PathVariable("sectorId") Integer theSectorId,@Valid @ModelAttribute("sector") Sector theSector, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            logger.warn("form has errors");
            logger.warn(theBindingResult);
            return "edit-sector";
        } else {
            logger.info("form is validated");
            Sector theSectorToUpdate = sectorService.findSectorById(theSectorId);
            theSector.setSpot(theSectorToUpdate.getSpot());
            theSector.setUser(theSectorToUpdate.getUser());
            if(!theSector.getImageFile().isEmpty()){
                theSector.setImage(ImageFileProcessing.getImageForEntityEditFromForm(theSector.getImageFile()));
            }
            else {
                theSector.setImage(theSectorToUpdate.getImage());
            }
            sectorService.updateSector(theSector);
            String redirectingString = "/spots/"+spotId+"/sector/"+theSector.getId();
            return "redirect:"+redirectingString;
        }
    }

    /***
     * <p>Process called the Supprimer button is clicked on the sectorId page</p>
     * @param theSpotId param in address -> id of the spot owning the sector
     * @param theSectorId param in address -> id of the sector to update
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/delete")
    public String deleteSectorFromSpot(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();
        Sector theSector = sectorService.findSectorById(theSectorId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getUserRole().getId()!= 2 && theDeleter.getId() != theSector.getUser().getId()){
            logger.warn("User trying to delete sector is neither the owner of the comment or a member or an admin");
            logger.warn("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        sectorService.deleteSector(theSectorId);
        return "redirect:/spots/"+theSpotId;
    }

    /*
     **************************************
     * Comments
     * ************************************
     */

    /**
     * Page displaying a form to add a comment to a spot
     * @param spotId param in address -> id of the spot being commented
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/commenter")
    public String addCommentToSpot(@PathVariable("spotId") Integer spotId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
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

    /**
     * <p>Process called after the submit button is clicked on the commenter page</p>
     * @param spotId param in address -> id of the spot being commented
     * @param theComment the comment being breated
     * @param theBindingResult the result of validation of the form
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @PostMapping("/{spotId}/add-comment")
    public String saveComment(@PathVariable("spotId") Integer spotId, @Valid @ModelAttribute("comment") Comment theComment, BindingResult theBindingResult,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            logger.info("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");
            if (theBindingResult.hasErrors()) {
                logger.warn("form has errors");
                logger.warn(theBindingResult);
                return "add-comment-toSpot";
            } else {
                logger.info("form is validated");
                theComment.setUser(userService.findUserByEmail(sessionEmail));
                theComment.setSpot(spotService.findSpotById(spotId));
                commentService.saveComment(theComment);
                return "redirect:/spots/"+spotId;
        }
    }

    /**
     * Page displaying a form to edit a comment
     * @param theSpotId param in address -> id of the spot owning the comment
     * @param theCommentId param in address -> id of the comment being edited
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/comment/{commentId}/editer")
    public String formForCommentUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("commentId") Integer theCommentId, Model theModel,
                                       HttpServletRequest request) {
        HttpSession  session = request.getSession();
            Spot theSpot = spotService.findSpotById(theSpotId);
            Comment theComment = commentService.findCommentById(theCommentId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getUserRole().getId()!= 2 && theUpdater.getId() != theSpot.getUser().getId()){
                logger.warn("User trying to update the comment is neither the owner of the spot, an admin or a member of the association");
                logger.warn("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("comment", theComment);
            return "edit-comment";
    }

    /**
     * Process called after the edit button is clicked on the updateFormComment page
     * @param spotId param in address -> id of the spot owning the comment
     * @param theCommentId param in address -> id of the comment being edited
     * @param theComment the comment being edited
     * @param theBindingResult the result of validation of the form
     * @return page to show depending on the result of process
     */
    @PostMapping("/{spotId}/comment/{commentId}/update")
    public String updateComment(@PathVariable("spotId") Integer spotId,  @PathVariable("commentId") Integer theCommentId,@Valid @ModelAttribute("comment") Comment theComment, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            logger.warn("form has errors");
            logger.warn(theBindingResult);
            return "edit-comment";
        } else {
            logger.info("form is validated");
            Comment theCommentToUpdate = commentService.findCommentById(theCommentId);
            theComment.setSpot(theCommentToUpdate.getSpot());
            theComment.setDate(theCommentToUpdate.getDate());
            theComment.setUser(theCommentToUpdate.getUser());
            commentService.updateComment(theComment);
            return "redirect:/spots/"+spotId;
        }
    }

    /**
     * <p>Process called the Supprimer button is clicked on a comment</p>
     * @param theSpotId param in address -> id of the spot owning the comment
     * @param theCommentId param in address -> id of the comment being deleted
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/comment/{commentId}/delete")
    public String deleteCommentFromSpot(@PathVariable("spotId") Integer theSpotId,@PathVariable("commentId") Integer theCommentId,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        Comment theComment = commentService.findCommentById(theCommentId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getUserRole().getId()!= 2 && theDeleter.getId() != theComment.getUser().getId()){
            logger.warn("User trying to delete comment is neither the owner of the comment, an admin, or a member of the association");
            logger.warn("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        commentService.deleteComment(theCommentId);
        return "redirect:/spots/"+theSpotId;
    }

    /*
     **************************************
     * Routes
     * ************************************
     */

    /**
     * <p>Page that displays a form to add a route to a sector</p>
     * @param spotId param in address -> id of the spot
     * @param sectorId param in address -> id of the sector
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/ajout-voie")
    public String addRouteToSector(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId,
                                   Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
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

    /**
     * <p>Process called after the submit button is clicked on the ajoutVoie page</p>
     * @param spotId param in address -> id of the spot
     * @param sectorId param in address -> id of the sector
     * @param theRoute route being created to the sector
     * @param theBindingResult the result of validation of the form
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @PostMapping("/{spotId}/sector/{sectorId}/add-route")
    public String saveRoute(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId,
                            @Valid @ModelAttribute("routeForm") Route theRoute,  BindingResult theBindingResult,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            logger.info("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");
            if (theBindingResult.hasErrors()) {
                logger.warn("form has errors");
                logger.warn(theBindingResult);
                return "add-route-toSector";
            } else {
                logger.info("form is validated");
                theRoute.setImage(ImageFileProcessing.getImageForEntityAddFromForm(theRoute.getImageFile()));
                theRoute.setUser(userService.findUserByEmail(sessionEmail));
                theRoute.setSector(sectorService.findSectorById(sectorId));
                theRoute.setName(theRoute.getName());
                routeService.saveRoute(theRoute);
                String redirectingString = "/spots/"+spotId+"/sector/"+sectorId+"/route/"+theRoute.getId();
                return "redirect:"+redirectingString;
            }
    }

    /**
     *<p>Page displaying the details of a route</p>
     * @param spotId param in address -> id of the spot
     * @param sectorId param in address -> id of the sector
     * @param routeId param in address -> id of the route
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/route/{routeId}")
    public String viewRoute(@PathVariable("spotId") Integer spotId,@PathVariable("sectorId") Integer sectorId, @PathVariable("routeId") Integer routeId,
                            Model theModel, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") != null) {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUser = userService.findUserByEmail(sessionEmail);
            theModel.addAttribute("user", theUser);
        }
        Spot theSpot = spotService.findSpotById(spotId);
        Sector theSector = sectorService.findSectorById(sectorId);
        Route theRoute = routeService.findRouteById(routeId);
        List<Rating> theRatings = ratingService.getRatings();
        ratingService.displayGradeList(theRatings);
        theModel.addAttribute("ratings", theRatings);
        theModel.addAttribute("route", theRoute);
        theModel.addAttribute("sector", theSector);
        theModel.addAttribute("spot", theSpot);
        return "view-route";
    }

    /**
     * <p>Page displaying the form to update a route</p>
     * @param theSpotId param in address -> id of the spot
     * @param theSectorId param in address -> id of the sector
     * @param theRouteId param in address -> id of the route
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/route/{routeId}/editer")
    public String formForRouteUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,@PathVariable("routeId") Integer theRouteId,
                                     Model theModel,  HttpServletRequest request) {
        HttpSession session = request.getSession();
            Route theRoute = routeService.findRouteById(theRouteId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 &&theUpdater.getUserRole().getId()!= 2 && theUpdater.getId() != theRoute.getUser().getId()){
                logger.warn("User trying to update the route is neither the owner of the sector or a member an admin");
                logger.warn("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("route", theRoute);
            return "edit-route";
    }

    /**
     *<p>Process called after the submit button is clicked in the updateFormRoute page</p>
     * @param theSpotId param in address -> id of the spot
     * @param theSectorId param in address -> id of the sector
     * @param theRouteId param in address -> id of the route
     * @param theRoute the entity Route being updated
     * @param theBindingResult the result of validation of the form
     * @return page to show depending on user of the page
     */
    @PostMapping("/{spotId}/sector/{sectorId}/route/{routeId}/update")
    public String updateRoute(@PathVariable("spotId") Integer theSpotId,  @PathVariable("sectorId") Integer theSectorId, @PathVariable("routeId") Integer theRouteId,
                              @Valid @ModelAttribute("route") Route theRoute, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            logger.warn("form has errors");
            logger.warn(theBindingResult);
            return "edit-route";
        } else {
            logger.info("form is validated");
            Route theRouteToUpdate = routeService.findRouteById(theRouteId);
            theRoute.setUser(theRouteToUpdate.getUser());
            theRoute.setSector(theRouteToUpdate.getSector());
            if(!theRoute.getImageFile().isEmpty()){
                theRoute.setImage(ImageFileProcessing.getImageForEntityEditFromForm(theRoute.getImageFile()));
            }
            else {
                theRoute.setImage(theRouteToUpdate.getImage());
            }
            routeService.updateRoute(theRoute);
            String redirectingString = "/spots/"+theSpotId+"/sector/"+theSectorId+"/route/"+theRouteId;
            return "redirect:"+redirectingString;
        }
    }

    /**
     *<p>Process called the Supprimer button is clicked on a routeId page</p>
     * @param theSpotId param in address -> id of the spot
     * @param theSectorId param in address -> id of the sector
     * @param theRouteId param in address -> id of the route
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/route/{routeId}/delete")
    public String deleteRouteFromSector(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,@PathVariable("routeId") Integer theRouteId,
                                        HttpServletRequest request) {
        HttpSession  session = request.getSession();
        Route theRoute = routeService.findRouteById(theRouteId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 &&theDeleter.getUserRole().getId()!= 2 && theDeleter.getId() != theRoute.getUser().getId()){
            logger.warn("User trying to delete route is neither the owner of the comment or a member an admin");
            logger.warn("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        routeService.deleteRoute(theRouteId);
        String redirectingString = "/spots/"+theSpotId+"/sector/"+theSectorId;
        return "redirect:"+redirectingString;
    }

    /*
     ******************************************
     * Lengths
     ******************************************
     */

    /**
     *<p>Page that displays a form to add a Length to a Route</p>
     * @param spotId param in address -> id of the spot
     * @param sectorId param in address -> id of the sector
     * @param routeId param in address -> id of the route
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/route/{routeId}/ajout-longueur")
    public String addLengthToRoute(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId,
                                   @PathVariable("routeId") Integer routeId, Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
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

    /**
     *<p>Process called after the submit button is clicked on the ajoutLongueur page </p>
     * @param spotId param in address -> id of the spot
     * @param sectorId param in address -> id of the sector
     * @param routeId param in address -> id of the route
     * @param theLengthForm DTO for the Length entity
     * @param theBindingResult the result of validation of the form
     * @param request  servlet request
     * @param theModel theModel attribute passed to jsp page
     * @return page to show depending on user of the page
     */
    @PostMapping("/{spotId}/sector/{sectorId}/route/{routeId}/add-length")
    public String saveLength(@PathVariable("spotId") Integer spotId, @PathVariable("sectorId") Integer sectorId, @PathVariable("routeId") Integer routeId,
                             @Valid @ModelAttribute("lengthForm") LengthForm theLengthForm,
                             BindingResult theBindingResult, HttpServletRequest request,   Model theModel) {
        HttpSession session = request.getSession();
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            lengthFormValidator.validate(theLengthForm, theBindingResult);
            if (theBindingResult.hasErrors()) {
                logger.warn("form has errors");
                logger.warn(theBindingResult);
                List<Grade> grades = gradeService.getGrades();
                theModel.addAttribute("grades", grades);
                return "add-length-toRoute";
            } else {
                logger.info("form is validated");
                Double height = null;
                if(!theLengthForm.getHeight().isEmpty()){
                    height = Double.parseDouble(theLengthForm.getHeight());
                }
                Integer bolts = null;
                if(!theLengthForm.getBolts().isEmpty()) {
                    bolts = Integer.parseInt(theLengthForm.getBolts());
                }
                Length theLength = new Length();
                theLength.setUser(userService.findUserByEmail(sessionEmail));
                theLength.setRoute(routeService.findRouteById(routeId));
                theLength.setBolts(bolts);
                theLength.setHeight(height);
                theLength.setGrade(gradeService.findGradeById(theLengthForm.getGrade()));
                lengthService.saveLength(theLength);
                String redirectingString = "/spots/"+spotId+"/sector/"+sectorId+"/route/"+routeId;
                return "redirect:"+redirectingString;
            }
    }

    /**
     *<p>Page displaying a form to update a length</p>
     * @param theSpotId param in address -> id of the spot
     * @param theSectorId param in address -> id of the sector
     * @param theRouteId param in address -> id of the route
     * @param theLengthId param in address -> id of the length
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/route/{routeId}/length/{lengthId}/editer")
    public String formForLengthUpdate(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,
                                      @PathVariable("routeId") Integer theRouteId,@PathVariable("lengthId") Integer theLengthId,
                                      Model theModel,  HttpServletRequest request) {
        HttpSession session = request.getSession();
            Length theLength = lengthService.findLengthById(theLengthId);
            LengthForm form = new LengthForm();
            List<Grade>grades = gradeService.getGrades();
            form.setBolts(String.valueOf(theLength.getBolts()));
            form.setHeight(String.valueOf(theLength.getHeight()));
            form.setGrade(theLength.getGrade().getId());
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 &&theUpdater.getUserRole().getId()!= 2 && theUpdater.getId() != theLength.getUser().getId()){
                logger.warn("User trying to update the length is neither the owner of the sector or a member or an admin");
                logger.warn("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("lengthForm", form);
            theModel.addAttribute("grades", grades);
            return "edit-length";
    }

    /**
     *<p>Process called after the submit button is clicked on the updateFormLength page</p>
     * @param theSpotId param in address -> id of the spot
     * @param theSectorId param in address -> id of the sector
     * @param theRouteId param in address -> id of the route
     * @param theLengthId param in address -> id of the length
     * @param theLengthForm DTO for the Length entity
     * @param theBindingResult the result of validation of the form
     * @param theModel theModel attribute passed to jsp page
     * @return page to show depending on user of the page
     */
    @PostMapping("/{spotId}/sector/{sectorId}/route/{routeId}/length/{lengthId}/update")
    public String updateLength(@PathVariable("spotId") Integer theSpotId,  @PathVariable("sectorId") Integer theSectorId,
                               @PathVariable("routeId") Integer theRouteId,@PathVariable("lengthId") Integer theLengthId,
                               @Valid @ModelAttribute("lengthForm") LengthForm theLengthForm, BindingResult theBindingResult, Model theModel) {
        lengthFormValidator.validate(theLengthForm, theBindingResult);
        if (theBindingResult.hasErrors()) {
            logger.warn("form has errors");
            logger.warn(theBindingResult);
            List<Grade> grades = gradeService.getGrades();
            theModel.addAttribute("grades", grades);
            return "edit-length";
        } else {
            logger.info("form is validated");
            Length theLengthToUpdate = lengthService.findLengthById(theLengthId);
            Double height = null;
            if(!theLengthForm.getHeight().isEmpty()){
                height = Double.parseDouble(theLengthForm.getHeight());
            }
            Integer bolts = null;
            if(!theLengthForm.getBolts().isEmpty()) {
                bolts = Integer.parseInt(theLengthForm.getBolts());
            }
            theLengthToUpdate.setBolts(bolts);
            theLengthToUpdate.setHeight(height);
            theLengthToUpdate.setGrade(gradeService.findGradeById(theLengthForm.getGrade()));
            lengthService.updateLength(theLengthToUpdate);
            String redirectingString = "/spots/"+theSpotId+"/sector/"+theSectorId+"/route/"+theRouteId;
            return "redirect:"+redirectingString;
        }
    }

    /**
     *<p>Process called after the delete button is clicked on the lengthId page</p>
     * @param theSpotId param in address -> id of the spot
     * @param theSectorId param in address -> id of the sector
     * @param theRouteId param in address -> id of the route
     * @param theLengthId param in address -> id of the length
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/{spotId}/sector/{sectorId}/route/{routeId}/length/{lengthId}/delete")
    public String deleteLengthFromRoute(@PathVariable("spotId") Integer theSpotId,@PathVariable("sectorId") Integer theSectorId,
                                        @PathVariable("routeId") Integer theRouteId,@PathVariable("lengthId") Integer theLengthId,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        Length theLength = lengthService.findLengthById(theLengthId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 &&theDeleter.getUserRole().getId()!= 2 && theDeleter.getId() != theLength.getUser().getId()){
            logger.warn("User trying to delete length is neither the owner of the comment or a member or an admin");
            logger.warn("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        lengthService.deleteLength(theLengthId);
        String redirectingString = "/spots/"+theSpotId+"/sector/"+theSectorId+"/route/"+theRouteId;
        return "redirect:"+redirectingString;
    }

}