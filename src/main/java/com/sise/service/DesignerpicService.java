package com.sise.service;

import com.sise.po.Designerpic;
import com.sise.po.DesignerpicFile;
import com.sise.po.SearchDesignerpic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:21 2018/2/22
 */
public interface DesignerpicService {

    /*通过registrationId查找设计图*/
    public Designerpic searchDesignerpicByRegistrationId( Integer registrationId);

    /*插入设计图资料*/
    public Integer saveDesigner(Designerpic designerpic);

    /*插入设计图文件*/
    public Integer saveDesignerpicFile(List<DesignerpicFile> designerpicFiles);

    /*查找全部设计图文件*/
    public List<DesignerpicFile> searchAllDesignerpicFileByDesignerId(Integer designerpicId);

    /*更新设计图文件描述*/
    public Integer changeDescribeById(Map<String,Object> map);

    /*删除设计图资料*/
    public Integer dropDesignerpicByDesignerpicId(Integer designerpicId);

    /*删除设计图文件*/
    public Integer dropDesignerpicFileByDesignerpicId(Integer designerpicId);

    /*更新设计图资料*/
    public Integer changeDesignerpicByDesignerpicId(Designerpic designerpic);

    /*获取版本号*/
    public Integer searchVersionByDesignerpicId(Integer designerpicId);

    public List<Designerpic> searchAllDesignerpicBySearch(SearchDesignerpic searchDesignerpic);

    public Integer changeStatusByDesignerpicId(Integer designerpicId,Integer status);
}
