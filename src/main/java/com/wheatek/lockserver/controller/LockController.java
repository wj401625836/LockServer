package com.wheatek.lockserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.wheatek.lockserver.bean.Lock;
import com.wheatek.lockserver.common.JsonResult;
import com.wheatek.lockserver.service.LockService;
import com.wheatek.lockserver.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/lock")
public class LockController {

    @Autowired
    LockService lockService;

    @ResponseBody
    @GetMapping("/get/{userId}")
    public JsonResult getLock(@PathVariable("userId") String userId){
        List<Lock> lockList = lockService.getLockByUserId(userId);
        if (lockList.size() != 0) {
            return JsonResult.success("查询成功",lockList);
        }
        return JsonResult.failure("此用户没有绑定锁");
    }

    @ResponseBody
    @GetMapping("/getAuthorizedIds/{lockId}")
    public JsonResult getAuthorizedIds(@PathVariable("lockId") String lockId){
        String authorizedIds = lockService.getAuthorizedIds(lockId);
        if (!StringUtils.isEmpty(authorizedIds)) {
            return JsonResult.success("查询成功",Arrays.asList(authorizedIds.split(",")));
        }
        return JsonResult.failure("此用户没有绑定锁");
    }

    @ResponseBody
    @RequestMapping(value ="/updateAuthorizedIds", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateAuthorizedIds(@RequestBody JSONObject jsonParam){
        String lockId = jsonParam.getString("lockId");
        String authorizedId = jsonParam.getString("authorizedIds");
        int addOrDelete = jsonParam.getIntValue("addOrDelete");//0 add 1 delete
        String authorizedIds = lockService.getAuthorizedIds(lockId);

        if (addOrDelete == 0 ) {
            if (StringUtils.isEmpty(authorizedIds)) {
                authorizedIds = authorizedId;
            } else if (authorizedIds.contains(authorizedId)) {
                return  JsonResult.failure("该用户已授权");
            } else {
                authorizedIds = authorizedIds + "," + authorizedId;
            }
        } else {
            if (!StringUtils.isEmpty(authorizedIds) && authorizedIds.contains(authorizedId)) {
                if (authorizedIds.contains(",")) {
                    authorizedIds = authorizedIds.replace("," + authorizedId ,"");
                } else {
                    authorizedIds = "";
                }
            } else {
                return  JsonResult.failure("该用户未授权");
            }
        }
        int result = lockService.updateAuthorizedIds(lockId, authorizedIds);
        if (result == 1) {
            return  JsonResult.success("更新授权用户信息成功");
        }
        return JsonResult.failure("更新授权用户信息失败");
    }

    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateUserByuserId(@RequestBody JSONObject jsonParam){
        Lock lock = JsonUtil.getJsonLock(jsonParam);

        int result = 0;
        try {
            result = lockService.updateUser(lock);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("不存在");
        }
        if (result == 1) {
            return JsonResult.success("更新成功");
        }
        return JsonResult.failure("失败");
    }

    @ResponseBody
    @RequestMapping("/delete/{lockId}")
    public JsonResult deleteUserByLockId(@PathVariable("lockId") String lockId){
        int result = lockService.deleteLockByLockId(lockId);
        if(result != 0) {
            return JsonResult.success("删除成功");
        }
        return JsonResult.failure("不存在要删除的锁");
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult insertLock(@RequestBody JSONObject jsonParam) {
        Lock lock = JsonUtil.getJsonLock(jsonParam);
        int result = 0;
        try {
            result = lockService.insertLock(lock);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("已存在");
        }
        if (result == 1) {
            return JsonResult.success("存储成功");
        }
        return JsonResult.failure("失败");
    }

    @GetMapping("/locklist")
    public String lockList(Model model){
        List<Lock> lockList = lockService.getAll();
        System.out.println("userList"+ Arrays.asList(lockList));
        model.addAttribute("locklist",lockList);
        return "/lock/list";
    }

}
