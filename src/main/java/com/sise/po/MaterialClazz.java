package com.sise.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:57 2018/2/24
 */
public class MaterialClazz implements Serializable{
    private static final long serialVersionUID = 2051261398047375124L;
    private Integer BigId;
    @NotNull(message = "材料大分类名字不能为空")
    @Size(max = 10,min = 1,message = "材料大分类名字长度必须在1-10之间")
    private String BigName;

    public MaterialClazz() {
    }

    public Integer getBigId() {
        return BigId;
    }

    public void setBigId(Integer bigId) {
        BigId = bigId;
    }

    public String getBigName() {
        return BigName;
    }

    public void setBigName(String bigName) {
        BigName = bigName;
    }

    @Override
    public String toString() {
        return "MaterialClazz{" +
                "BigId=" + BigId +
                ", BigName='" + BigName + '\'' +
                '}';
    }
}
