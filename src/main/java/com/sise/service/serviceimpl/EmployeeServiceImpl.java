package com.sise.service.serviceimpl;

import com.sise.dao.EmployeeDao;
import com.sise.po.Employee;
import com.sise.service.EmployeeService;
import com.sise.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:29 2018/2/7
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;
    public List<Employee> searchAllDesigner() {
        return employeeDao.findAllDesigner(1001);
    }

    public List<Employee> searchEmployBySearch(String search) {
        return employeeDao.searchEmployBySearch(search,1001);
    }

    public List<Employee> searchAllMarketer() {
        return employeeDao.findAllDesigner(1000);
    }

    public List<Employee> searchMarketerBySearch(String search) {
        return employeeDao.searchEmployBySearch(search,1000);
    }

    public Employee searchEmployeeById(Integer employeeId) {
        return employeeDao.findEmployeeById(employeeId);
    }

    public boolean changeEmployeepasswordByEmployeeId(Integer employeeId, String password, String oldpassword) {
        String nowpassword = employeeDao.findEmployeepasswordByEmployeeId(employeeId);
        if (MD5Utils.generatePassword(oldpassword).equals(nowpassword)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("employeeId",employeeId);
            map.put("password",MD5Utils.generatePassword(password));
            Integer column = employeeDao.updateEmployeepasswordByEmployeeId(map);
            if (column==1)
                return true;
        }
        return false;
    }
}
