package com.wheatek.lockserver.service;

import com.wheatek.lockserver.bean.Lock;
import com.wheatek.lockserver.mapper.LockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LockService {
    @Autowired
    LockMapper lockMapper;

    public List<Lock> getLockByUserId(String userId) {
        return lockMapper.getLockByUserId(userId);
    }

    public String getAuthorizedIds(String lockId) {
        return lockMapper.getAuthorizedIds(lockId);
    }

    public int updateAuthorizedIds(String lockId, String authorizedIds) {
        return  lockMapper.updateAuthorizedIds(lockId, authorizedIds);
    }

    public int deleteLockByLockId(String lockId) {
        return lockMapper.deleteLockByLockId(lockId);
    }

    public int insertLock(Lock lock){
        return lockMapper.insertLock(lock);
    }

    public int updateUser(Lock lock){
        return lockMapper.updateLock(lock);

    }

    public List<Lock> getAll(){
        return lockMapper.getAll();
    }
}
