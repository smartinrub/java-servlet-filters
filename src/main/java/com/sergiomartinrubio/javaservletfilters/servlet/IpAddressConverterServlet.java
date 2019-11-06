package com.sergiomartinrubio.javaservletfilters.servlet;

import com.sergiomartinrubio.javaservletfilters.model.IpAddressFormat;
import com.sergiomartinrubio.javaservletfilters.strategy.BinaryConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.DecimalConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.HexConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.IpAddressConversionContext;

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
        IpAddressFormat format = (IpAddressFormat) request.getAttribute("format");
        IpAddressConversionContext conversionContext = createIpAddressConversionContext(format);
        PrintWriter out = response.getWriter();
        out.println(conversionContext.executeStrategy(ip));
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
