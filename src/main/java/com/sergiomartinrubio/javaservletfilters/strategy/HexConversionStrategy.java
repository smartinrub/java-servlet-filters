package com.sergiomartinrubio.javaservletfilters.strategy;

import com.sergiomartinrubio.javaservletfilters.strategy.utility.DecimalIpConverter;

public class HexConversionStrategy implements ConversionStrategy {

    private DecimalIpConverter decimalIpConverter;

    public HexConversionStrategy() {
        this.decimalIpConverter = new DecimalIpConverter();
    }

    @Override
    public String convert(String ipAddress) {
        long decimalValue = decimalIpConverter.convert(ipAddress);
        return Long.toHexString(decimalValue);
    }
}
