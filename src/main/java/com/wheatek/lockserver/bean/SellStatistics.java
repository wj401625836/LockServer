package com.wheatek.lockserver.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SellStatistics {
    private Integer id;
    private String lockId;
    private String custProjectName;
    private String intrProjectName;
    private String verno;
    private String userId;
    private Integer flag;
    private String ip;
    private String country;
    private String addr;
    private String webType;
    private Date createTime;
    private Date updateTime;

    public SellStatistics(){

    }

    public SellStatistics(String lockId, String custProjectName, String intrProjectName, String verno, String userId, Integer flag, String ip, String country, String addr, String webType) {
        this.lockId = lockId;
        this.custProjectName = custProjectName;
        this.intrProjectName = intrProjectName;
        this.verno = verno;
        this.userId = userId;
        this.flag = flag;
        this.ip = ip;
        this.country = country;
        this.addr = addr;
        this.webType = webType;
    }

}
