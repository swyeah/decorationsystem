package com.sise.dao;

import com.sise.po.Consumer;
import com.sise.po.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {

    public Consumer searchByConsumer(@Param("userName") String userName,@Param("password") String password);
    public Employee searchByEmployee(@Param("employeeId") Integer employeeId,@Param("password") String password);
    public String searchByName(@Param("name") String name);
    public String searchByUserName(@Param("userName") String userName);
    public Integer insertConsumer(Consumer consumer);

}
