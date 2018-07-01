package com.sise.service;

import com.sise.po.Consumer;
import com.sise.po.Employee;

public interface LoginService {

    public Consumer login(String username,String password);
    public Employee loginEmployee(Integer employeeId,String password);
    public String checkName(String name);
    public String checkUserName(String userName);
    public Integer saveConsumer(Consumer consumer);
}
