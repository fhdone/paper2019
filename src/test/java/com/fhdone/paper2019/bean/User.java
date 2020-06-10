package com.fhdone.paper2019.bean;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotNull(message = "用户名不能为空")
    private String name;

    @Min(value = 1, message = "年龄不能小于1")
    @Max(value = 200, message = "年龄不能大于200")
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}