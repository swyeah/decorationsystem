package com.sise.po;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:54 2018/2/23
 */
public class Contract {

    private Integer contractId;

    private BigDecimal contractMoney;

    private String contractName;

    private String contractDescribe;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Integer registrationId;

    private java.util.Date uploadTime;

    private Integer version;

    private DecorationMode decorationMode;

    public Contract() {
    }

    public Contract(BigDecimal contractMoney, String contractName, String contractDescribe, Date startTime, Date endTime, Integer registrationId, java.util.Date uploadTime, DecorationMode decorationMode) {
        this.contractMoney = contractMoney;
        this.contractName = contractName;
        this.contractDescribe = contractDescribe;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationId = registrationId;
        this.uploadTime = uploadTime;
        this.decorationMode = decorationMode;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractDescribe() {
        return contractDescribe;
    }

    public void setContractDescribe(String contractDescribe) {
        this.contractDescribe = contractDescribe;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public java.util.Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(java.util.Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public DecorationMode getDecorationMode() {
        return decorationMode;
    }

    public void setDecorationMode(DecorationMode decorationMode) {
        this.decorationMode = decorationMode;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", contractMoney=" + contractMoney +
                ", contractName='" + contractName + '\'' +
                ", contractDescribe='" + contractDescribe + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", registrationId=" + registrationId +
                ", uploadTime=" + uploadTime +
                ", version=" + version +
                ", decorationMode=" + decorationMode +
                '}';
    }
}
