package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/7/13.
 */
public class StudentService implements UserService {
    private HttpServletResponse response;
    public void setResponse(HttpServletResponse resp)
    {
        response = resp;
    }

    public void UserReg() throws IOException {
        response.getWriter().write("学生注册成功!<br/>");
    }

    public Boolean UserLogin() throws IOException {
        response.getWriter().write("学生登录成功!<br/>");
        return false;
    }
}
