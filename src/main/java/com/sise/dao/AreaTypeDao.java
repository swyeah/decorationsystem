package com.sise.dao;

import com.sise.po.AreaType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:47 2018/2/20
 */
@Repository
public interface AreaTypeDao {

    /*查找全部户型类型*/
    public List<AreaType> searchAllAreaType();

    public AreaType searchAreaTypeById(@Param("area_id") Integer areaId);
}
