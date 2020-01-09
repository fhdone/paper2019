package com.fhdone.paper2019.util;

import org.junit.Test;

public class IPUtilsTest {

    @Test
    public void queryIp(){
        try {
			IPUtils.queryIpByDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}