package com.wheatek.lockserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.wheatek.lockserver.bean.User;
import com.wheatek.lockserver.common.JsonResult;
import com.wheatek.lockserver.service.BaiduPushService;
import com.wheatek.lockserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserLoginController {


    @Autowired
    UserService userService;

    @Autowired
    BaiduPushService baiduService;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 用户登陆操作
     *
     * @return 用户信息
     */

    @ResponseBody
    @RequestMapping(value = "/userlogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult userlogin(@RequestBody JSONObject jsonParam) {
        String channelid = jsonParam.getString("channel_id");
        Integer user_type = jsonParam.getInteger("user_type");
        String number = jsonParam.getString("number");

        //一.登陆获取用户信息
        User user = new User();
        switch (user_type) {
            //1.查用户数据
            //QQ
            case 1:
                user = userService.getUserByuserqqNumber(number);
                break;
            //微信
            case 2:
                user = userService.getUserByuserweixinNumber(number);
                break;
            //手机 登录
            default:
                user = userService.getUserByuserId(number);
                break;
        }

        //二.判断用户是否存在 ，查询到用户不为空，手机号码不为空。 即用户存在返回用户信息。
        if (user != null && !StringUtils.isEmpty(user.getUserId())) {

            //当前登陆的ChannelId 等于用户存储的ChannelId 说明旧的手机登陆。
            if (channelid.equals(user.getChannelId())) {
                return JsonResult.success(JsonResult.SUCCESS_STR, user);
            } else {
                //1.发送旧的设备离线通知
                try {
                    baiduService.sendToChannelId(user.getChannelId(), "离线通知", "您的账号在xxx手机登陆");
                } catch (PushClientException e) {
                    e.printStackTrace();
                } catch (PushServerException e) {
                    e.printStackTrace();
                }
                //2.更新用户数据库表格
                user.setChannelId(channelid);
                //3.更新数据库表格 ChannelId
                userService.updateUser(user);
                return JsonResult.success(JsonResult.SUCCESS_STR, user);
            }
        }
        //三.未注册用户
        else {
            //用户设置手机号码。
            //返回手机号码为空 请设置手机号码。

            //1.判断该手机是否绑定过百度push
            //2.首先登出的时候 数据库删除ChannelId
            //3.每次登陆查询 当前ChannelId是否被绑定  且手机号码一致。
            User loginuser = userService.getUserByChannelId(channelid);
            if (loginuser != null && channelid.equals(loginuser.getChannelId())) {
                logger.info("rockey  更新用户");
                loginuser.setChannelId("");
                loginuser.setLogout(0);
                userService.updateUser(loginuser);
            }
            //切换了手机号码 但是channelid 没变 channelid唯一导致数据插入失败。
            if (user_type == 0) {
                User registeruser = new User();
                registeruser.setChannelId(channelid);
                registeruser.setUserId(number);
                registeruser.setUserName(number);
                logger.info("rockey  " + registeruser.toString());
                userService.insertUser(registeruser);
                return JsonResult.success(JsonResult.SUCCESS_STR, registeruser);
            } else {
                //切换到手机号码验证码阶段
                return JsonResult.failure(JsonResult.SET_PHONE_NUMBER_STR);
            }
        }
    }

    /**
     * 三方登陆第二次手机号码验证
     *
     * @param jsonParam
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/threelogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult rd(@RequestBody JSONObject jsonParam) {
        String channelid = jsonParam.getString("channel_id");
        Integer user_type = jsonParam.getInteger("user_type");
        String phonenumber = jsonParam.getString("phone_number");
        String threeId = jsonParam.getString("three_id");
        User search = userService.getUserByuserId(phonenumber);
        if (search != null) {
            return JsonResult.failure(JsonResult.PHONE_IS_SAVE);
        } else {
            User registeruser = new User(phonenumber, channelid, phonenumber, 1);
            if (user_type == 1) {
                registeruser.setQqNumber(threeId);
            } else {
                registeruser.setWeixinNumber(threeId);
            }
            userService.insertUser(registeruser);
            return JsonResult.success(JsonResult.SUCCESS_STR, registeruser);
        }
    }
}
