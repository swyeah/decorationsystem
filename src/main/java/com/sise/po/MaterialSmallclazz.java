package com.sise.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:58 2018/2/24
 */
public class MaterialSmallclazz implements Serializable {

    private static final long serialVersionUID = 542048289058568777L;
    private Integer smallId;
    @NotNull(message = "材料小分类名字不能为空")
    @Size(max = 10,min = 1,message = "材料小分类名字长度必须在1-10之间")
    private String smallName;
    @Size(max = 100,min = 1,message = "材料小分类描述长度必须在1-100之间")
    private String smallDescribe;

    private MaterialClazz materialClazz;
    @NotNull(message = "材料大分类不能为空")
    private Integer bigId;

    public MaterialSmallclazz() {
    }

    public Integer getSmallId() {
        return smallId;
    }

    public void setSmallId(Integer smallId) {
        this.smallId = smallId;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getSmallDescribe() {
        return smallDescribe;
    }

    public void setSmallDescribe(String smallDescribe) {
        this.smallDescribe = smallDescribe;
    }

    public MaterialClazz getMaterialClazz() {
        return materialClazz;
    }

    public void setMaterialClazz(MaterialClazz materialClazz) {
        this.materialClazz = materialClazz;
    }


    public Integer getBigId() {
        return bigId;
    }

    public void setBigId(Integer bigId) {
        this.bigId = bigId;
    }

    @Override
    public String toString() {
        return "MaterialSmallclazz{" +
                "smallId=" + smallId +
                ", smallName='" + smallName + '\'' +
                ", smallDescribe='" + smallDescribe + '\'' +
                ", materialClazz=" + materialClazz +
                '}';
    }
}
