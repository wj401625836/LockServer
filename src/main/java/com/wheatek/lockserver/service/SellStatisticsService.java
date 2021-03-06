package com.wheatek.lockserver.service;

import com.wheatek.lockserver.bean.SellStatistics;
import com.wheatek.lockserver.mapper.SellStatisticsMapper;
import com.wheatek.lockserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SellStatisticsService {

    @Autowired
    SellStatisticsMapper sellStatisticsMapper;

    public SellStatistics getSellStatisticsByLockId(String lockId){
        return sellStatisticsMapper.getSellStatisticsByLockId(lockId);
    }

    public List<SellStatistics> getAllSelledLocks(){
        return sellStatisticsMapper.getAllSelledLocks();
    }

    public int insertSellStatistics(SellStatistics sellStatistics) {
        return sellStatisticsMapper.insertSellStatistics(sellStatistics);
    }

    public int updateSellStatistics(SellStatistics sellStatistics){
        return sellStatisticsMapper.updateSellStatistics(sellStatistics);
    }

    public List<SellStatistics> getAll(){return sellStatisticsMapper.getAll();}
}
