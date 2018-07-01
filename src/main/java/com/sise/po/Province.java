package com.sise.po;

public class Province {
    private Integer provinceId;
    private String provinceName;
    private char initials;

    public char getInitials() {
        return initials;
    }

    public void setInitials(char initials) {
        this.initials = initials;
    }

    public Province() {
    }

    public Province(Integer provinceId, String provinceName, char initials) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.initials = initials;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
