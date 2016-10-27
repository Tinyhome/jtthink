package com.db.Mapper;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tinyhome on 16/7/14.
 */

@Service
public interface UserMapper {
     List<UserEntity> selectAll();
     List<UserEntity> selectByID(UserEntity userEntity);
     int addUser(UserEntity userEntity);
     int addUsers(List<UserEntity> list);
     int editMuti(List<UserEntity> list);
     int isDistinct(String user_login);
}

