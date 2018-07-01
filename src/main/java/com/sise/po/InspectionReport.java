package com.sise.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 12:14 2018/3/5
 */
public class InspectionReport implements Serializable{


    private static final long serialVersionUID = 4059334188274933461L;

    private Integer reportId;

    private Date uploadTime;

    private Integer version;

    private Integer reportStatus;

    private Integer registrationId;

    private Employee employee;

    public InspectionReport() {
    }

    @Override
    public String toString() {
        return "InspectionReport{" +
                "reportId=" + reportId +
                ", uploadTime=" + uploadTime +
                ", version=" + version +
                ", reportStatus=" + reportStatus +
                ", registrationId=" + registrationId +
                ", employee=" + employee +
                '}';
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
