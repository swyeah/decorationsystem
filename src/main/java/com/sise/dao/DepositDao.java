package com.sise.dao;

import com.sise.po.Deposit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 14:57 2018/2/18
 */
@Repository
public interface DepositDao {

    /*插入协议*/
    public Integer insertDeposit(Deposit deposit);

    /*通过登记记录id查找协议*/
    public Deposit findDepositByRegistrationId(@Param("registrationId") Integer registrationId);

    /*删除协议*/
    public Integer delDepositByRegistrationId(@Param("registrationId") Integer registrationId);

    /*通过协议id更新协议*/
    public Integer updateDepositById(Deposit deposit);

    /*通过id得到上传时间*/
    public Date getUploadTimeById(Integer depositId);
}
