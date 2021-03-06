package com.wheatek.lockserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.wheatek.lockserver.bean.UnlockRecord;
import com.wheatek.lockserver.common.JsonResult;
import com.wheatek.lockserver.service.UnlockRecordService;
import com.wheatek.lockserver.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@Slf4j
@RequestMapping("/unlockrecord")
public class UnlockRecordController {
    @Autowired
    UnlockRecordService unlockRecordService;

    @ResponseBody
    @PostMapping(value = "/get",produces = "application/json;charset=UTF-8")
    public JsonResult getUnlockRecordByLockId(@RequestBody JSONObject jsonParam){
        String lockId = jsonParam.getString("lockId");
        int page = jsonParam.getIntValue("page");
        int size = jsonParam.getIntValue("size");
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<UnlockRecord> unlockRecordPage = unlockRecordService.getUnlockRecordByLockId(lockId,pageRequest);
        List<UnlockRecord> unlockRecordList = unlockRecordPage.getContent();
        if (unlockRecordList.size() != 0) {
            return JsonResult.success("查询开锁记录成功",unlockRecordList);
        }
        return JsonResult.failure("没有此锁记录");
    }

    @ResponseBody
    @GetMapping(value = "/get/{lockId}")
    public JsonResult getUnlockRecord(@PathVariable("lockId") String lockId){
        log.error("getUnlockRecord = " + lockId);
        List<UnlockRecord> unlockRecordList = unlockRecordService.getUnlockRecord(lockId);
        if (unlockRecordList.size() != 0) {
            return JsonResult.success("查询开锁记录成功",unlockRecordList);
        }
        return JsonResult.failure("没有此锁记录");
    }

    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateUserByuserId(@RequestBody JSONObject jsonParam){
        /*User user = JsonUtil.getJsonUser(jsonParam);
        int result = 0;
        try {
            result = unlockRecordService.updateUser(user);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("不存在");
        }
        if (result == 1) {
            return JsonResult.success("更新成功");
        }*/
        return JsonResult.failure("失败");
    }

    @ResponseBody
    @RequestMapping("/delete/{unlockUserId}")
    public JsonResult deleteUnlockRecordByUnlockUser(@PathVariable("unlockUserId") String unlockUserId){
        int result = unlockRecordService.deleteUnlockRecordByUnlockUser(unlockUserId);
        log.info("deleteUnlockRecordByUnlockUser unlockUserId = " + unlockUserId + " / result = " + result);
        if(result != 0) {
            return JsonResult.success("删除解锁记录成功");
        }
        return JsonResult.failure("不存在要删除的锁");
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult insertUser(@RequestBody JSONObject jsonParam) {
        UnlockRecord unlockRecord = JsonUtil.getJsonUnlockRecord(jsonParam);
        log.info("insert UnlockRecord  = " + unlockRecord);
        int result = 0;
        try {
            result = unlockRecordService.insertUnlockRecord(unlockRecord);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("已存在");
        }
        if (result == 1) {
            return JsonResult.success("存储成功");
        }
        return JsonResult.failure("存储失败");
    }
}
