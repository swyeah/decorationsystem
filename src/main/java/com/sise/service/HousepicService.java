package com.sise.service;

import com.sise.po.Housepic;
import com.sise.po.HousepicFile;
import com.sise.po.SearchHousepic;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:38 2018/2/20
 */
public interface HousepicService {

    /*通过登记记录id查找户型图资料*/
    public Housepic findHousepicByRegistrationId(Integer registrationId);

    /*通过户型图id查找所有户型图图片*/
    public List<HousepicFile> findHousepicFileByHousepicId(Integer housepicId);

    /*插入户型图*/
    public Integer addHousepic(Housepic housepic);

    /*通过户型图id查找状态*/
    public Integer findStatusById(Integer housepicId);

    /*通过户型图id删除户型图*/
    public Integer dripHousepicById(Integer housepicId);

    /*通过户型图id更新状态*/
    public Integer changeStatusById(Integer housepicId,Integer status);

    /*储存户型图文件*/
    public Integer saveHousepicFile(List<HousepicFile> housepicFiles);

    /*通过id得到版本号*/
    public Integer searchVersionById(Integer housepicId);

    public Integer changeHousepic(Housepic housepic);

    public Integer dropAllHousepicFileByHouseId(Integer housepicId);

    public List<Housepic> searchAllHousepic(SearchHousepic searchHousepic);
}
