package com.jtthink;


import com.Validator.UserValidator;
import com.db.Mapper.UserEntity;
import com.service.ServiceResult;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tinyhome on 16/7/15.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    @RequestMapping(value = "/user/{userid:10000[0-9]}",
            method = RequestMethod.GET)
    public List<UserEntity> getUser(
            @PathVariable int userid,
            HttpServletRequest request){
        //String getID = request.getParameter("id").toString();
        //return userService.getUserByID(Integer.parseInt(getID));
        return userService.getUserByID(userid);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ServiceResult addUser(@RequestParam String user_login,
                                 @RequestParam String user_pwd
                       ){
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_login(user_login);
        userEntity.setUser_pwd(user_pwd);

        try{
            if(userService.regUser(userEntity) ==1){
                return new ServiceResult("sucess",String.valueOf(userEntity.getUser_id()));
            }
            else {
                return new ServiceResult("error", "0");
            }
        }
        catch(Exception ex){
            return new ServiceResult("error", ex.getMessage());
        }

    }




    /*@InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserValidator());
    }*/

    @RequestMapping(value = "/user1",method = RequestMethod.POST)
    public ServiceResult addUser(@RequestBody @Valid UserEntity userEntity,
                                 BindingResult bindingResult){


        if(bindingResult.hasErrors()){
            StringBuffer stringBuffer = new StringBuffer();
            for(ObjectError objectError:bindingResult.getAllErrors()){
                //收集错误信息
                stringBuffer.append(objectError.getDefaultMessage());
            }

            return new ServiceResult("error",stringBuffer.toString());
        }

        userEntity.setUser_login(userEntity.getUser_login());
        userEntity.setUser_pwd(userEntity.getUser_pwd());

        try {

            if(userService.regUser(userEntity)==1)
            {
                return new ServiceResult("success",String.valueOf(userEntity.getUser_id()));
            }
            else
            {
                return new ServiceResult("error","0");
            }
        }
        catch (Exception ex)
        {
            return new ServiceResult("error",ex.getMessage());
        }

    }

}
