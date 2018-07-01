package com.sise.service;

import com.sise.po.TrackingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:22 2018/2/13
 */
public interface TrackingInfoService {

    public List<TrackingInfo> searchTrackingInfoByRegistrationId(Integer registrationId);

    public Integer saveTrackingInfo(TrackingInfo trackingInfo);

    public Integer changTrackingInfo(Integer infoId,String infoContent,String infoType,boolean isWarning,String businessStatus);

    public TrackingInfo searchTrackingInfo(Integer infoId);

    public Integer changRistrationWarningTimes(Integer registrationId);

    public Integer changRistrationTrackingTime(Integer registrationId);


}
