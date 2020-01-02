package com.fhdone.paper2019.util;

import java.net.InetAddress;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

public class IPUtils {

    public static void queryIp() throws Exception {
        try(
//                WebServiceClient client = new WebServiceClient.Builder(150078, "U0D5rABcb0iTBjlf")
//                        .build()) {
            WebServiceClient client = new WebServiceClient.Builder(150054, "2JiA6Vbo1g0Dtj0Y")
                    .build()) {

            InetAddress ipAddress = InetAddress.getByName("128.101.101.101");

            // Do the lookup
            CountryResponse response = client.country(ipAddress);

            Country country = response.getCountry();
            System.out.println(country.getIsoCode());            // 'US'
            System.out.println(country.getName());               // 'United States'
            System.out.println(country.getNames().get("zh-CN")); // '美国'
        }
    }
}

