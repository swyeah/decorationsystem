package com.sise.po;

public class City {
    private Integer cityId;
    private String cityName;
    private Province province;

    public City(Integer cityId, String cityName, Province province) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.province = province;
    }

    public City() {
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
