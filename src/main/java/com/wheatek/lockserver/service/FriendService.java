package com.wheatek.lockserver.service;

import com.wheatek.lockserver.bean.Friend;
import com.wheatek.lockserver.bean.User;
import com.wheatek.lockserver.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Configuration
public class FriendService {

    @Autowired
    FriendMapper friendMapper;

    public List<Friend> getFriendListByuserId(String userId){
        return friendMapper.getFriendListByuserId(userId);
    }

    public Friend getOneFriend(Friend friend) {
        return  friendMapper.getOneFriend(friend);
    }

    public int insertFriend(Friend friend) {
       Friend myfriend = friendMapper.haveInsertFriend(friend);
       if(myfriend != null){
           throw new DuplicateKeyException("已经存在");
       }else{
           return friendMapper.insertFriend(friend);
       }
    }

    public int deleteFriendByBean(Friend friend){
        return friendMapper.deleteFriendByBean(friend);
    }

    //这个基本不会使用
    public int updateFriend(Friend friend){
        Friend myfriend = friendMapper.haveInsertFriend(friend);
        //若朋友存在
        if(myfriend != null){
            friendMapper.deleteFriendByBean(friend);
            return friendMapper.insertFriend(friend);
        }else {
            return friendMapper.insertFriend(friend);
        }
    }
}
