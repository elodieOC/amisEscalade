package com.elo.oc.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/spots/*", "/topos/*", "/admin/*"})
public class RestrictionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(RestrictionFilter.class);
    public static final String LOGIN_PAGE = "/user/login";

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException, ServletException {
        /* Cast of objects request & response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        /* Non-filtering of static resources */
        String path = request.getRequestURI().substring( request.getContextPath().length());
        if ( path.contains( "inc" ) || path.contains("resources")|| path.contains("recherche") || path.equals("/spots/")|| path.equals("/topos/") ||
                path.matches("^/spots/\\d+$")|| path.matches("^/spots/\\d+/sector/\\d+$")||
                path.matches("^/spots/\\d+/sector/\\d+/route/\\d+$")|| path.matches("^/topos/\\d+$")) {
            /* Display requested page */
            chain.doFilter( request, response );
            return;
        }
        HttpSession session = request.getSession();
        /**
         * if loggedInUserEmail doesn't exist in session, then user isn't connected
         */
        if ( session.getAttribute("loggedInUserEmail") == null ) {
            /* Redirect to login page */
            logger.info("Filtered back to login page with RestrictionFilter");
            response.sendRedirect( request.getContextPath() + LOGIN_PAGE);
        } else {
            /* Display requested page */
            chain.doFilter( request, response );
        }
    }

    public void destroy() {
    }
}
