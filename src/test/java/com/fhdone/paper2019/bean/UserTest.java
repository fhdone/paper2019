package com.fhdone.paper2019.bean;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


public class UserTest {

    @Test
    public void user( ) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> validate = validator.validate(new User(null, 0));
        for (ConstraintViolation<User> violation : validate) {
            System.out.println(violation.getMessage());
        }
    }

}
