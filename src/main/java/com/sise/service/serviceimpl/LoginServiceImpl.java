package com.sise.service.serviceimpl;

import com.sise.dao.LoginDao;
import com.sise.po.Consumer;
import com.sise.po.Employee;
import com.sise.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;
    public Consumer login(String username,String password) {
        return  loginDao.searchByConsumer(username,password);
    }

    public Employee loginEmployee(Integer employeeId, String password) {
        return loginDao.searchByEmployee(employeeId,password);
    }

    public String checkName(String name) {
        return loginDao.searchByName(name);
    }

    public String checkUserName(String userName) {
        return loginDao.searchByUserName(userName);
    }
    public Integer saveConsumer(Consumer consumer){
        Integer resultInt=loginDao.insertConsumer(consumer);
        return resultInt;
    }

}
