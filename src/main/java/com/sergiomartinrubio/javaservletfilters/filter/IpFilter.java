package com.sergiomartinrubio.javaservletfilters.filter;

import com.sergiomartinrubio.javaservletfilters.exception.InputParameterException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// @WebFilter has no element to define the order of filter execution. If you're in the need of it, you should opt for web.xml
@WebFilter(urlPatterns = "/convert")
public class IpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String ip = request.getParameter("ip");

        if (ip != null && ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
            chain.doFilter(request, response);
        } else {
            throw new InputParameterException("Missing ip parameter!");
        }
    }

    @Override
    public void destroy() {

    }
}
