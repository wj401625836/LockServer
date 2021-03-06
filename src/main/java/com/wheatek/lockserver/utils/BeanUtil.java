package com.wheatek.lockserver.utils;

import com.wheatek.lockserver.bean.SellStatistics;
import com.wheatek.lockserver.bean.User;

public class BeanUtil {
    public static User copyUser(User oldUser, User newUser) {
        if(newUser.getChannelId() != null) {
            oldUser.setChannelId(newUser.getChannelId());
        }

        if (newUser.getUserName() != null) {
            oldUser.setUserName(newUser.getUserName());
        }

        if (newUser.getSecurityType() != null) {
            oldUser.setSecurityType(newUser.getSecurityType());
        }

        if(newUser.getSecurityPassword() != null) {
            oldUser.setSecurityPassword(newUser.getSecurityPassword());
        }

        if (newUser.getSecurityPattern() != null) {
            oldUser.setSecurityPattern(newUser.getSecurityPattern());
        }

        if (newUser.getQqNumber() != null) {
            oldUser.setQqNumber(newUser.getQqNumber());
        }

        if (newUser.getWeixinNumber() != null) {
            oldUser.setWeixinNumber(newUser.getWeixinNumber());
        }

        if (newUser.getLogout() != null) {
            oldUser.setLogout(newUser.getLogout());
        }

        if(newUser.getAvatar() != null) {
            oldUser.setAvatar(newUser.getAvatar());
        }
        
        return oldUser;
    }

    public static SellStatistics copySellStatistics(SellStatistics oldSellStatistics, SellStatistics newSellStatistics) {
        if(newSellStatistics.getAddr() != null) {
            oldSellStatistics.setAddr(newSellStatistics.getAddr());
        }

        if(newSellStatistics.getCountry() != null) {
            oldSellStatistics.setCountry(newSellStatistics.getCountry());
        }

        if(newSellStatistics.getFlag() != null) {
            oldSellStatistics.setFlag(newSellStatistics.getFlag());
        }

        if(newSellStatistics.getCustProjectName() != null) {
            oldSellStatistics.setCustProjectName(newSellStatistics.getCustProjectName());
        }

        if(newSellStatistics.getIntrProjectName() != null) {
            oldSellStatistics.setIntrProjectName(newSellStatistics.getIntrProjectName());
        }

        if(newSellStatistics.getIp() != null) {
            oldSellStatistics.setIp(newSellStatistics.getIp());
        }

        if(newSellStatistics.getLockId() != null) {
            oldSellStatistics.setLockId(newSellStatistics.getLockId());
        }

        if(newSellStatistics.getUserId() != null) {
            oldSellStatistics.setUserId(newSellStatistics.getUserId());
        }

        if(newSellStatistics.getWebType() != null) {
            oldSellStatistics.setWebType(newSellStatistics.getWebType());
        }

        if(newSellStatistics.getVerno() != null) {
            oldSellStatistics.setVerno(newSellStatistics.getVerno());
        }

        return oldSellStatistics;
    }
}
