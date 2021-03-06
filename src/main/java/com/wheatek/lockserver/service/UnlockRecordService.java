package com.wheatek.lockserver.service;

import com.wheatek.lockserver.bean.UnlockRecord;
import com.wheatek.lockserver.mapper.UnlockRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@Configuration
public class UnlockRecordService {
    @Autowired
    UnlockRecordMapper unlockRecordMapper;

    public Page<UnlockRecord> getUnlockRecordByLockId(String lockId, Pageable pageable){
        return unlockRecordMapper.getUnlockRecordByLockId(lockId,pageable);
    }

    public List<UnlockRecord> getUnlockRecord(String lockId) {
        return unlockRecordMapper.getUnlockRecord(lockId);
    }

    public int deleteUnlockRecordByUnlockUser(String unlockUserId) {
        return unlockRecordMapper.deleteUnlockRecordByBtUnlockUser(unlockUserId);
    }

    public int insertUnlockRecord(UnlockRecord unlockRecord){
        return unlockRecordMapper.insertUnlockRecord(unlockRecord);
    }

    public int updateUnlockRecord(UnlockRecord unlockRecord){
        return unlockRecordMapper.updateUnlockRecord(unlockRecord);
    }
}
