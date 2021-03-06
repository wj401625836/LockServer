package com.wheatek.lockserver.service;

import com.wheatek.lockserver.bean.User;
import com.wheatek.lockserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserByuserId(String userId) {
        return userMapper.getUserByuserId(userId);
    }

    public User getUserByuserqqNumber(String qqNumber) {
        return userMapper.getUserByuserqqNumber(qqNumber);
    }


    public User getUserByuserweixinNumber(String weixinNumber) {
        return userMapper.getUserByuserweixinNumber(weixinNumber);
    }

    public String getUserNameByuserId(String userId) {
        return userMapper.getUserNameByuserId(userId);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int deleteUserByuserId(String userId) {
        return userMapper.deleteUserByuserId(userId);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int updateOtherLockByuserId(String userId, String otherLockIds, String otherLocknames) {
        return userMapper.updateOtherLockByuserId(userId, otherLockIds, otherLocknames);
    }

    public String getOtherLockIds(String userId) {
        return userMapper.getOtherLockIds(userId);
    }

    public String getOtherLockNames(String userId) {
        return userMapper.getOtherLocknames(userId);
    }

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public User getUserByChannelId(String ChannelId) {
        return userMapper.getUserByChannelId(ChannelId);
    }
}
