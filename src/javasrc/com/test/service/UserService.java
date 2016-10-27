package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/7/13.
 */
public interface UserService {
    void setResponse(HttpServletResponse resp);
    void UserReg() throws IOException;
    //void UserLogin() throws IOException;
    Boolean UserLogin() throws IOException;
}
