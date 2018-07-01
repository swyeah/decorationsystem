package dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sise.dao.ConsumerDao;
import com.sise.dao.EmployeeDao;
import com.sise.po.*;
import com.sise.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class EmployeeTest {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findAllDesignerTest(){
        //测试findAllProvince方法
        List<Employee> employees = employeeService.searchAllDesigner();
        for (Employee e:employees){
            System.out.println(e.getEmployeeId()+"   "+e.getEmployeeName()+"   "+e.getJob().getDepartment().getDepartmentName()+"    "+e.getJob().getJobName());
        }
        Gson gson = new Gson();
        String result = gson.toJson(employees);
        System.out.println(result);

    }

    @Test
    public void searchEmployBySearchTest(){
        PageHelper.startPage(1, 2);
        List<Employee> employees = new ArrayList<Employee>();
        employees= employeeService.searchEmployBySearch("1");
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
        for (Employee e:employees){
            System.out.println(e.getEmployeeId()+"   "+e.getEmployeeName()+"   "+e.getJob().getDepartment().getDepartmentName()+"    "+e.getJob().getJobName());
        }
    }

    @Test
    public void insertEmployeeTest(){
        Employee employee=new Employee();
        for (int i=0;i<=20;i++){
            employee.setEmployeeName("业务员"+i);
            employee.setEmployeeBirth(new Date(System.currentTimeMillis()));
            Job job = new Job();
            job.setJobId(10001);
            employee.setJob(job);
            employee.setEmployeeIdCard("123456789123456789");
            employee.setJoinedDate(new Date(System.currentTimeMillis()));
            Integer result = employeeDao.insertEmployee(employee);
            System.out.println("更新行数为"+result);
        }

    }

    @Test
    public void Test(){
        System.out.println(employeeDao.findOneNoWorkingOnRegistrationFirstStatus());
        System.out.println(employeeDao.findMinOneOnRegistrationFirstStatus());
    }


}
