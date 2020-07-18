package com.fhdone.paper2019;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class DemoTest {

    @Test
    public void test(){
        Set<Integer> set = new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        System.out.println(set);
    }
}
