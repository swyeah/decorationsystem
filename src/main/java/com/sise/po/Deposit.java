package com.sise.po;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 14:51 2018/2/18
 */
public class Deposit {

    private Integer depositId;

    private Date depositTime;

    private BigDecimal depositMoney;

    private String depositUrl;

    private String depositDescribe;

    private Integer registrationId;

    private String filename;

    private Integer employeeId;

    private java.util.Date uploadTime;


    public Deposit() {
    }

    public Deposit(Date depositTime, BigDecimal depositMoney, String depositUrl, String depositDescribe, Integer registrationId, String filename, Integer employeeId, java.util.Date uploadTime) {
        this.depositTime = depositTime;
        this.depositMoney = depositMoney;
        this.depositUrl = depositUrl;
        this.depositDescribe = depositDescribe;
        this.registrationId = registrationId;
        this.filename = filename;
        this.employeeId = employeeId;
        this.uploadTime = uploadTime;
    }

    public Integer getDepositId() {
        return depositId;
    }

    public void setDepositId(Integer depositId) {
        this.depositId = depositId;
    }

    public Date getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(Date depositTime) {
        this.depositTime = depositTime;
    }

    public BigDecimal getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(BigDecimal depositMoney) {
        this.depositMoney = depositMoney;
    }

    public String getDepositUrl() {
        return depositUrl;
    }

    public void setDepositUrl(String depositUrl) {
        this.depositUrl = depositUrl;
    }

    public String getDepositDescribe() {
        return depositDescribe;
    }

    public void setDepositDescribe(String depositDescribe) {
        this.depositDescribe = depositDescribe;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public java.util.Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(java.util.Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositId=" + depositId +
                ", depositTime=" + depositTime +
                ", depositMoney=" + depositMoney +
                ", deposityUrl='" + depositUrl + '\'' +
                ", depositDescribe='" + depositDescribe + '\'' +
                ", registrationId=" + registrationId +
                ", filename='" + filename + '\'' +
                ", employeeId=" + employeeId +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
