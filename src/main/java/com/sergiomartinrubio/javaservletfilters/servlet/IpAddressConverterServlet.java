package com.sergiomartinrubio.javaservletfilters.servlet;

import com.sergiomartinrubio.javaservletfilters.strategy.IpAddressConversionContext;
import com.sergiomartinrubio.javaservletfilters.exception.IpAddressTargetFormatException;
import com.sergiomartinrubio.javaservletfilters.model.IpAddressFormat;
import com.sergiomartinrubio.javaservletfilters.strategy.BinaryConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.DecimalConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.HexConversionStrategy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/convert", name = "ConvertServlet")
public class IpAddressConverterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ip = request.getParameter("ip");
        String format = request.getParameter("format");
        IpAddressFormat inputIpAddressFormat;
        inputIpAddressFormat = convertFormatInputToIpAddressFormat(format);
        IpAddressConversionContext conversionContext = createIpAddressConversionContext(inputIpAddressFormat);
        PrintWriter out = response.getWriter();
        out.println(conversionContext.executeStrategy(ip));
    }

    private IpAddressFormat convertFormatInputToIpAddressFormat(String format) {
        try {
            return IpAddressFormat.valueOf(format.toUpperCase());
        } catch (RuntimeException e) {
            throw new IpAddressTargetFormatException("Invalid format value: " + format);
        }
    }

    private IpAddressConversionContext createIpAddressConversionContext(IpAddressFormat inputIpAddressFormat) {
        switch (inputIpAddressFormat) {
            case HEX:
                return new IpAddressConversionContext(new HexConversionStrategy());
            case DECIMAL:
                return new IpAddressConversionContext(new DecimalConversionStrategy());
            default:
                return new IpAddressConversionContext(new BinaryConversionStrategy());

        }
    }

}
