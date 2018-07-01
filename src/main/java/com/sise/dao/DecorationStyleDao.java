package com.sise.dao;

import com.sise.po.DecorationStyle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:07 2018/2/22
 */
@Repository
public interface DecorationStyleDao {

    /*查找所有设计风格*/
    public List<DecorationStyle> findAllDecorationStyle();

    /*通过id查找设计风格*/
    public DecorationStyle findDecorationStyleById(@Param("style_id") Integer styleId);
}
