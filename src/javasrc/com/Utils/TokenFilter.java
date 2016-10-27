package com.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/9/7.
 */
public class TokenFilter implements javax.servlet.Filter {
    @Autowired
    JedisPool shardedJedisPool;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String getToken=servletRequest.getParameter("token");
        if(getToken==null)
        {
            //大家可以改成json形式
            servletResponse.getWriter().write("no token");
            return;
        }

        Jedis jedis=shardedJedisPool.getResource();
        String getAppID=jedis.hget("tokenset",getToken);

        if(getAppID!=null && jedis.get(getAppID)!=null
                && jedis.get(getAppID).toString().trim().equals(getToken.trim()))
            //进入下一步响应
            filterChain.doFilter(servletRequest, servletResponse);
        else
            servletResponse.getWriter().write("error token");

    }

    public void destroy() {

    }
}
