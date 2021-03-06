package com.wheatek.lockserver.mapper;

import com.wheatek.lockserver.bean.Lock;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LockMapper {
    @Select("select * from bt_lock where user_id = #{userId}")
    public List<Lock> getLockByUserId(String userId);

    @Select("select authorized_ids from bt_lock where lock_id = #{lockId}")
    public String getAuthorizedIds(@Param("lockId") String lockId);

    @Update("update bt_lock set authorized_ids = #{authorizedIds} where lock_id = #{lockId}")
    public int updateAuthorizedIds(@Param("lockId") String lockId, @Param("authorizedIds") String authorizedIds);

    @Delete("delete from bt_lock where lock_id = #{lockId}")
    public int deleteLockByLockId(String lockId);

    @Insert("insert into bt_lock(lock_id,lock_name,user_id) values (#{lockId},#{lockName},#{userId})")
    public int insertLock(Lock lock);

    @Update("update bt_lock set user_id = #{userId},lock_name = #{lockName} where user_id = #{userId}")
    public int updateLock(Lock lock);

    @Select("select * from bt_lock")
    List<Lock> getAll();
}
