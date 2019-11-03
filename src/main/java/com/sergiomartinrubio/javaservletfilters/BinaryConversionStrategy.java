package com.sergiomartinrubio.javaservletfilters;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BinaryConversionStrategy implements ConversionStrategy {
    @Override
    public String convert(String ipAddress) {
        byte[] bytes;
        try {
            bytes = InetAddress.getByName(ipAddress).getAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException();
        }
        return new BigInteger(1, bytes).toString(2);
    }
}
