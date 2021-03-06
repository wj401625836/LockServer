package com.wheatek.lockserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.wheatek.lockserver.bean.SellStatistics;
import com.wheatek.lockserver.common.JsonResult;
import com.wheatek.lockserver.service.SellStatisticsService;
import com.wheatek.lockserver.utils.BeanUtil;
import com.wheatek.lockserver.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/sellstatics")
public class SellStaticsController {
    @Autowired
    SellStatisticsService sellStatisticsService;

    @ResponseBody
    @GetMapping("/get/{lockId}")
    public JsonResult getSellStatisticsByLockId(@PathVariable("lockId") String lockId){
        SellStatistics sellStatistics = sellStatisticsService.getSellStatisticsByLockId(lockId);
        if (sellStatistics != null) {
            return JsonResult.success("查询成功",sellStatistics);
        }
        return JsonResult.failure("没有此锁");
    }

    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult updateSellStatistics(@RequestBody JSONObject jsonParam){
        SellStatistics sellStatistics = JsonUtil.getJsonSellStatistics(jsonParam);
        SellStatistics oldSellStatistics = sellStatisticsService.getSellStatisticsByLockId(sellStatistics.getLockId());
        if (oldSellStatistics == null) {
            return JsonResult.failure("不存在要更新的锁");
        }
        sellStatistics = BeanUtil.copySellStatistics(oldSellStatistics, sellStatistics);
        int result = 0;
        try {
            result = sellStatisticsService.updateSellStatistics(sellStatistics);
        } catch (DuplicateKeyException exception) {
            return JsonResult.failure("不存在");
        }
        if (result == 1) {
            return JsonResult.success("更新成功");
        }
        return JsonResult.failure("失败");
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult insertUser(@RequestBody JSONObject jsonParam) {
        SellStatistics sellStatistics = JsonUtil.getJsonSellStatistics(jsonParam);
        int result = 0;
        try {
            result = sellStatisticsService.insertSellStatistics(sellStatistics);
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

    @GetMapping("/sellstaticslist")
    public String userList(Model model){
        List<SellStatistics> sellStatisticsList = sellStatisticsService.getAll();
        System.out.println("sellStatisticsList"+ Arrays.asList(sellStatisticsList));
        model.addAttribute("sellStatisticsList",sellStatisticsList);
        return "/sellstatics/list";
    }
}
