package com.sise.dao;

import com.sise.po.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:06 2018/2/22
 */
@Repository
public interface DesignerpicDao {

    /*通过registrationId查找设计图*/
    public Designerpic findDesignerpicByRegistrationId(@Param("registrationId") Integer registrationId);

    /*插入设计图资料*/
    public Integer insertDesigner(Designerpic designerpic);

    /*插入设计图文件*/
    public Integer insertDesignerpicFile(DesignerpicFile designerpicFile);

    /*查找全部设计图文件*/
    public List<DesignerpicFile> findAllDesignerpicFileByDesignerId(@Param("designerpicId")Integer designerpicId);

    /*更新设计图文件描述*/
    public Integer updateDescribeById(Map<String,Object> map);

    /*删除设计图资料*/
    public Integer delDesignerpicByDesignerpicId(@Param("designerpicId")Integer designerpicId);

    /*删除设计图文件*/
    public Integer delDesignerpicFileByDesignerpicId(@Param("designerpicId")Integer designerpicId);

    /*更新设计图资料*/
    public Integer updateDesignerpicByDesignerpicId(Designerpic designerpic);

    /*获取版本号*/
    public Integer getVersionByDesignerpicId(@Param("designerpicId")Integer designerpicId);

    /*查找所有设计图*/
    public List<Designerpic> findAllDesignerpic(SearchDesignerpic searchDesignerpic);

    /*通过designerpicId更新状态*/
    public Integer updateStatusByDesignerpicId(@Param("designerpicId") Integer designerpicId,@Param("status") Integer status);

}
