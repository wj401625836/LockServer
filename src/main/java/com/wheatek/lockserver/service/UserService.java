package com.wheatek.lockserver.service;

import com.wheatek.lockserver.bean.User;
import com.wheatek.lockserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserByPhoneNumber(String phoneNumber){
        return userMapper.getUserByPhoneNumber(phoneNumber);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int deleteUserByPhoneNumber(String phoneNumber){
        return userMapper.deleteUserByPhoneNumber(phoneNumber);
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }
}
