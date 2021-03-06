package com.wheatek.lockserver.bean;

import lombok.Data;

@Data
public class Lock {
    private Integer id;
    private String lockId;
    private String lockName;
    private String userId;
    private String authorizedIds;

    public Lock() {
    }

    public Lock(String lockId, String lockName, String userId) {
        this.lockId = lockId;
        this.lockName = lockName;
        this.userId = userId;
    }
}
