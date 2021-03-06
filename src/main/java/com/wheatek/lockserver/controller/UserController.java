package com.wheatek.lockserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.wheatek.lockserver.bean.User;
import com.wheatek.lockserver.common.JsonResult;
import com.wheatek.lockserver.service.UnlockRecordService;
import com.wheatek.lockserver.service.UserService;
import com.wheatek.lockserver.utils.BeanUtil;
import com.wheatek.lockserver.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UnlockRecordService unlockRecordService;

    @ResponseBody
    @GetMapping("/get/{userId}")
    public JsonResult getUser(@PathVariable("userId") String userId){
        User user = userService.getUserByuserId(userId);
        if (user != null) {
            return JsonResult.success("查询成功",user);
        }
        return JsonResult.failure("没有此用户");
    }

    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateUserByuserId(@RequestBody JSONObject jsonParam){
        User user = JsonUtil.getJsonUser(jsonParam);
        User oldUser = userService.getUserByuserId(user.getUserId());
        if (oldUser == null) {
            return JsonResult.failure("不存在要更新的联系人");
        }
        user = BeanUtil.copyUser(oldUser,user);
        int result = 0;
        try {
            result = userService.updateUser(user);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("不存在");
        }
        if (result == 1) {
            return JsonResult.success("更新成功");
        }
        return JsonResult.failure("失败");
    }

    @ResponseBody
    @RequestMapping(value ="/updateOtherLock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateOtherLockByuserId(@RequestBody JSONObject jsonParam){
        String userId = jsonParam.getString("userId");
        String otherLockId = jsonParam.getString("otherLockId");
        String otherLockName = jsonParam.getString("otherLockName");
        String otherLockIds = userService.getOtherLockIds(userId);
        String otherLockNames = userService.getOtherLockNames(userId);
        if (!StringUtils.isEmpty(otherLockIds)) {
            String[] otherLockIdsList = otherLockIds.split(",");
            String[] otherLockNamesList = otherLockNames.split(",");
            int index = Arrays.binarySearch(otherLockIdsList,otherLockId);
            if (index >= 0) {
                otherLockNamesList[index] = otherLockName;
                otherLockNames = StringUtils.arrayToDelimitedString(otherLockNamesList, ",");
            } else {
                otherLockIds = otherLockIds + "," + otherLockId;
                otherLockNames = otherLockNames +"," + otherLockName;
            }
        } else {
            otherLockIds = otherLockId;
            otherLockNames = otherLockName;
        }
        int result = userService.updateOtherLockByuserId(userId, otherLockIds,otherLockNames);
        if (result == 1) {
            return  JsonResult.success("更新授权锁信息成功");
        }
        return JsonResult.failure("更新授权锁信息失败");
    }

    @ResponseBody
    @RequestMapping("/delete/{userId}")
    public JsonResult deleteUserByuserId(@PathVariable("userId") String userId){
        int result = userService.deleteUserByuserId(userId);
        if(result != 0) {
            unlockRecordService.deleteUnlockRecordByUnlockUser(userId);
            return JsonResult.success("删除成功");
        }
        return JsonResult.failure("不存在要删除的用户");
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult insertUser(@RequestBody JSONObject jsonParam) {
        User user = JsonUtil.getJsonUser(jsonParam);
        int result = 0;
        try {
            result = userService.insertUser(user);
        } catch (DuplicateKeyException exception) {
            String cause = exception.getCause().toString();
            int index = cause.lastIndexOf("'",1);
            log.error("insert user DuplicateKeyException " + cause + " / index = " + index);
            return JsonResult.failure("已存在");
        }
        if (result == 1) {
            return JsonResult.success("存储成功");
        }
        return JsonResult.failure("失败");
    }

    @GetMapping("/userlist")
    public String userList(Model model){
        List<User> userList = userService.getAll();
        System.out.println("userList"+ Arrays.asList(userList));
        model.addAttribute("userlist",userList);
        return "/user/list";
    }
}
