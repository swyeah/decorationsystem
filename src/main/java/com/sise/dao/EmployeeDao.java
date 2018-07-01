package com.sise.dao;

import com.sise.po.Consumer;
import com.sise.po.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:14 2018/2/7
 */
@Repository
public interface EmployeeDao {
    //查找全部设计师
    public List<Employee> findAllDesigner(@Param("deptId") Integer deptId);

    //通过搜索内容模糊查询
    public List<Employee> searchEmployBySearch(@Param("search") String search,@Param("deptId") Integer deptId);

    //插入员工数据
    public Integer insertEmployee(Employee employee);

    //根据员工id查找员工
    public Employee findEmployeeById(@Param("employee_id") Integer employeeId);

    //根据employeeId查找密码
    public String findEmployeepasswordByEmployeeId(@Param("employeeId") Integer employeeId);

    //根据employeeId修改密码
    public Integer updateEmployeepasswordByEmployeeId(Map<String ,Object> map);

    /*查找登记客户中没有业务的业务员中的一个的employeeId*/
    public Integer findOneNoWorkingOnRegistrationFirstStatus();

    /*查找登记客户中有业务和本身业务数量最小的一个业务员的id*/
    public Integer findMinOneOnRegistrationFirstStatus();

}
