package com.elo.oc.controller;

import com.elo.oc.entity.Spot;
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
            System.out.println("user "+ userService.findByEmail(sessionEmail).getUsername()+" logged in");
            Spot theSpot = new Spot();
            theModel.addAttribute("spot", theSpot);
            return "spot-register";}
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
            System.out.println("user "+ userService.findByEmail(sessionEmail).getUsername()+" logged in");

            spotRegistrationValidator.validate(theSpot, theBindingResult);

            if (theBindingResult.hasErrors()) {
                System.out.println("form has errors");
                return "spot-register";
            } else {
                System.out.println("form is validated");
                theSpot.setUser(userService.findByEmail(sessionEmail));
                System.out.println(theSpot.toString());
                spotService.saveSpot(theSpot);
                return "redirect:list";
            }
        }
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("spotId") int theId, Model theModel) {
        Spot theSpot = spotService.findByIdSpot(theId);
        theModel.addAttribute("spot", theSpot);
        return "spot-register";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("spotId") int theId) {
        spotService.deleteSpot(theId);
        return "redirect:list";
    }
}