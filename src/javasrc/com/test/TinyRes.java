package com.test;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/7/13.
 */
public class TinyRes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-type","image/jpg");
        File f=new File(this.getClass().getClassLoader().getResource("").getPath()+"../../images/java.jpg");
        FileInputStream fs=new FileInputStream(f);

        byte[] bs=new byte[512];

        ServletOutputStream outer= resp.getOutputStream();
        while (fs.read(bs)!=-1)
        {
            outer.write(bs);
        }
        fs.close();
        outer.close();
        outer.flush();


    }
}
