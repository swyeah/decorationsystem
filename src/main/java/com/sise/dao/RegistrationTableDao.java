package com.sise.dao;

import com.sise.po.RegistrationTable;
import com.sise.po.SearchContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:02 2018/2/4
 */
@Repository
public interface RegistrationTableDao {

    /*查询所有登记记录*/
    public List<RegistrationTable> findAllRegistration();

    /*根据状态查询登记记录*/
    public List<RegistrationTable> findRegistrationByStatus(@Param("status") Integer status);

    public List<RegistrationTable> findRegistrationWithAppointmentByStatus(@Param("status") Integer status);
    /*插入登记数据*/
    public Integer insertRegistration(RegistrationTable registrationTable);

    /*更新设计师,更新版本号*/
    public Integer updateDesignerByRegistrationId(@Param("designerId") Integer designerId,@Param("registrationId") Integer registrationId,@Param("version") Integer version);

    /*获取版本号*/
    public Integer getVersion(@Param("registrationId") Integer registrationId);

    /*根据id获取登记记录*/
    public RegistrationTable findRegistrationById(@Param("registrationId") Integer registrationId );

    /*根据id获取登记记录和预约时间*/
    public RegistrationTable findRegistrationWithAppointmentById(@Param("registrationId") Integer registrationId );

    /*跟新跟踪信息*/
    public Integer updateInfo(RegistrationTable registrationTable);

    /*更新登记状态*/
    public Integer updateStatus(@Param("registrationId")Integer registrationId,@Param("status") Integer status);

    /*改变预约id*/
    public Integer updateAppointmentId(@Param("registrationId")Integer registrationId,@Param("appointmentId") Integer appointmentId);

    /*查找预约id*/
    public Integer findAppointmentIdById(@Param("registrationId") Integer registrationId);

    /*登记客户联合搜索*/
    public List<RegistrationTable> findRegistrationByStatusAndSearchContent(SearchContent searchContent);
    /*意向客户联合搜索*/
    public List<RegistrationTable> findRegistrationWithAppByStatusAndSearchContent(SearchContent searchContent);

    /*协议客户联合搜索*/
    public List<RegistrationTable> findRegistrationWithProjectStatusByStatusAndSearchContent(SearchContent searchContent);

    /*通过客户userName查找登记id*/
    public Integer findRegistrationIdByUserName(@Param("userName") String userName);

    /*通过客户username查找登记*/
    public List<RegistrationTable> findRegistrationByUserName(@Param("userName") String userName);

    /*通过housepicid查找登记记录的userName*/
    public String finduserNameByHousepicId(@Param("housepicId") Integer housepicId);

    /*通过designerpicId查找登记记录的userName*/
    public String finduserNameByDesignerpicId(@Param("designerpicId") Integer designerpicId);

    /*通过contractId查找登记记录的userName*/
    public String finduserNameByContractId(@Param("contractId") Integer contractId);

    /*合同客户联合搜索*/
    public List<RegistrationTable> findRegistrationwithContractByStatusAndSearchContent(SearchContent searchContent);

    /*完工客户联合搜索*/
    public List<RegistrationTable> findRegistrationwithGuaranteeByStatusAndSearchContent(SearchContent searchContent);

    /*删除登记记录*/
    public Integer delRegistrationTableById(@Param("registrationId") Integer registrationId);

    /*更新registration的userName*/
    public Integer updateUserNameByRegistrationId(Map<String , Object> map);

    /*查找registration的userName*/
    public String findUserNameByRegistrationId(@Param("registrationId") Integer registrationId);

}
