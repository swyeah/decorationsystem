package com.sise.service.serviceimpl;

import com.sise.dao.DepositDao;
import com.sise.po.Deposit;
import com.sise.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:04 2018/2/18
 */
@Service
public class DepositServiceImpl implements DepositService {
    @Autowired
    private DepositDao depositDao;


    public Integer saveDeposit(Deposit deposit) {
        return depositDao.insertDeposit(deposit);
    }

    public Deposit searchDepositByRegistrationId(Integer registrationId) {
        return depositDao.findDepositByRegistrationId(registrationId);
    }

    public Integer dropDepositByRegistrationId(Integer registrationId) {
        return depositDao.delDepositByRegistrationId(registrationId);
    }

    public Integer changeDepositById(Deposit deposit) {
        return depositDao.updateDepositById(deposit);
    }

    public Date searchUploadTimeById(Integer depositId) {
        return depositDao.getUploadTimeById(depositId);
    }
}
