package com.Validator;


import com.db.Mapper.UserEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Tinyhome on 16/8/26.
 */
public class UserValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserEntity.class);
    }

    public void validate(Object o, Errors errors) {
        UserEntity userEntity = (UserEntity) o;
        if(userEntity.getUser_login()==null || userEntity.getUser_pwd()==null){
            errors.reject("username and user password can not null");
        }

        if(userEntity.getUser_pwd().length()<8){
            errors.reject("password length error");
        }
    }
}
