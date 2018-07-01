package com.sise.service.serviceimpl;

import com.sise.dao.InspectionReportDao;
import com.sise.po.InspectionReport;
import com.sise.po.InspectionReportPic;
import com.sise.service.InspectionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 19:12 2018/3/5
 */
@Service
public class InspectionReportServiceImpl implements InspectionReportService {
    @Autowired
    private InspectionReportDao inspectionReportDao;
    public Integer saveInspectionReport(InspectionReport inspectionReport) {
        return inspectionReportDao.insertInspectionReport(inspectionReport);
    }


    public Integer changeInspectionReport(InspectionReport inspectionReport) {
        return inspectionReportDao.updateInspectionReport(inspectionReport);
    }

    public Integer dropInspectionReport(Integer registrationId) {
        return inspectionReportDao.delInspectionReport(registrationId);
    }

    public InspectionReport searchInspectionReportByRegistrationId(Integer registrationId) {
        return inspectionReportDao.findInspectionReportByRegistrationId(registrationId);
    }

    public Integer saveInspectionReportPic(List<InspectionReportPic> inspectionReportPics) {
        Integer column = 0;
        if (inspectionReportPics!=null){
            for (InspectionReportPic i:inspectionReportPics){
                column=column+ inspectionReportDao.insertInspectionReportPic(i);
            }
        }
        return column;
    }

    public Integer dropInspectionReportPicByReportId(Integer reportId) {
        return inspectionReportDao.delInspectionReportPicByReportId(reportId);
    }

    public List<InspectionReportPic> searchInspectionReportpicByReportId(Integer reportId) {
        return inspectionReportDao.findInspectionReportpicByReportId(reportId);
    }

    public Integer getInspectionReportVersionByRegistrationId(Integer registrationId) {
        return inspectionReportDao.getInspectionReportVersionByRegistrationId(registrationId);
    }
}
