package com.sise.service.serviceimpl;

import com.sise.dao.CancelTableDao;
import com.sise.po.CancelTable;
import com.sise.service.CancelTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:57 2018/2/14
 */
@Service
public class CancelTableServiceImpl implements CancelTableService {
    @Autowired
    private CancelTableDao cancelTableDao;


    public Integer saveCancelTable(CancelTable cancelTable) {
        return cancelTableDao.insertCancelTable(cancelTable);
    }

    public CancelTable searchCancelTableByRegistrationId(Integer registrationId) {
        return cancelTableDao.findCancelTableByRegistrationId(registrationId);
    }

    public Integer dropCancelTableByRegistrationId(Integer registrationId) {
        return cancelTableDao.delCancelTableByRegistrationId(registrationId);
    }
}
