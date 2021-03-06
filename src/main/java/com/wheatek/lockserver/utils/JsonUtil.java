package com.wheatek.lockserver.utils;

import com.alibaba.fastjson.JSONObject;
import com.wheatek.lockserver.bean.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class JsonUtil {

    public static JSONObject getJSONParam(HttpServletRequest request) {
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }

    /*
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
    */
    public static User getJsonUser(JSONObject jsonParam) {
        String userId = jsonParam.getString("userId");
        String channelId = jsonParam.getString("channelId");
        String userName = jsonParam.getString("userName");
        Integer securityType = jsonParam.getInteger("securityType");
        String securityPassword = jsonParam.getString("securityPassword");
        String securityPattern = jsonParam.getString("securityPattern");
        String qqNumber = jsonParam.getString("qqNumber");
        String weixinNumber = jsonParam.getString("weixinNumber");
        Integer logout = jsonParam.getInteger("logout");
        String avatar = jsonParam.getString("avatar");

        User user = new User(userId,channelId,userName, securityType, securityPassword, securityPattern, qqNumber, weixinNumber, logout, avatar);

        return user;
    }

    /*
    private Integer id;
    private String lockId;
    private String unlockTime;
    private Integer unlockType;
    private String unlockUserId;
    private Date createTime;
    private Date updateTime;
    */
    public static UnlockRecord getJsonUnlockRecord(JSONObject jsonParam) {
        String lockId = jsonParam.getString("lockId");
        Date unlockTime = jsonParam.getDate("unlockTime");
        Integer unlockType = jsonParam.getInteger("unlockType");
        String unlockUserId = jsonParam.getString("unlockUserId");
        UnlockRecord unlockRecord = new UnlockRecord(lockId, unlockTime, unlockType, unlockUserId);
        return unlockRecord;
    }


//    private String userId;//锁用户id
//    private String frUserId; //用户名
//    private String frUserName; //自定义好友昵称
//    private Date createTime;//添加时间
//    private Date updateTime;//更新时间

    public static Friend getJsonFriend(JSONObject jsonParam) {
        String userId = jsonParam.getString("userId");
        String frUserId = jsonParam.getString("frUserId");
        String frUserName = jsonParam.getString("frUserName");

        Friend user = new Friend(userId, frUserId, frUserName);
        return user;
    }

//    private Integer id;
//    private String lockId;
//    private String userId;
    public static Lock getJsonLock(JSONObject jsonParam) {
        String lockId = jsonParam.getString("lockId");
        String lockName = jsonParam.getString("lockName");
        String userId = jsonParam.getString("userId");
        Lock lock = new Lock(lockId, lockName, userId);
        return lock;
    }

    /*
    private Integer id;
    private String lockId;
    private String custProjectName;
    private String intrProjectName;
    private String userId;
    private Integer flag;
    private String ip;
    private String country;
    private String addr;
    private String webType;
    private Date createTime;
    private Date updateTime;
    */
    public static SellStatistics getJsonSellStatistics(JSONObject jsonParam) {
        String lockId = jsonParam.getString("lockId");
        String custProjectName = jsonParam.getString("custProjectName");
        String intrProjectName = jsonParam.getString("intrProjectName");
        String verno = jsonParam.getString("verno");
        String userId = jsonParam.getString("userId");
        Integer flag = jsonParam.getInteger("flag");
        String ip = jsonParam.getString("ip");
        String country = jsonParam.getString("country");
        String addr = jsonParam.getString("addr");
        String webType = jsonParam.getString("webType");

        SellStatistics sellStatistics = new SellStatistics(lockId, custProjectName, intrProjectName, verno, userId, flag, ip, country, addr, webType);
        return sellStatistics;
    }
}
