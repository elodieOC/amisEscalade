package com.elo.oc.utils;

import com.elo.oc.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionCheck {

    public static boolean checkIfUserIsLoggedIn(HttpServletRequest request, HttpSession session){
        session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") == null){
            System.out.println("user not logged in, redirect to login");
            return false;
        }
        return true;
    }

    public static boolean checkIfUserIsAdmin(User theAccessor){
        if(theAccessor.getUserRole().getId()!=1){
            System.out.println("User trying to access the admin actions is not an admin");
            System.out.println("User is: ["+theAccessor.getId()+ ", "+theAccessor.getUsername()+"]");
            return false;
        }
        return true;
    }
}
