package com.sergiomartinrubio.javaservletfilters.filter;

import com.sergiomartinrubio.javaservletfilters.exception.InputParameterException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/convert", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class FormatFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("The filter " + FormatFilter.class.getName() + " has been created!");
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
        log.info("Destroy method is invoked for the servlet " + FormatFilter.class.getName());
    }
}
