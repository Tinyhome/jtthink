package com.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tinyhome on 16/9/18.
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    JedisPool shardedJedisPool;

    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {

        String getToken = httpServletRequest.getParameter("token");
        if (getToken == null) {
            //大家可以改成json形式
            httpServletResponse.getWriter().write("no token");
            return false;
        }

        Jedis jedis = shardedJedisPool.getResource();
        String getAppID = jedis.hget("tokenset", getToken);

        if (getAppID != null && jedis.get(getAppID) != null
                && jedis.get(getAppID).toString().trim().equals(getToken.trim())){

            //进入下一步响应
            return true;
        }

        else
        {
            httpServletResponse.getWriter().write("error token");
            return false;
        }

    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
