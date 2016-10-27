package com.jtthink;

/**
 * Created by Tinyhome on 16/7/5.
 */
import com.db.Mapper.UserEntity;
import com.db.Mapper.UserMapper;
import com.service.UserService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.test.TinyManager;
import com.test.TinyPool;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.test.TinyBean;

@Controller
public class IndexController {

    @Autowired
    TinyBean tb;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    //不返回任何值
    public void loadIndex(HttpServletResponse resp) throws Exception {

        resp.setHeader("Content-type","text/html;charset=utf-8");

        /*UserEntity userEntity = new UserEntity();
        userEntity.setId(1);

        List<UserEntity> getUsers =  userMapper.selectByID(userEntity);
        for(UserEntity ue : getUsers){
            resp.getWriter().write(ue.getUsername());
            resp.getWriter().write("<hr/>");
        }*/

        /*List<UserEntity> list = new ArrayList<UserEntity>();

        UserEntity user3 = new UserEntity();
        user3.setUsername("user3");
        user3.setUserphone("3333");

        UserEntity user4 = new UserEntity();
        user4.setUsername("user4");
        user4.setUserphone("4444");

        list.add(user3);
        list.add(user4);

        int result = userMapper.addUsers(list);
        resp.getWriter().write(String.valueOf(result));*/

        /*UserEntity user4 = new UserEntity();
        user4.setId(4);

        UserEntity user5 = new UserEntity();
        user5.setId(5);

        UserEntity user6 = new UserEntity();
        user6.setId(6);

        int result = userMapper.editMuti(new ArrayList<UserEntity>(Arrays.asList(user4,user5,user6)));
        resp.getWriter().write(String.valueOf(result));*/

        //userService.AddUser();


    }
}

