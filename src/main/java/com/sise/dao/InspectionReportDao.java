package com.sise.dao;

import com.sise.po.InspectionReport;
import com.sise.po.InspectionReportPic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 14:13 2018/3/5
 */
@Repository
public interface InspectionReportDao {

    /*插入一个工程验收报告*/
    public Integer insertInspectionReport(InspectionReport inspectionReport);

    /*更新一个工程验收报告*/
    public Integer updateInspectionReport(InspectionReport inspectionReport);

    /*删除一个工程验收报告*/
    public Integer delInspectionReport(@Param("registrationId") Integer registrationId);

    /*查找registrationid工程验收报告*/
    public InspectionReport findInspectionReportByRegistrationId(@Param("registrationId") Integer registrationId);

    /*插入工程验收报告图片*/
    public Integer insertInspectionReportPic(InspectionReportPic inspectionReportPic);

    /*删除工程验收报告图片*/
    public Integer delInspectionReportPicByReportId(@Param("reportId") Integer reportId);

    /*根据reportId查找验收报告图片*/
    public List<InspectionReportPic> findInspectionReportpicByReportId(@Param("reportId") Integer reportId);

    /*得到验收报告版本号*/
    public Integer getInspectionReportVersionByRegistrationId(@Param("registrationId") Integer registrationId);
}
