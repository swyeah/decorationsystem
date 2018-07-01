package com.sise.service.serviceimpl;

import com.sise.dao.TrackingInfoDao;
import com.sise.po.TrackingInfo;
import com.sise.service.TrackingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:37 2018/2/13
 */
@Service
public class TrackingInfoServiceImpl implements TrackingInfoService {
    @Autowired
    private TrackingInfoDao trackingInfoDao;

    public List<TrackingInfo> searchTrackingInfoByRegistrationId(Integer registrationId) {
        return trackingInfoDao.findTrackingInfoByRegistrationId(registrationId);
    }

    public Integer saveTrackingInfo(TrackingInfo trackingInfo) {
        return  trackingInfoDao.insertTrackingInfo(trackingInfo);

    }

    public Integer changTrackingInfo(Integer infoId, String infoContent, String infoType,boolean isWarning,String businessStatus) {
        return trackingInfoDao.updateTrackingInfoById(infoId,infoContent,infoType,isWarning,businessStatus);
    }

    public TrackingInfo searchTrackingInfo(Integer infoId) {
        return trackingInfoDao.findTrackingInfoByInfoId(infoId);
    }

    public Integer changRistrationWarningTimes(Integer registrationId) {
        return trackingInfoDao.countWarning(registrationId);
    }

    public Integer changRistrationTrackingTime(Integer registrationId) {
        return trackingInfoDao.countInfoAll(registrationId);
    }
}
