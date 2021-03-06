package com.wheatek.lockserver.mapper;

import com.wheatek.lockserver.bean.SellStatistics;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SellStatisticsMapper {
    @Select("select * from lock_sell_statistics where lock_id = #{lockId}")
    public SellStatistics getSellStatisticsByLockId(String lockId);

    @Select("select * from lock_sell_statistics where flag = 1")
    public List<SellStatistics> getAllSelledLocks();

    @Insert("insert into lock_sell_statistics(lock_id,cust_project_name,intr_project_name,verno,user_id,flag,ip," +
            "country,addr,web_type) values (#{lockId},#{custProjectName},#{intrProjectName},#{verno},#{userId},#{flag},#{ip},#{country}," +
            "#{addr},#{webType})")
    public int insertSellStatistics(SellStatistics sellStatistics);

    @Update("update lock_sell_statistics set lock_id = #{lockId},cust_project_name = #{custProjectName},intr_project_name = #{intrProjectName}," +
            "verno = #{verno},user_id = #{userId},flag = #{flag},ip = #{ip},country = #{country},addr = #{addr},web_type = #{webType} where lock_id = #{lockId}")
    public int updateSellStatistics(SellStatistics sellStatistics);


    @Select("select * from lock_sell_statistics")
    List<SellStatistics> getAll();
}
