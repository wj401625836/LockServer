package com.wheatek.lockserver.mapper;

import com.wheatek.lockserver.bean.Friend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendMapper {


    // 获取到好友列表
    /*@Select("select a.user_id ,b.user_id AS fr_user_id ,a.LockId ,b.user_name , b.avatar ,b.create_time," +
            "b.update_time from lock_friend as a, user as b where a.fr_user_id=b.user_id and a.user_id=#{userId}")*/

    @Select("select a.* ,b.avatar AS avatar from lock_friend as a, user as b where a.fr_user_id=b.user_id and a.user_id=#{userId}")
    public List<Friend> getFriendListByuserId(String userId);

    //获取到一个朋友的详细信息
    @Select("select a.user_id ,b.user_id AS fr_user_id ,a.LockId ,b.user_name , b.avatar ,b.create_time,b.update_time  " +
            "from lock_friend as a, user as b where a.fr_user_id = b.user_id and a.user_id =#{userId} and a.fr_user_id = #{frUserId}")
    public Friend getOneFriend(Friend friend);

    //删除一个好友
    @Delete("delete from lock_friend where user_id = #{userId} and fr_user_id = #{frUserId}")
    public int deleteFriendByBean(Friend friend);

    //查询好友是否存在
    @Select("select * from lock_friend where user_id = #{userId} and  fr_user_id = #{frUserId}")
    public Friend haveInsertFriend(Friend friend);

    //新建一个好友
    @Insert("insert into lock_friend(user_id,fr_user_id,fr_user_name) values (#{userId},#{frUserId},#{frUserName})")
    public int insertFriend(Friend friend);

//    @Update("update user set user_id = #{userId},user_name = #{userName},security_type = #{securityType},security_password = #{securityPassword}" +
//            ",security_pattern = #{securityPattern},qq_number = #{qqNumber},weixin_number = #{weixinNumber},avatar = #{avatar} where user_id = #{userId}")
//    public int updateUser(User user);
}
