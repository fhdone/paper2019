package com.fhdone.paper2019.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

import java.io.InputStream;
import java.net.InetAddress;

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

    public static void queryIpByDB() throws Exception {
        // A File object pointing to your GeoLite2 ASN database
        InputStream database = IPUtils.class.getClassLoader().getResourceAsStream("geolite2/GeoLite2-ASN.mmdb");

// This creates the DatabaseReader object. To improve performance, reuse
// the object across lookups. The object is thread-safe.
        try (DatabaseReader reader = new DatabaseReader.Builder(database).build()) {

            InetAddress ipAddress = InetAddress.getByName("128.101.101.101");

            AsnResponse response = reader.asn(ipAddress);

            System.out.println(response.getAutonomousSystemNumber());       // 217
            System.out.println(response.getAutonomousSystemOrganization()); // 'University of Minnesota'
//}
        }
    }
}

