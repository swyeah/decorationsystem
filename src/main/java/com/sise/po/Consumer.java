package com.sise.po;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class Consumer {
    private Integer consId;
    @NotNull(message = "账号不能为空")
    @Size(min = 6,max = 12,message = "长度必须在6-12之间")
    private String userName;
    @NotNull(message = "密码不能为空")
    @Size(min = 6,max = 12,message = "长度必须在6-12之间")
    private String password;
    @NotNull(message = "用户名不能为空")
    @Size(min = 1,max = 12,message = "长度必须在1-12之间")
    private String name;
    @NotNull(message = "出生日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "必须是一个过去的时间")
    private java.sql.Date birth;
    @NotNull(message = "身份证号码不能为空")
    @Size(min = 18,max = 18,message = "长度为18位数字")
    private String idCard;
    @NotNull(message = "住址不能为空")
    private String address;
    private String email;
    @NotNull(message = "手机号码不能为空")
    @Size(min = 11,max = 11,message = "长度为11")
    private String phone;
    private Date registrationTime;

    public Consumer() { }

    public Consumer(Integer consId, String userName, String password, String name,  java.sql.Date birth, String idCard, String address, String email, String phone, Date registrationTime) {
        this.consId = consId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.idCard = idCard;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.registrationTime = registrationTime;
    }

    public Integer getConsId() {
        return consId;
    }

    public void setConsId(Integer consId) {
        this.consId = consId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public java.sql.Date getBirth() {
        return birth;
    }

    public void setBirth(java.sql.Date birth) {
        this.birth = birth;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "consId=" + consId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", idCard=" + idCard +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", registrationTime=" + registrationTime +
                '}';
    }
}
