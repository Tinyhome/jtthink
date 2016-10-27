package com.service;

import com.db.Mapper.UserEntity;
import com.db.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tinyhome on 16/7/15.
 */

@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public List<UserEntity> getUserByID(int uid){
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_id(uid);
        return userMapper.selectByID(userEntity);
    }

    public int regUser(UserEntity userEntity){

        return userMapper.addUser(userEntity);
    }

    public boolean UserLoginIsDistinct(String user_login){
        return userMapper.isDistinct(user_login)>0?false:true;
    }
}
