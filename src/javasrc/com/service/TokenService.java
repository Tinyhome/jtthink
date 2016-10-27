package com.service;

import com.db.Mapper.ClientEntity;
import com.db.Mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tinyhome on 16/9/4.
 */

public class TokenService {

    @Autowired
    ClientMapper clientMapper;

    public boolean existsClient(String appid,String appkey){

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClient_appid(appid);
        clientEntity.setClient_appkey(appkey);
        if(clientMapper.getClient(clientEntity)==1)
            return true;
        return false;
    }


}
