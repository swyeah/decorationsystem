package com.sise.service;

import com.sise.po.Employee;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:29 2018/2/7
 */
public interface EmployeeService {

    public List<Employee> searchAllDesigner();

    public List<Employee> searchEmployBySearch( String search);

    public List<Employee> searchAllMarketer();

    public List<Employee> searchMarketerBySearch( String search);

    public Employee searchEmployeeById(Integer employeeId);

    public boolean changeEmployeepasswordByEmployeeId(Integer employeeId,String password,String oldpassword);

}
