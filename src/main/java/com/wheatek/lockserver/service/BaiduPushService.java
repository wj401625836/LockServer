package com.wheatek.lockserver.service;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import org.springframework.stereotype.Service;

@Service
public interface BaiduPushService {
    boolean sendToAll()throws PushClientException,PushServerException;
    boolean sendToChannelId(String channelId ,String title,String description)throws PushClientException,PushServerException;
}
