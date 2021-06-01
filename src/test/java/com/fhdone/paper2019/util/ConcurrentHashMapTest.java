package com.fhdone.paper2019.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//https://www.cnblogs.com/thisiswhy/p/13127162.html
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>(16);
        map.computeIfAbsent(
                "AaAa",
                key -> {
                    return map.computeIfAbsent(
                            "BBBB",
                            key2 -> 42);
                }
        );
    }
}