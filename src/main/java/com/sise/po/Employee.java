package com.sise.po;

import java.sql.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 17:53 2018/2/4
 */
public class Employee {

    private Integer employeeId;

    private String password;

    private String employeeName;

    private Date employeeBirth;

    private String employeeIdCard;

    private Date joinedDate;

    private Job job;


    public Employee() {
    }

    public Employee(Integer employeeId, String password, String employeeName, Date employeeBirth, String employeeIdCard, Date joinedDate, Job job) {
        this.employeeId = employeeId;
        this.password = password;
        this.employeeName = employeeName;
        this.employeeBirth = employeeBirth;
        this.employeeIdCard = employeeIdCard;
        this.joinedDate = joinedDate;
        this.job = job;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getEmployeeBirth() {
        return employeeBirth;
    }

    public void setEmployeeBirth(Date employeeBirth) {
        this.employeeBirth = employeeBirth;
    }

    public String getEmployeeIdCard() {
        return employeeIdCard;
    }

    public void setEmployeeIdCard(String employeeIdCard) {
        this.employeeIdCard = employeeIdCard;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", password='" + password + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeBirth=" + employeeBirth +
                ", employeeIdCard='" + employeeIdCard + '\'' +
                ", joinedDate=" + joinedDate +
                ", job=" + job +
                '}';
    }
}
