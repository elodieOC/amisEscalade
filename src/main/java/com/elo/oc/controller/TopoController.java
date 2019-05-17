package com.elo.oc.controller;

import com.elo.oc.entity.Topo;
import com.elo.oc.entity.User;
import com.elo.oc.service.TopoService;
import com.elo.oc.service.UserService;
import com.elo.oc.utils.SessionCheck;
import com.elo.oc.utils.TopoRegistrationValidator;
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
 *<h2>Controller for all Topos</h2>
 * <p>Concerns topos and their users</p>
 */
@Controller
@RequestMapping("/topos")
public class TopoController {
    @Autowired
    private TopoService topoService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopoRegistrationValidator topoRegistrationValidator;

    /**
     * <p>Page that displays a list of all the topos in database</p>
     * @param theModel attribute passed to jsp page
     * @return page with a list of all the topos
     */
    @GetMapping("/list")
    public String listTopos(Model theModel) {
        List<Topo> theTopos = topoService.getTopos();
        theModel.addAttribute("topos", theTopos);
        return "list-topos";
    }

    /*
     **************************************
     * Topos CRUD
     * ************************************
     */

    /**
     * <p>Page that displays a form to add a new topo</p>
     * @param theModel attribute passed to jsp page 
     * @param request  servlet request 
     * @return page to show depending on user on user on the page
     */
    @GetMapping("/ajoutTopo")
    public String showFormForTopoAdd(Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            System.out.println("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");
            Topo theTopo = new Topo();
            theModel.addAttribute("topo", theTopo);
            return "topo-register";}
    }

    /**
     * <p>Process called after the submit button is clicked on the ajoutTopo page</p>
     * <p>Processes the saving of the topo in db</p>
     * @param theTopo the topo being added
     * @param theBindingResult the result of validation of the form 
     * @param request  servlet request  
     * @return page to show depending on user on the page
     */
    @PostMapping("/saveTopo")
    public String saveTopo(@Valid @ModelAttribute("topo") Topo theTopo, BindingResult theBindingResult,
                           HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            System.out.println("user "+ userService.findUserByEmail(sessionEmail).getUsername()+" logged in");
            topoRegistrationValidator.validate(theTopo, theBindingResult);
            if (theBindingResult.hasErrors()) {
                System.out.println("form has errors");
                return "topo-register";
            } else {
                System.out.println("form is validated");
                theTopo.setUser(userService.findUserByEmail(sessionEmail));
                topoService.saveTopo(theTopo);
                return "redirect:/topos/topo/"+theTopo.getId();
            }
        }
    }

    /**
     * <p>Page displaying the details of a topo</p>
     * @param topoId the topo being displayed on the page
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page displaying the details of the topo
     */
    @GetMapping("/topo/{topoId}")
    public String viewTopo(@PathVariable("topoId") Integer topoId, Model theModel, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") != null) {
            String sessionEmail = (session.getAttribute("loggedInUserEmail")).toString();
            User theUser = userService.findUserByEmail(sessionEmail);
            theModel.addAttribute("user", theUser);
        }
        Topo theTopo = topoService.findTopoById(topoId);
        theModel.addAttribute("topo", theTopo);
        return "view-topo";
    }

    /**
     * <p>Page displaying the form to update a topo</p>
     * @param theTopoId param in address -> id of the topo being updated
     * @param theModel theModel attribute passed to jsp page
     * @param request  servlet request
     * @return page to show depending on user on the page
     */
    @GetMapping("/topo/{topoId}/updateFormTopo")
    public String formForTopoUpdate(@PathVariable("topoId") Integer theTopoId, Model theModel,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        else {
            Topo theTopo = topoService.findTopoById(theTopoId);
            String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
            User theUpdater = userService.findUserByEmail(sessionEmail);
            if(theUpdater.getUserRole().getId()!= 1 && theUpdater.getId() != theTopo.getUser().getId()){
                System.out.println("User trying to update is neither the owner of the topo, or an admin");
                System.out.println("User is: ["+theUpdater.getId()+ ", "+theUpdater.getUsername()+"]");
                return "redirect:/home";
            }
            theModel.addAttribute("topo", theTopo);
            return "topo-update";
        }
    }

    /**
     * <p>Process called after the submit button is clicked on the updateFormTopo page</p>
     * <p>Processes the updating of the topo in db</p>
     * @param topoId param in address -> id of the topo being updated
     * @param theTopo Entity topo
     * @param theBindingResult the result of validation of the form
     * @return page to show depending on result of process
     */
    @PostMapping("/topo/{topoId}/updateTopo")
    public String updateTopo(@PathVariable("topoId") Integer topoId, @Valid @ModelAttribute("topo") Topo theTopo, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            System.out.println("form has errors");
            return "topo-update";
        } else {
            System.out.println("form is validated");
            Topo topoToUpdate = topoService.findTopoById(topoId);
            User topoUser =  userService.findUserById(topoToUpdate.getUser().getId());
            theTopo.setUser(topoUser);
            topoService.updateTopo(theTopo);
            return "redirect:/topos/topo/"+topoId;
        }
    }

    /**
     * <p>Process called after the Supprimer button is clicked on the topoId page</p>
     * @param theTopoId param in address -> id of the topo being updated
     * @param request  servlet request
     * @return page to show depending on user of the page
     */
    @GetMapping("/topo/{topoId}/delete")
    public String deleteCustomer(@PathVariable("topoId") Integer theTopoId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!SessionCheck.checkIfUserIsLoggedIn(request, session)){
            return "redirect:/user/login";
        }
        Topo theTopo = topoService.findTopoById(theTopoId);
        String sessionEmail = session.getAttribute("loggedInUserEmail").toString();
        User theDeleter = userService.findUserByEmail(sessionEmail);
        if(theDeleter.getUserRole().getId()!= 1 && theDeleter.getId() != theTopo.getUser().getId()){
            System.out.println("User trying to delete is neither the owner of the topo, or an admin");
            System.out.println("User is: ["+theDeleter.getId()+ ", "+theDeleter.getUsername()+"]");
            return "redirect:/home";
        }
        topoService.deleteTopo(theTopoId);
        return "redirect:/topos/list";
    }

}
