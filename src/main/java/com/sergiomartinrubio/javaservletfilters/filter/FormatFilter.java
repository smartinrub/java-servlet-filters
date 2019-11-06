package com.sergiomartinrubio.javaservletfilters.filter;

import com.sergiomartinrubio.javaservletfilters.exception.InputParameterException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/convert")
public class FormatFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request = new FormatRequestWrapper((HttpServletRequest) request);

        String format = request.getParameter("format");

        if (format != null) {
            chain.doFilter(request, response);
        } else {
            throw new InputParameterException("Missing format parameter!");
        }
    }

    @Override
    public void destroy() {
    }
}
