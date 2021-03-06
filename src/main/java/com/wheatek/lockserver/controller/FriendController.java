package com.wheatek.lockserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.wheatek.lockserver.bean.Friend;
import com.wheatek.lockserver.common.JsonResult;
import com.wheatek.lockserver.service.FriendService;
import com.wheatek.lockserver.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;

    /**
     * 获取授权列表
     * @param userId
     * @return
     */
    @GetMapping("/getlist/{userId}")
    public JsonResult getUser(@PathVariable("userId") String userId) {
        List<Friend> friendlist = friendService.getFriendListByuserId(userId);
        if (friendlist != null) {
            return JsonResult.success("查询成功", friendlist);
        }
        return JsonResult.failure("此用户暂没有好友");
    }

    /**
     * 单个好友更新
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateUserByuserId(@RequestBody JSONObject jsonParam) {
        Friend friend = JsonUtil.getJsonFriend(jsonParam);
        int result = 0;
        try {
            result = friendService.updateFriend(friend);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("不存在");
        }
        if (result == 1) {
            return JsonResult.success("更新成功");
        }
        return JsonResult.failure("失败");
    }

    /**
     * 删除好友
     * @param jsonParam  Friend 对象的json
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult deleteUserByuserId(@RequestBody JSONObject jsonParam) {
        Friend friend = JsonUtil.getJsonFriend(jsonParam);
        int result = friendService.deleteFriendByBean(friend);
        if (result != 0) {
            return JsonResult.success("删除成功");
        }
        return JsonResult.failure("不存在要删除的用户");
    }

    /**
     * 添加一个好友
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult insertUser(@RequestBody JSONObject jsonParam) {
        Friend friend = JsonUtil.getJsonFriend(jsonParam);
        int result = 0;
        try {
            result = friendService.insertFriend(friend);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("已存在");
        }
        if (result == 1) {
            return JsonResult.success("存储成功");
        }
        return JsonResult.failure("失败");
    }
}
