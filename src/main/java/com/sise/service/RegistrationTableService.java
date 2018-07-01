package com.sise.service;

import com.sise.po.RegistrationTable;
import com.sise.po.SearchContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:47 2018/2/5
 */
public interface RegistrationTableService {

    public List<RegistrationTable> searchAllRegistration();

    public List<RegistrationTable> searchRegistrationByStatus(Integer status);

    public Integer saveRegistration(RegistrationTable registrationTable);

    public Integer setDesigner(Integer designerId,Integer registrationId,Integer version);

    public Integer seeVersion(Integer registrationId);

    public RegistrationTable searchRegistrationById(Integer registrationId);

    public RegistrationTable searchRegistrationWithAppointmentById(Integer registrationId);

    public Integer changInfo(RegistrationTable registrationTable);

    public Integer changStatusByRegistrationId(Integer registrationId,Integer status);

    public List<RegistrationTable> searchRegistrationWithAppointmentByStatus(Integer status);

    public Integer changAppointmentIdById(Integer registrationId,Integer appointmentId);

    public Integer searchAppointmentIdById(Integer registrationId);

    public List<RegistrationTable> searchRegistrationWithSearchContentByStatus(Integer status, SearchContent searchContent);

    public List<RegistrationTable> searchRegistrationWithAppByStatusSearchContent(Integer status, SearchContent searchContent);


    public List<RegistrationTable> searchRegistrationWithProjectStatusByStatusAndSearchContent(Integer status, SearchContent searchContent);

    public Integer searchRegistrationIdByUserName(String userName);

    public List<RegistrationTable> searchRegistrationByUserName(String userName);

    public boolean compareHousepicAndConusmer(String userName,Integer housepicId);

    public boolean compareDesignerpicAndConusmer(String userName,Integer designerpicId);

    public boolean compareContractAndConusmer(String userName,Integer contractId);

    public List<RegistrationTable> searchRegistrationwithContractByStatusAndSearchContent(Integer status, SearchContent searchContent );

    public List<RegistrationTable> searchRegistrationwithGuaranteeByStatusAndSearchContent(Integer status,SearchContent searchContent);

    public Integer dropRegistrationTableById(Integer registrationId);

    /*更新registration的userName*/
    public Integer changeUserNameByRegistrationId(Map<String , Object> map);

    /*查找registration的userName*/
    public String searchUserNameByRegistrationId(Integer registrationId);
}
