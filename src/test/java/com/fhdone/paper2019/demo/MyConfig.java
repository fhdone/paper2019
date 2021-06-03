package com.fhdone.paper2019.demo;

import org.springframework.context.annotation.Bean;

public class MyConfig {

    @Bean
    public Dog getDog() {
        return new Dog();
    }

    @Bean
    public Cat getCat() {
        return new Cat();
    }
}

