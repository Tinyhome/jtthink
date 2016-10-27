package com.jtthink;

import com.Utils.EncryptUtil;
import com.Utils.WebUtil;
import com.db.Mapper.ClientEntity;
import com.service.ServiceResult;
import com.service.TokenResult;
import com.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Tinyhome on 16/8/31.
 */
@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;
    @Autowired
    JedisPool shardedJedisPool;

    @RequestMapping(value = "/token",method = RequestMethod.GET)
    public TokenResult getToken(
            @Valid @ModelAttribute ClientEntity clientEntity,
            BindingResult bindingResult,HttpServletRequest request
            ){

        TokenResult tokenResult = new TokenResult();

        if(bindingResult.hasErrors()){
            StringBuffer stringBuffer = new StringBuffer();
            for(ObjectError objectError:bindingResult.getAllErrors()){
                //收集错误信息
                stringBuffer.append(objectError.getDefaultMessage());
            }

            tokenResult.setAccess_token(stringBuffer.toString());
            tokenResult.setExpires_in("-1");
            return tokenResult;

        }

        //在数据库判断,客户是否存在
        if(!tokenService.existsClient(clientEntity.getClient_appid(),
                clientEntity.getClient_appkey())){
            tokenResult.setAccess_token("client not exists");
            tokenResult.setExpires_in("-1");
            return tokenResult;
        }


        String appid = clientEntity.getClient_appid();
        Jedis jedis = shardedJedisPool.getResource();

        //以下代码生成token字符串
        String clientInfo= EncryptUtil.md5(clientEntity.getClient_appid()
                +clientEntity.getClient_appkey()
                + WebUtil.getIP(request)); //md5用户信息

        String timeInfo=EncryptUtil.md5(String.valueOf(System.currentTimeMillis()));//md5时间戳
        String tokenString = EncryptUtil.md5(clientInfo + timeInfo);//最终生成的加密串


        if(!jedis.exists(appid)){
            //把token放到redis里,设置300秒过期时间
            jedis.setex(appid,300,tokenString);
            //把token 放入集合，以 token字符串为field
            jedis.hset("tokenset",tokenString,appid);
        }

        tokenResult.setAccess_token(jedis.get(appid));
        tokenResult.setExpires_in(String.valueOf(jedis.ttl(appid)));
        return tokenResult;
    }
}
