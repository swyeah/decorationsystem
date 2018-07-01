package com.sise.dao;

import com.sise.po.HouseType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:46 2018/2/20
 */
@Repository
public interface HouseTypeDao {

    /*查找全部户型类型*/
    public List<HouseType> searchAllHouseType();

    public HouseType searchHouseTypeById(@Param("house_id") Integer houseId);

}
