package com.sise.po;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:55 2018/3/5
 */
public class Guarantee implements Serializable {
    private static final long serialVersionUID = 4254904626552824606L;
    private Integer guarantee_id;
    @NotNull(message = "时间不能为空！")
    private Date startTime;

    @NotNull(message = "年不能为空！")
    @Max(value = 5,message = "年不能大于5")
    @Min(value = 0,message = "年不能小于0")
    private Integer year;
    @NotNull(message = "月不能为空！")
    @Max(value = 12,message = "月不能大于12")
    @Min(value = 0,message = "月不能小于0")
    private Integer month;

    private Date endTime;

    private Integer registrationId;

    public Guarantee() {
    }

    public Integer getGuarantee_id() {
        return guarantee_id;
    }

    public void setGuarantee_id(Integer guarantee_id) {
        this.guarantee_id = guarantee_id;
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

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Guarantee{" +
                "guarantee_id=" + guarantee_id +
                ", startTime=" + startTime +
                ", year=" + year +
                ", month=" + month +
                ", endTime=" + endTime +
                ", registrationId=" + registrationId +
                '}';
    }
}
