package com.sergiomartinrubio.javaservletfilters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/convert")
public class ParametersCheckerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String ip = request.getParameter("ip");
        String format = request.getParameter("format");

        if (ip != null && format != null && ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
            chain.doFilter(request, response);
        } else {
            response.getWriter().println("Missing or wrong input parameter!");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
