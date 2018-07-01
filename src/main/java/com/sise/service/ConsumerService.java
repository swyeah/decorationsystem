package com.sise.service;

import com.sise.po.City;
import com.sise.po.Consumer;
import com.sise.po.Province;

import java.util.List;


public interface ConsumerService {

    //查找数据库所有的省份信息
    public List<Province> searchAllProvince();

    //通过id查找城市
    public List<City> searchCityByProvinceId(Integer provinceId);

    public boolean changePassword(String userName,String oldpassword,String newpassword);

    public Consumer searchConsumerByConsId(Integer consId);
}
