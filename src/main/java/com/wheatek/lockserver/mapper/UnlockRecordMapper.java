package com.wheatek.lockserver.mapper;

import com.wheatek.lockserver.bean.UnlockRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UnlockRecordMapper {

    //@Select("select * from unlock_record where lock_id = #{lockId}")
    //public ArrayList<UnlockRecord> getUnlockRecordByLockId(String lockId);

    @Select("select * from unlock_record where lock_id = #{lockId}")
    public Page<UnlockRecord> getUnlockRecordByLockId(@Param("lockId") String lockId, Pageable pageable);

    @Select("select a.*,b.user_name as unlock_user_name from unlock_record as a,user as b where a.lock_id = #{lockId} and a.unlock_user_id = b.user_id order by a.unlock_time desc")
    public List<UnlockRecord> getUnlockRecord(String lockId);

    @Delete("delete from unlock_record where unlock_user_id = #{unlockUserId}")
    public int deleteUnlockRecordByBtUnlockUser(@Param("unlockUserId") String unlockUserId);

    @Insert("insert into unlock_record(lock_id,unlock_time,unlock_type,unlock_user_id) values " +
            "(#{lockId},#{unlockTime},#{unlockType},#{unlockUserId})")
    public int insertUnlockRecord(UnlockRecord unlockRecord);

    @Update("update unlock_record set lock_id = #{lockId},unlock_time = #{unlockTime}" +
            ",unlock_type = #{unlockType},unlock_user_id = #{unlockUserId} where lock_id = #{lockId}")
    public int updateUnlockRecord(UnlockRecord unlockRecord);
}
