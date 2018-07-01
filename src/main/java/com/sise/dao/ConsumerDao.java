package com.sise.dao;

import com.sise.po.City;
import com.sise.po.Consumer;
import com.sise.po.Province;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Past;
import java.util.List;
@Repository
public interface ConsumerDao {
    /**
     * 查找所有省份
     * @param
     */
    public List<Province> findAllProvince();

    /**
     * 根据省份id查找城市
     * @param provinceId  省份id
     */
    public List<City> findCityByProvinceId(@Param("provinceId")Integer provinceId);
    /**
     * 根据账号查找客户信息
     * @param userName
     */
    public Consumer findConsumerByUserName(@Param("userName") String userName);

    public Integer updateConsumerPasswordByUserName(@Param("userName") String userName);

    public Consumer findConsumerByConsId(@Param("consId") Integer consId);


}
