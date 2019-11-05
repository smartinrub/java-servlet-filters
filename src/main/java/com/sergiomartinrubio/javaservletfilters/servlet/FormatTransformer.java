package com.sergiomartinrubio.javaservletfilters.servlet;

import com.sergiomartinrubio.javaservletfilters.exception.InputParameterException;
import com.sergiomartinrubio.javaservletfilters.model.IpAddressFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class FormatTransformer extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     * @throws IllegalArgumentException if the request is null
     * @param request the {@link HttpServletRequest} to be wrapped.
     */
    public FormatTransformer(HttpServletRequest request) {
        super(request);
        String format = request.getParameter("format");
        request.setAttribute("format", convertFormatInputToIpAddressFormat(format));
    }

    private IpAddressFormat convertFormatInputToIpAddressFormat(String format) {
        try {
            return IpAddressFormat.valueOf(format.toUpperCase());
        } catch (RuntimeException e) {
            throw new InputParameterException("Invalid format value: \"" + format + "\"");
        }
    }
}
