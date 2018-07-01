package com.sise.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:01 2018/2/28
 */
public class DesignerShow implements Serializable {
    private static final long serialVersionUID = -2490793976125249764L;

    private Integer showId;

    @NotNull(message = "标题不能为空！")
    @Size(min = 1,max = 100,message = "标题长度应在1-100之间！")
    private String showTitle;

    @NotNull(message = "内容不能为空！")
    private String showContent;

    private DecorationStyle decorationStyle;

    private HouseType houseType;

    private AreaType areaType;
    @NotNull(message = "请重新登录！")
    private Integer employeeId;

    private Date uploadTime;

    private Integer styleId;

    private Integer houseId;

    private Integer areaId;

    private String showTitlepic;

    private Integer collectCount;

    private Integer praiseCount;

    public DesignerShow() {
    }

    public String getShowTitlepic() {
        return showTitlepic;
    }

    public void setShowTitlepic(String showTitlepic) {
        this.showTitlepic = showTitlepic;
    }

    public DesignerShow(String showTitle, String showContent, DecorationStyle decorationStyle, HouseType houseType, AreaType areaType, Integer employeeId, Date uploadTime) {
        this.showTitle = showTitle;
        this.showContent = showContent;
        this.decorationStyle = decorationStyle;
        this.houseType = houseType;
        this.areaType = areaType;
        this.employeeId = employeeId;
        this.uploadTime = uploadTime;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }

    public DecorationStyle getDecorationStyle() {
        return decorationStyle;
    }

    public void setDecorationStyle(DecorationStyle decorationStyle) {
        this.decorationStyle = decorationStyle;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public AreaType getAreaType() {
        return areaType;
    }

    public void setAreaType(AreaType areaType) {
        this.areaType = areaType;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "DesignerShow{" +
                "showId=" + showId +
                ", showTitle='" + showTitle + '\'' +
                ", showContent='" + showContent + '\'' +
                ", decorationStyle=" + decorationStyle +
                ", houseType=" + houseType +
                ", areaType=" + areaType +
                ", employeeId=" + employeeId +
                ", uploadTime=" + uploadTime +
                ", showTitlepic='" + showTitlepic + '\'' +
                '}';
    }
}
