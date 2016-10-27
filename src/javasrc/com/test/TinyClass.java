package com.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

/**
 * Created by Tinyhome on 16/7/8.
 */

@TinyAnnotation(name="TinyClass类注解name值",age=19)
public class TinyClass {
    public String getName(){
        return "Tiny's class getName method";
    }

    @TinyLoad
    com.test.TinyBean tb;

    @TinyAnnotation
    com.test.TinyBean tb2;

    @TinyAnnotation(name="aa",age=25)
    public String getMe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("name","Tiny");
        //req.setAttribute("age","18");
        //req.getRequestDispatcher("/aa.jsp").forward(req,resp);
        resp.setHeader("Content-type","text/html");

        String config_file = this.getClass().getClassLoader().getResource("").getPath()+"../tiny.xml";
        try {
            XMLConfiguration xc = new XMLConfiguration();
            xc.load(config_file);
            ConfigurationNode root = xc.getRootNode();
            //resp.getWriter().write(root.getName());
            List<ConfigurationNode> list = root.getChildren("bean");

            for(ConfigurationNode n:list){
                List<ConfigurationNode> plist = n.getChildren("p");
                for(ConfigurationNode m : plist){
                    resp.getWriter().write(m.getAttribute(0).getValue().toString());
                    resp.getWriter().write("<hr/>");
                }

            }

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return "";
    }

 /*   @TinyAnnotation(name="aa",age=25)
    public String show(HttpServletRequest req, HttpServletResponse resp){
        try {
            resp.getWriter().write("This is show.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "I am show !!";
    }*/
}
