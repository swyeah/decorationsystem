package com.sise.service;

import com.sise.po.CancelTable;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:56 2018/2/14
 */
public interface CancelTableService {

    public Integer saveCancelTable(CancelTable cancelTable);

    /*查找飞单内容*/
    public CancelTable searchCancelTableByRegistrationId(Integer registrationId);

    /*删除飞单内容*/
    public Integer dropCancelTableByRegistrationId(Integer registrationId);
}
