package com.fhdone.paper2019.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest {

    @Test
    public void test() throws Exception {
        SimpleDateFormat df1 = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        // 2020年12月26日周六
        c.set(Calendar.DATE, 26);
        System.out.println("YYYY-MM-dd = " + df1.format(c.getTime()));
        System.out.println("yyyy-MM-dd = " + df2.format(c.getTime()));

        // 分割线
        System.out.println("========================");

        // 2020年12月27日 周日
        c.add(Calendar.DATE, 1);
        System.out.println("YYYY-MM-dd = " + df1.format(c.getTime()));
        System.out.println("yyyy-MM-dd = " + df2.format(c.getTime()));
    }

}
