package com.sise.dao;

import com.sise.po.TrackingInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:05 2018/2/13
 */
@Repository
public interface TrackingInfoDao {

    /*通过登记记录ID查找跟踪信息*/
    public List<TrackingInfo> findTrackingInfoByRegistrationId(@Param("registrationId") Integer registrationId);

    /*添加跟踪信息*/
    public Integer insertTrackingInfo(TrackingInfo trackingInfo);

    /*更新跟踪信息*/
    public Integer updateTrackingInfoById(@Param("infoId") Integer infoId,@Param("infoContent") String infoContent,@Param("infoType") String infoType,@Param("isWarning") boolean isWarning,@Param("businessStatus") String businessStatus);

    /*通过id查找信息*/
    public TrackingInfo findTrackingInfoByInfoId(@Param("infoId") Integer infoId);

    /*计算警告次数*/
    public Integer countWarning(@Param("registrationId") Integer registrationId);

    /*计算全部消息数量*/
    public Integer countInfoAll(@Param("registrationId") Integer registrationId);
}
