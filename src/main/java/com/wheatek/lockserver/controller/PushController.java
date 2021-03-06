package com.wheatek.lockserver.controller;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.wheatek.lockserver.service.BaiduPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/push")
public class PushController {
    @Autowired
    BaiduPushService baiduPushDao;

    //send?title=title&description=description
    @ResponseBody
    @GetMapping("/send")
    public String getUser(HttpServletRequest request) {
        String title,description,channelId;
        try {
            title = request.getParameter("title");
            description = request.getParameter("description");
            channelId = request.getParameter("channelid");
            baiduPushDao.sendToChannelId(channelId, title ,description);
        } catch (PushClientException e) {
            e.printStackTrace();
            return "fail";
        } catch (PushServerException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success"+"  channelId  :"+channelId+"  title  :"+title+"  description :  "+description;
    }

}
