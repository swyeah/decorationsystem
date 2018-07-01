package com.sise.service.serviceimpl;

import com.sise.dao.ConsumerDao;
import com.sise.po.City;
import com.sise.po.Consumer;
import com.sise.po.Province;
import com.sise.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerDao consumerDao;

    public List<Province> searchAllProvince() {
        return consumerDao.findAllProvince();
    }

    public List<City> searchCityByProvinceId(Integer provinceId) {
        return consumerDao.findCityByProvinceId(provinceId);
    }

    public boolean changePassword(String userName, String oldpassword, String newpassword) {
        Consumer consumer = consumerDao.findConsumerByUserName(userName);
        return false;
    }

    public Consumer searchConsumerByConsId(Integer consId) {
        return consumerDao.findConsumerByConsId(consId);
    }

    public Consumer getConsumerByUserName(String userName){
        return consumerDao.findConsumerByUserName(userName);
    }
}
