package com.sise.dao;

import com.sise.po.CancelTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:29 2018/2/14
 */
@Repository
public interface CancelTableDao {

    /*插入飞单审批申请*/
    public Integer insertCancelTable(CancelTable cancelTable);

    /*查找飞单内容*/
    public CancelTable findCancelTableByRegistrationId(@Param("registrationId") Integer registrationId);

    /*删除飞单内容*/
    public Integer delCancelTableByRegistrationId(@Param("registrationId") Integer registrationId);

}
