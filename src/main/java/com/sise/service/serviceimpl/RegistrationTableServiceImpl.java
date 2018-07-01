package com.sise.service.serviceimpl;

import com.sise.dao.EmployeeDao;
import com.sise.dao.RegistrationTableDao;
import com.sise.po.Employee;
import com.sise.po.MyAuth;
import com.sise.po.RegistrationTable;
import com.sise.po.SearchContent;
import com.sise.service.RegistrationTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:48 2018/2/5
 */
@Service
public class RegistrationTableServiceImpl implements RegistrationTableService {
    @Autowired
    private RegistrationTableDao registrationTableDao;

    @Autowired
    private EmployeeDao employeeDao;

    public List<RegistrationTable> searchAllRegistration() {
        return registrationTableDao.findAllRegistration();
    }

    public List<RegistrationTable> searchRegistrationByStatus(Integer status) {
        return registrationTableDao.findRegistrationByStatus(status);
    }

    public Integer saveRegistration(RegistrationTable registrationTable) {
        registrationTable.setBusinessStatus("未跟踪");
        registrationTable.setNotrackingDays(new Date(System.currentTimeMillis()));
        registrationTable.setTrackingTimes(0);
        registrationTable.setWarningTimes(0);
        registrationTable.setVersion(1);
        registrationTable = setStatus(registrationTable);
        try{
            if (registrationTable.getEmployeeId()==null){
                Integer employeeId = employeeDao.findOneNoWorkingOnRegistrationFirstStatus();
                System.out.println("登记客户没有业务的业务员id"+employeeId);
                if (employeeId==null){
                    employeeId = employeeDao.findMinOneOnRegistrationFirstStatus();
                    System.out.println("登记客户中业务最少的业务员id"+employeeId);
                }
                registrationTable.setEmployeeId(employeeId);

            }
        }catch (Exception e){
            registrationTable.setEmployeeId(null);
        }
        return registrationTableDao.insertRegistration(registrationTable);
    }

    public Integer setDesigner(Integer designerId, Integer registrationId,Integer version) {
        return registrationTableDao.updateDesignerByRegistrationId(designerId,registrationId,version);
    }

    public Integer seeVersion(Integer registrationId) {
        return registrationTableDao.getVersion(registrationId);
    }

    public RegistrationTable searchRegistrationById(Integer registrationId) {
        return registrationTableDao.findRegistrationById(registrationId);
    }

    public RegistrationTable searchRegistrationWithAppointmentById(Integer registrationId) {
        return registrationTableDao.findRegistrationWithAppointmentById(registrationId);
    }

    public Integer changInfo(RegistrationTable registrationTable) {
        return registrationTableDao.updateInfo(registrationTable);
    }

    public Integer changStatusByRegistrationId(Integer registrationId, Integer status) {
        return registrationTableDao.updateStatus(registrationId,status);
    }

    public List<RegistrationTable> searchRegistrationWithAppointmentByStatus(Integer status) {
        return registrationTableDao.findRegistrationWithAppointmentByStatus(status);
    }

    public Integer changAppointmentIdById(Integer registrationId, Integer appointmentId) {
        return registrationTableDao.updateAppointmentId(registrationId,appointmentId);
    }

    public Integer searchAppointmentIdById(Integer registrationId) {
        return registrationTableDao.findAppointmentIdById(registrationId);
    }

