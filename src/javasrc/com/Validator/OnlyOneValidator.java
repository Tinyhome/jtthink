package com.Validator;


import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Tinyhome on 16/8/31.
 */
public class OnlyOneValidator implements ConstraintValidator<OnlyOne,String> {

    @Autowired
    UserService userService;

    public void initialize(OnlyOne onlyOne) {

    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.UserLoginIsDistinct(s);
    }
}
