package com.sise.service;

import com.sise.po.Deposit;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:03 2018/2/18
 */
public interface DepositService {

    public Integer saveDeposit(Deposit deposit);

    public Deposit searchDepositByRegistrationId(Integer registrationId);

    public Integer dropDepositByRegistrationId(Integer registrationId);

    public Integer changeDepositById(Deposit deposit);

    public Date searchUploadTimeById(Integer depositId);
}
