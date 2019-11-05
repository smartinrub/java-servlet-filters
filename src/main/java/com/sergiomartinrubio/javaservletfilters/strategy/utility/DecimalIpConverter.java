package com.sergiomartinrubio.javaservletfilters.strategy.utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DecimalIpConverter {

    public long convert(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        List<String> ipAddressInList = Arrays.asList(ipAddressInArray);
        Collections.reverse(ipAddressInList);

        long result = 0;
        for (int i = 0; i < ipAddressInList.size(); i++) {
            int octet = Integer.parseInt(ipAddressInList.get(i));
            result += octet * Math.pow(256, i);
        }
        return result;
    }
}
