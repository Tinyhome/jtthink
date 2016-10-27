package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/7/13.
 */
public class TeacherService implements UserService {
    private HttpServletResponse response;
    public void setResponse(HttpServletResponse resp)
    {
        response=resp;
    }

    public void UserReg() {

    }

    public Boolean UserLogin() throws IOException {
        response.getWriter().write("teacher login sucessful!");
        return true;
    }
}
