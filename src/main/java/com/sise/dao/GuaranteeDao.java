package com.sise.dao;

import com.sise.po.Guarantee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:57 2018/3/5
 */
@Repository
public interface GuaranteeDao {
    public Integer insertGuarantee(Guarantee guarantee);

    public Integer updateGuarantee(Guarantee guarantee);

    public Guarantee findGuaranteeByRegistrationId(@Param("registrationId") Integer registrationId);
}
