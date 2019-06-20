package com.elo.oc.utils;

import com.elo.oc.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Class checks if a user is logged in and its role</p>
 */
public class SessionCheck {
    /**
     * <p>Method checks if a session has an email for attribute (user is logged in)</p>
     * @param request servlet request
     * @param session http session
     * @return true if there is an email attribute
     */
    public static boolean checkIfUserIsLoggedIn(HttpServletRequest request, HttpSession session){
        session = request.getSession();
        if(session.getAttribute("loggedInUserEmail") == null){
            System.out.println("user not logged in, redirect to login");
            return false;
        }
        return true;
    }

    /**
     * <p>Method checks if logged in user is an admin or not</p>
     * @param theAccessor the logged in user
     * @return true if user is an admin
     */
    public static boolean checkIfUserIsAdminOrMember(User theAccessor){
        if(theAccessor.getUserRole().getId()!=1 && theAccessor.getUserRole().getId()!=2 ){
            System.out.println("User trying to access the admin actions is not an admin or a member");
            System.out.println("User is: ["+theAccessor.getId()+ ", "+theAccessor.getUsername()+"]");
            return false;
        }
        return true;
    }
}
