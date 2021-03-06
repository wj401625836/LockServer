package com.wheatek.lockserver.mapper;

import com.wheatek.lockserver.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where user_id = #{userId}")
    public User getUserByuserId(String userId);

    @Select("select * from user where qq_number = #{qqNumber}")
    public User getUserByuserqqNumber(String qqNumber);

    @Select("select * from user where weixin_number = #{weixinNumber}")
    public User getUserByuserweixinNumber(String weixinNumber);

    @Select("select user_name from user where user_id = #{userId}")
    public String getUserNameByuserId(String userId);

    @Delete("delete from user where user_id = #{userId}")
    public int deleteUserByuserId(String userId);

    @Insert("insert into user(user_id,channel_id,user_name,security_type,security_password,security_pattern," +
            "qq_number,weixin_number,avatar) values (#{userId},#{channelId},#{userName},#{securityType},#{securityPassword},#{securityPattern},#{qqNumber}," +
            "#{weixinNumber},#{avatar})")
    public int insertUser(User user);

    @Update("update user set user_id = #{userId},channel_id = #{channelId},user_name = #{userName},security_type = #{securityType},security_password = #{securityPassword}" +
            ",security_pattern = #{securityPattern},qq_number = #{qqNumber},weixin_number = #{weixinNumber},avatar = #{avatar} where user_id = #{userId}")
    public int updateUser(User user);

    @Select("select other_lock_ids from user where user_id = #{userId}")
    public String getOtherLockIds(String userId);

    @Select("select other_lock_names from user where user_id = #{userId}")
    public String getOtherLocknames(String userId);

    @Update("update user set other_lock_ids = #{otherLockIds}, other_lock_names = #{otherLockNames} where user_id = #{userId}")
    public int updateOtherLockByuserId(@Param("userId") String userId, @Param("otherLockIds") String otherLockIds, @Param("otherLockNames") String otherLockNames);

    @Select("select * from user")
    List<User> getAll();

    @Select("select * from user where channel_id = #{ChannelId}")
    public User getUserByChannelId(String ChannelId);

}
