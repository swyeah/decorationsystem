package com.sise.dao;

import com.sise.po.DecorationMode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:59 2018/2/23
 */
@Repository
public interface DecorationModeDao {

    public List<DecorationMode> findAllDecorationMode();

    public DecorationMode findDecorationModeById(@Param("mode_id") Integer modeId);
}
