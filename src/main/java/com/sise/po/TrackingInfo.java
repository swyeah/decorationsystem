package com.sise.po;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:01 2018/2/13
 */
public class TrackingInfo implements Serializable{

    private static final long serialVersionUID = 150085051683669283L;
    public Integer infoId;

    @NotNull(message = "跟踪内容不能为空")
    @Size(min = 1,max = 50,message = "字数必须在1-50之间")
    public String infoContent;

    public Date infoTime;

    public Employee employee;

    @NotNull(message = "跟踪登记不能为空")
    public Integer registrationId;

    @NotNull(message = "跟踪方式不能为空")
    public String infoType;

    @NotNull(message = "跟踪人员不能为空")
    public Integer employeeId;

    @NotNull(message = "不能为空")
    public boolean warning;
    @NotNull(message = "不能为空")
    public String businessStatus;

    public TrackingInfo() {
    }

    public TrackingInfo( String infoContent, Date infoTime, Integer registrationId, String infoType, Integer employeeId, boolean warning, String businessStatus) {
        this.infoContent = infoContent;
        this.infoTime = infoTime;
        this.registrationId = registrationId;
        this.infoType = infoType;
        this.employeeId = employeeId;
        this.warning = warning;
        this.businessStatus = businessStatus;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    @Override
    public String toString() {
        return "TrackingInfo{" +
                "infoId=" + infoId +
                ", infoContent='" + infoContent + '\'' +
                ", infoTime=" + infoTime +
                ", employee=" + employee +
                ", registrationId=" + registrationId +
                ", infoType='" + infoType + '\'' +
                ", employeeId=" + employeeId +
                ", warning=" + warning +
                ", businessStatus='" + businessStatus + '\'' +
                '}';
    }
}
