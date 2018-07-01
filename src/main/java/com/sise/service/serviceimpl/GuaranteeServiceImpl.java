package com.sise.service.serviceimpl;

import com.sise.dao.GuaranteeDao;
import com.sise.po.Guarantee;
import com.sise.service.GuaranteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 0:17 2018/3/6
 */
@Service
public class GuaranteeServiceImpl implements GuaranteeService {
    @Autowired
    private GuaranteeDao guaranteeDao;
    public Integer saveGuarantee(Guarantee guarantee) {
        guarantee.setEndTime(setguarantee(guarantee.getStartTime(),guarantee.getYear(),guarantee.getMonth()));
        return guaranteeDao.insertGuarantee(guarantee);
    }

    public Integer changeGuarantee(Guarantee guarantee) {
        guarantee.setEndTime(setguarantee(guarantee.getStartTime(),guarantee.getYear(),guarantee.getMonth()));
        return guaranteeDao.updateGuarantee(guarantee);
    }

    public Guarantee searchGuaranteeByRegistrationId(Integer registrationId) {
        return guaranteeDao.findGuaranteeByRegistrationId(registrationId);
    }

    //Date是java.sql.Date类型
    protected java.sql.Date setguarantee(java.sql.Date holdDate,Integer y,Integer m) {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(holdDate);
        calendar.add(calendar.YEAR, y);
        calendar.add(calendar.MONTH,m);
        // calendar的time转成java.util.Date格式日期
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date)calendar.getTime();
        //java.util.Date日期转换成转成java.sql.Date格式
        java.sql.Date newDate =new java.sql.Date(utilDate.getTime());
        return newDate;
    }
}
