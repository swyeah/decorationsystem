package com.sise.service;

import com.sise.po.Guarantee;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 0:15 2018/3/6
 */
public interface GuaranteeService {

    public Integer saveGuarantee(Guarantee guarantee );

    public Integer changeGuarantee(Guarantee guarantee);

    public Guarantee searchGuaranteeByRegistrationId(Integer registrationId);
}
