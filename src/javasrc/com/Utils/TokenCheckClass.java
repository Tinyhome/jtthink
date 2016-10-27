package com.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tinyhome on 16/9/21.
 */
public class TokenCheckClass {

    @Autowired
    JedisPool shardedJedisPool;
    private Logger myLogger= LogManager.getLogger(this.getClass().getName());

    public Object exec(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attrs.getRequest();
        HttpServletResponse response=  attrs.getResponse();

        //获取当前执行的方法
        MethodSignature methodSignature=(MethodSignature)proceedingJoinPoint.getSignature();
        //判断当前方法是否存在 自定义的注解
        if(methodSignature.getMethod().isAnnotationPresent(TokenCheck.class))
        {
            String getToken = request.getParameter("token");
            if(getToken == null)
            {
                //response.getWriter().write("no token");//大家可以改成json形式
                //myLogger.info("no token");
                throw new Exception(WebUtil.getIP(request)+" no token ");
                //return  null;

            }

            Jedis jedis=shardedJedisPool.getResource();
            String getAppID=jedis.hget("tokenset",getToken);

            if(getAppID != null && jedis.get(getAppID) != null
                    && jedis.get(getAppID).toString().trim().equals(getToken.trim()))
            {
                return proceedingJoinPoint.proceed();
            }
            else
            {
                response.getWriter().write("error token");
                return null;
            }
        }
        else
        {
            return proceedingJoinPoint.proceed();
        }
    }

}
