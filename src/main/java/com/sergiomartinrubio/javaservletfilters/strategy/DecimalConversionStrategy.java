package com.sergiomartinrubio.javaservletfilters.strategy;

import com.sergiomartinrubio.javaservletfilters.strategy.utility.DecimalIpConverter;

public class DecimalConversionStrategy implements ConversionStrategy {

    private DecimalIpConverter decimalIpConverter;

    public DecimalConversionStrategy() {
        this.decimalIpConverter = new DecimalIpConverter();
    }

    @Override
    public String convert(String ipAddress) {
        return Long.toString(decimalIpConverter.convert(ipAddress));
    }
}
