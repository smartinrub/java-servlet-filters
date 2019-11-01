package com.sergiomartinrubio.javaservletfilters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/uppercase")
public class EmptyParameterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String input = request.getParameter("input");

        if (input != null && input.matches("[A-Za-z0-9]+")) {
            chain.doFilter(request, response);
        } else {
            response.getWriter().println("Missing input parameter!");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
