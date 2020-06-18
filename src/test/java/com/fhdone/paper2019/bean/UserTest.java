package com.fhdone.paper2019.bean;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

// http://imushan.com/2018/01/18/java/language/%E6%8E%8C%E6%8F%A1Java-Bean-Validation/
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
