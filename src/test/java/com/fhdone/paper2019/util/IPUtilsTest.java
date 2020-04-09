package com.fhdone.paper2019.util;

import com.fhdone.paper2019.BaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IPUtilsTest extends BaseTest {

    @Autowired
    private IPUtils ipUtils;

    @Test
    @Ignore
    public void queryIp(){
        try {
            ipUtils.queryIpByDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}