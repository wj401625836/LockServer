package com.wheatek.lockserver.bean;

import lombok.Data;

import javax.validation.valueextraction.ExtractedValue;
import java.util.Date;

@Data
public class Friend {
    private String userId;//锁用户id
    private String frUserId; //用户名
    private String frUserName; //自定义好友昵称
    @ExtractedValue
    private String avatar;//头像
    private Date createTime;//添加时间
    private Date updateTime;//更新时间

    public Friend() {
    }

    public Friend(String userId, String frUserId, String frUserName) {
        this.userId = userId;
        this.frUserId = frUserId;
        this.frUserName = frUserName;
    }
}
