package com.elo.oc.utils;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Class for encoding characters on pages</p>
 */
public class EncodingFilter extends OncePerRequestFilter {
    /**
     * <p>Filters all characters in and out, sets them in utf-8</p>
     * @param request servlet request
     * @param response servlet response
     * @param chain filter chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
