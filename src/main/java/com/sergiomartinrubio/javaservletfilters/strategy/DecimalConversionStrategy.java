package com.sergiomartinrubio.javaservletfilters.strategy;

public class DecimalConversionStrategy implements ConversionStrategy {
    @Override
    public String convert(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {

            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return Long.toString(result);
    }
}
