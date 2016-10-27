package com.Utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tinyhome on 2016/10/1.
 */
public class LogUtil {
    private Logger myLogger= LogManager.getLogger(this.getClass().getName());

    public  void doLog(JoinPoint joinPoint,Throwable ex) throws IOException {
        ServletRequestAttributes attrs =
                   (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletResponse response=  attrs.getResponse();
        myLogger.info(ex.getMessage());

        response.getWriter().write("<script>self.location='http://www.jtthink.com';</script>");
        response.getWriter().close();

    }

}
