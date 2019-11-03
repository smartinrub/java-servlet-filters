package com.sergiomartinrubio.javaservletfilters;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/convert", name = "convertServlet")
public class IpAddressConverterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ip = request.getParameter("ip");
        String format = request.getParameter("format");
        IpAddressConversionContext conversionContext = null;
        IpAddressFormat inputIpAddressFormat;

        try {
            inputIpAddressFormat = IpAddressFormat.valueOf(format.toUpperCase());
        } catch (RuntimeException e) {
            throw new IpAddressTargetFormatException("Invalid format value: " + format);
        }

        switch (inputIpAddressFormat) {
            case HEX:
                conversionContext = new IpAddressConversionContext(new HexConversionStrategy());
                break;
            case DECIMAL:
                conversionContext = new IpAddressConversionContext(new DecimalConversionStrategy());
                break;
            case BINARY:
                conversionContext = new IpAddressConversionContext(new BinaryConversionStrategy());
                break;
        }

        PrintWriter out = response.getWriter();
        out.println(conversionContext.executeStrategy(ip));
    }

}
