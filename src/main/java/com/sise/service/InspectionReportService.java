package com.sise.service;

import com.sise.po.InspectionReport;
import com.sise.po.InspectionReportPic;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 19:10 2018/3/5
 */
public interface InspectionReportService {

    /*插入一个工程验收报告*/
    public Integer saveInspectionReport(InspectionReport inspectionReport);

    /*更新一个工程验收报告*/
    public Integer changeInspectionReport(InspectionReport inspectionReport);

    /*删除一个工程验收报告*/
    public Integer dropInspectionReport( Integer registrationId);

    /*查找registrationid工程验收报告*/
    public InspectionReport searchInspectionReportByRegistrationId( Integer registrationId);

    /*插入工程验收报告图片*/
    public Integer saveInspectionReportPic(List<InspectionReportPic> inspectionReportPics);

    /*删除工程验收报告图片*/
    public Integer dropInspectionReportPicByReportId(Integer reportId);

    /*根据reportId查找验收报告图片*/
    public List<InspectionReportPic> searchInspectionReportpicByReportId(Integer reportId);

    /*得到验收报告版本号*/
    public Integer getInspectionReportVersionByRegistrationId(Integer registrationId);
}
