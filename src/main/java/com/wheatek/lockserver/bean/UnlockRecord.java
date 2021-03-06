package com.wheatek.lockserver.bean;

import lombok.Data;

import javax.validation.valueextraction.ExtractedValue;
import java.util.Date;

@Data
public class UnlockRecord {
    private Integer id;
    private String lockId;
    private String unlockUserId;
    private Integer unlockType;
    private Date unlockTime;
    @ExtractedValue
    private String unlockUserName;
    private Date createTime;
    private Date updateTime;

    public UnlockRecord() {

    }

    public UnlockRecord(String lockId, Date unlockTime, Integer unlockType, String unlockUserId) {
        this.lockId = lockId;
        this.unlockTime = unlockTime;
        this.unlockType = unlockType;
        this.unlockUserId = unlockUserId;
    }
}
