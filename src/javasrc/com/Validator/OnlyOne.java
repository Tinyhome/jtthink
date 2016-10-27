package com.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tinyhome on 16/8/31.
 */

@Target({ElementType.METHOD, ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { OnlyOneValidator.class })
public @interface OnlyOne {
    String message() default  "用户名不唯一";   //必须的属性
    Class<?>[] groups() default {};  //必须的属性
    Class<? extends Payload>[] payload() default {}; //必须的属性
}
