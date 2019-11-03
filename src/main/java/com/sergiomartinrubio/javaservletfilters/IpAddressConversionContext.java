package com.sergiomartinrubio.javaservletfilters;

public class IpAddressConversionContext {

    private final ConversionStrategy conversionStrategy;

    public IpAddressConversionContext(ConversionStrategy conversionStrategy) {
        this.conversionStrategy = conversionStrategy;
    }

    public String executeStrategy(String ipAddress) {
        return conversionStrategy.convert(ipAddress);
    }
}
