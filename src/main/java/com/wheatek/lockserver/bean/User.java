package com.wheatek.lockserver.bean;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String userId;
    private String channelId;
    private String userName;
    private Integer securityType;
    private String securityPassword;
    private String securityPattern;
    private String qqNumber;
    private String weixinNumber;
    private Integer logout;
    private String avatar;
    private String otherLockIds;
    private String otherLockNames;
    private Date createTime;
    private Date updateTime;

    public User() {
    }

    public User(String userId, String userName, Integer securityType, String securityPassword, String securityPattern, String qqNumber, String weixinNumber, Integer logout, String avatar) {
        this.userId = userId;
        this.userName = userName;
        this.securityType = securityType;
        this.securityPassword = securityPassword;
        this.securityPattern = securityPattern;
        this.qqNumber = qqNumber;
        this.weixinNumber = weixinNumber;
        this.logout = logout;
        this.avatar = avatar;
    }

    public User(String userId,String channelId ,String userName, Integer securityType, String securityPassword, String securityPattern, String qqNumber, String weixinNumber, Integer logout, String avatar) {
        this.userId = userId;
        this.userName = userName;
        this.securityType = securityType;
        this.securityPassword = securityPassword;
        this.securityPattern = securityPattern;
        this.qqNumber = qqNumber;
        this.weixinNumber = weixinNumber;
        this.logout = logout;
        this.avatar = avatar;
        this.channelId=channelId;
    }


    public User(String userId,String channelId ,String userName,Integer logout) {
        this.userId = userId;
        this.userName = userName;
        this.logout = logout;
        this.channelId=channelId;
    }
    
}
