package com.sergiomartinrubio.javaservletfilters.servlet;

import com.sergiomartinrubio.javaservletfilters.model.IpAddressFormat;
import com.sergiomartinrubio.javaservletfilters.strategy.BinaryConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.DecimalConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.HexConversionStrategy;
import com.sergiomartinrubio.javaservletfilters.strategy.IpAddressConversionContext;
import org.json.JSONObject;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/convert", name = "ConvertServlet", initParams = {
        @WebInitParam(name = "param1", value = "hello")
})
public class IpAddressConverterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ip = request.getParameter("ip");
        IpAddressFormat format = (IpAddressFormat) request.getAttribute("format");
        IpAddressConversionContext conversionContext = createIpAddressConversionContext(format);
        PrintWriter out = response.getWriter();
        log(getInitParameter("param1"));
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("format", format);
        jsonOutput.put("value", conversionContext.executeStrategy(ip));
        out.println(jsonOutput);
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
