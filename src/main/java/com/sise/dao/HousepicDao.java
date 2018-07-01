package com.sise.dao;

import com.sise.po.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:21 2018/2/20
 */
@Repository
public interface HousepicDao {

    /*通过登记记录id查找户型图资料*/
    public Housepic searchHousepicByRegistrationId(@Param("registrationId") Integer registrationId);

    /*通过户型图id查找所有户型图图片*/
    public List<HousepicFile> searchHousepicFileByHousepicId(@Param("housepicId") Integer housepicId);

    /*插入户型图*/
    public Integer insertHousepic(Housepic housepic);

    /*通过户型图id查找状态*/
    public Integer searchStatusById(@Param("housepicId") Integer housepicId);


    /*通过户型图id删除户型图*/
    public Integer delHousepicById(@Param("housepicId") Integer housepicId);

    /*通过户型图id更新状态*/
    public Integer updateStatusById(@Param("housepicId") Integer housepicId,@Param("status") Integer status);

    /*插入户型图图片*/
    public Integer insertHousepicFile(HousepicFile housepicFile);

    /*获得版本号*/
    public Integer getVersionById(@Param("housepicId") Integer housepicId);

    /*查找所有户型图*/
    public List<Housepic> findAllHousepic(SearchHousepic searchHousepic);


    /*更新户型图*/
    public Integer updateHousepic(Housepic housepic);

    /*删除户型图文件*/
    public Integer delAllHousepicFileByHousepicId(@Param("housepicId") Integer housepicId);

}