    public List<RegistrationTable> searchRegistrationWithSearchContentByStatus(Integer status, SearchContent searchContent) {
        searchContent.setStatus(status);
        Employee employee = searchContent.getEmployee();
        if (findAuth(employee.getJob().getMyAuths(),41)){//搜索全部数据
            searchContent.setEmployeeId(0);
        }else {//搜索本人相关联数据
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        return registrationTableDao.findRegistrationByStatusAndSearchContent(searchContent);
    }

    public List<RegistrationTable> searchRegistrationWithAppByStatusSearchContent(Integer status, SearchContent searchContent) {
        searchContent.setStatus(status);
        Employee employee = searchContent.getEmployee();
        if (findAuth(employee.getJob().getMyAuths(),42)){
            searchContent.setEmployeeId(0);
            /*searchContent.setDesignerId(0);*/
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        /*else if (employee.getJob().getJobId()==10001){
            searchContent.setEmployeeId(employee.getEmployeeId());
            searchContent.setDesignerId(0);
        }else if(employee.getJob().getJobId()==10002){
            searchContent.setEmployeeId(0);
            searchContent.setDesignerId(employee.getEmployeeId());
        }*/
        return registrationTableDao.findRegistrationWithAppByStatusAndSearchContent(searchContent);
    }

    public List<RegistrationTable> searchRegistrationWithProjectStatusByStatusAndSearchContent(Integer status, SearchContent searchContent) {
        searchContent.setStatus(status);
        Employee employee = searchContent.getEmployee();
        if (findAuth(employee.getJob().getMyAuths(),43)){
            searchContent.setEmployeeId(0);
            /*searchContent.setDesignerId(0);*/
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        return registrationTableDao.findRegistrationWithProjectStatusByStatusAndSearchContent(searchContent);
    }

    public Integer searchRegistrationIdByUserName(String userName) {
        return registrationTableDao.findRegistrationIdByUserName(userName);
    }

    public List<RegistrationTable> searchRegistrationByUserName(String userName) {
        return registrationTableDao.findRegistrationByUserName(userName);
    }

    public boolean compareHousepicAndConusmer(String userName,Integer housepicId) {
        String userName1 = registrationTableDao.finduserNameByHousepicId(housepicId);
        if (userName1.equals(userName)){
            return true;
        }else {
            return false;

        }
    }

    public boolean compareDesignerpicAndConusmer(String userName,Integer designerpicId) {
        String userName1 = registrationTableDao.finduserNameByDesignerpicId(designerpicId);
        if (userName1.equals(userName)){
            return true;
        }else {
            return false;

        }
    }
    public boolean compareContractAndConusmer(String userName,Integer contractId) {
        String userName1 = registrationTableDao.finduserNameByContractId(contractId);
        if (userName1.equals(userName)){
            return true;
        }else {
            return false;

        }
    }

    public List<RegistrationTable> searchRegistrationwithContractByStatusAndSearchContent(Integer status, SearchContent searchContent) {
        searchContent.setStatus(status);
        Employee employee = searchContent.getEmployee();
        if (findAuth(employee.getJob().getMyAuths(),44)){
            searchContent.setEmployeeId(0);
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        return registrationTableDao.findRegistrationwithContractByStatusAndSearchContent(searchContent);
    }

    public List<RegistrationTable> searchRegistrationwithGuaranteeByStatusAndSearchContent(Integer status, SearchContent searchContent) {
        searchContent.setStatus(status);
        Employee employee = searchContent.getEmployee();
        if (findAuth(employee.getJob().getMyAuths(),45)){
            searchContent.setEmployeeId(0);
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        return registrationTableDao.findRegistrationwithGuaranteeByStatusAndSearchContent(searchContent);
    }

    public Integer dropRegistrationTableById(Integer registrationId) {
        return registrationTableDao.delRegistrationTableById(registrationId);
    }

    public Integer changeUserNameByRegistrationId(Map<String, Object> map) {
        return registrationTableDao.updateUserNameByRegistrationId(map);
    }

    public String searchUserNameByRegistrationId(Integer registrationId) {
        return registrationTableDao.findUserNameByRegistrationId(registrationId);
    }

    public RegistrationTable setStatus(RegistrationTable registrationTable ){
        try{
            if(registrationTable.getDesignerId()==null || registrationTable.getDesignerId().equals("") ){
                registrationTable.setStatus(1);
                System.out.println("设置用户为登记客户");
            }else{
                registrationTable.setStatus(2);
                System.out.println("设置用户为意向客户");
            }
        }catch (NullPointerException e){
            registrationTable.setStatus(1);
        }
        return registrationTable;
    }

    public boolean findAuth(List<MyAuth> myAuths,Integer authNum){
        boolean status = false;
        for (MyAuth m:myAuths){
            System.out.println(m);
            if (m.getAuthId()==authNum){
                status = true;
                return status;
            }
        }
        System.out.println(status);
        return status;
    }

}
