package com.fhdone.paper2019.jdk11;

import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;

/**
 * https://jimolonely.github.io/2018/09/08/java/005-jdk11-new-feature/
 */
public class FlightRecorderTest {

    @Label("Hello World")
    @Description("Helps the programmer getting started")
    static class HelloWorld extends Event {
        @Label("Message")
        String message;
    }

    public static void main(String[] args) {
        HelloWorld event = new HelloWorld();
        event.message = "hello, world!";
        event.commit();
    }

}
