package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/7/13.
 */
public class CommonTool {

    private HttpServletResponse response;

    public void addUserLog() throws IOException {
        //记录用户日志
        response.getWriter().write("记录用户日志!<br/>");
    }

    public void addUserOnline(Boolean result) throws IOException {
        //记录在线人数
        if(result)
            response.getWriter().write("记录在线人数!<br/>");
        else
            response.getWriter().write("登录失败,不记录在线人数!<br/>");
    }

    public void setResponse(HttpServletResponse rs){
        response = rs;
    }

}
