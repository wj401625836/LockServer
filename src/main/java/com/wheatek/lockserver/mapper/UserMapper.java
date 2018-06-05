package com.wheatek.lockserver.mapper;

import com.wheatek.lockserver.bean.User;

public interface UserMapper {
    //@Select("select * from user where phone_number = #{phoneNumber}")
    public User getUserByPhoneNumber(String phoneNumber);

    //@Delete("delete from user where phone_number = #{phoneNumber}")
    public int deleteUserByPhoneNumber(String phoneNumber);

    //@Insert("insert into user(user_name,phone_number) values (#{userName},#{phoneNumber})")
    public int insertUser(User user);

    //@Update("update user(user_name) values (#{userName}) where phone_number = #{phoneNumber}")
    public int updateUser(User user);
}
