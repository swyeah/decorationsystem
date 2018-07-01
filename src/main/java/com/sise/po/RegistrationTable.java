package com.sise.po;

import java.sql.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:04 2018/2/4
 */
public class RegistrationTable {

    private Integer registrationId;

    private Date registrationTime;

    private String registrationName;

    private String registrationPhone;

    private String registrationArea;

    private String registrationAddress;

    private City city;

    private String registrationSource;

    private Employee employee;

    private Integer status;

    private String userName;

    private Integer version;

    private Employee designer;

    private String businessStatus;

    private Date notrackingDays;

    private Integer trackingTimes;

    private Integer warningTimes;

    private Integer days;

    /*json占位属性*/
    private Integer cityId;

    private Integer employeeId;

    private Integer designerId;

    private Appointment appointment;

    private Deposit deposit;

    private Integer housepicStatus;

    private Integer designerpicStatus;

    private Integer contractStatus;

    private Date startTime;

    private Date endTime;

    private Integer projectStatus;//合同客户的工程状态


    public RegistrationTable() {
    }

    public RegistrationTable(Date registrationTime, String registrationName, String registrationPhone, String registrationArea, String registrationAddress, City city, String registrationSource, Employee employee, Integer status, String userName, Integer version, Employee designer, String businessStatus, Date notrackingDays, Integer trackingTimes, Integer warningTimes) {
        this.registrationTime = registrationTime;
        this.registrationName = registrationName;
        this.registrationPhone = registrationPhone;
        this.registrationArea = registrationArea;
        this.registrationAddress = registrationAddress;
        this.city = city;
        this.registrationSource = registrationSource;
        this.employee = employee;
        this.status = status;
        this.userName = userName;
        this.version = version;
        this.designer = designer;
        this.businessStatus = businessStatus;
        this.notrackingDays = notrackingDays;
        this.trackingTimes = trackingTimes;
        this.warningTimes = warningTimes;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getRegistrationPhone() {
        return registrationPhone;
    }

    public void setRegistrationPhone(String registrationPhone) {
        this.registrationPhone = registrationPhone;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getRegistrationSource() {
        return registrationSource;
    }

    public void setRegistrationSource(String registrationSource) {
        this.registrationSource = registrationSource;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Employee getDesigner() {
        return designer;
    }

    public void setDesigner(Employee designer) {
        this.designer = designer;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Integer getTrackingTimes() {
        return trackingTimes;
    }

    public void setTrackingTimes(Integer trackingTimes) {
        this.trackingTimes = trackingTimes;
    }

    public Integer getWarningTimes() {
        return warningTimes;
    }

    public void setWarningTimes(Integer warningTimes) {
        this.warningTimes = warningTimes;
    }

    public String getRegistrationArea() {
        return registrationArea;
    }

    public void setRegistrationArea(String registrationArea) {
        this.registrationArea = registrationArea;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getNotrackingDays() {
        return notrackingDays;
    }

    public void setNotrackingDays(Date notrackingDays) {
        this.notrackingDays = notrackingDays;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public Integer getHousepicStatus() {
        return housepicStatus;
    }

    public void setHousepicStatus(Integer housepicStatus) {
        this.housepicStatus = housepicStatus;
    }

    public Integer getDesignerpicStatus() {
        return designerpicStatus;
    }

    public void setDesignerpicStatus(Integer designerpicStatus) {
        this.designerpicStatus = designerpicStatus;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
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

    @Override
    public String toString() {
        return "RegistrationTable{" +
                "registrationId=" + registrationId +
                ", registrationTime=" + registrationTime +
                ", registrationName='" + registrationName + '\'' +
                ", registrationPhone='" + registrationPhone + '\'' +
                ", registrationArea='" + registrationArea + '\'' +
                ", registrationAddress='" + registrationAddress + '\'' +
                ", registrationSource='" + registrationSource + '\'' +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                ", version=" + version +
                ", businessStatus='" + businessStatus + '\'' +
                ", notrackingDays=" + notrackingDays +
                ", trackingTimes=" + trackingTimes +
                ", warningTimes=" + warningTimes +
                ", cityId=" + cityId +
                ", employeeId=" + employeeId +
                ", designerId=" + designerId +
                '}';
    }
}
