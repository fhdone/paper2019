package com.fhdone.paper2019.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


//https://blog.csdn.net/pange1991/article/details/81356594
//@SpringBootApplication
@ComponentScan
/*把用到的资源导入到当前容器中*/
//@Import({Dog.class, Cat.class})
@Import(MyConfig.class)
public class App {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Cat.class));
        context.close();
    }
}

