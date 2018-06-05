package com.wheatek.lockserver.bean;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String phoneNumber;
    private String userName;
    private Integer securityType;
    private String securityPassword;
    private String securityPattern;
    private String qqNumber;
    private String weixinNumber;
    private Integer logout;
    private String avatar;
    private Date createTime;
    private Date updateTime;
}
