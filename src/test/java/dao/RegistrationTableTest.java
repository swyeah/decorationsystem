package dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.dao.RegistrationTableDao;
import com.sise.po.City;
import com.sise.po.Employee;
import com.sise.po.Job;
import com.sise.po.RegistrationTable;
import com.sise.service.RegistrationTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:28 2018/2/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class RegistrationTableTest {
    @Autowired
    private RegistrationTableDao registrationTableDao;
    @Autowired
    private RegistrationTableService registrationTableService;

    @Test
    public void findAllRegistration(){
        PageHelper.startPage(1, 2);
        List<RegistrationTable> registrationTables = registrationTableDao.findAllRegistration();
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        for (RegistrationTable rt:registrationTables){
            System.out.println(rt.getRegistrationId()+"  "+rt.getRegistrationName()+"  "+rt.getRegistrationTime());
            System.out.println(rt.getRegistrationAddress()+"  "+rt.getRegistrationPhone()+"  "+rt.getRegistrationSource());
            System.out.println(rt.getStatus()+"  "+rt.getCity().getCityName()+rt.getCity().getProvince().getProvinceName()+"  "+rt.getUserName());
            System.out.println("计算日期与当前日期相差天数"+calculateDate(rt.getNotrackingDays()));
            if (rt.getEmployee()==null){
                System.out.println("无市场人员");
            }else{
                Employee employee = rt.getEmployee();
                System.out.println(employee.toString());
                Job job=employee.getJob();
                System.out.println(job.getDepartment().getDepartmentName()+"   "+job.getJobName());
            }

            if (rt.getDesigner()==null){
                System.out.println("无设计师");
            }else{
                Employee employee = rt.getDesigner();
                System.out.println(employee.toString());
                Job job=employee.getJob();
                System.out.println(job.getDepartment().getDepartmentName()+"   "+job.getJobName());
            }

        }



    }

    @Test
    public void testPageInfo(){
        PageHelper.startPage(1, 2);
        List<RegistrationTable> registrationTables =registrationTableService.searchAllRegistration();
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        System.out.println(pageInfo.getList());
        for (RegistrationTable r:registrationTables){
            System.out.println(r.getRegistrationId());
        }
        System.out.println(pageInfo.getStartRow());
        System.out.println(pageInfo.getNextPage());

    }
    public Integer calculateDate(Date date){
        long from = date.getTime();
        long to = new Date(System.currentTimeMillis()).getTime();
        Integer days = (int) ((to - from)/(1000 * 60 * 60 * 24));
        return days;
    }

    @Test
    public void insertRegistration(){
        City city = new City();
        city.setCityId(1);
        Employee employee = new Employee();
        employee.setEmployeeId(10000);
        for (int i=20;i<40;i++){
            RegistrationTable registrationTable = new RegistrationTable(new Date(System.currentTimeMillis()), "数据"+i, "12345678912", "80", "测试数据"+i,city, "测试"+i,employee, 1, "", 1,employee, "未跟踪", new Date(System.currentTimeMillis()), 0,0);
            registrationTable.setCityId(7);
            registrationTable.setEmployeeId(10104);
            Integer result = registrationTableDao.insertRegistration(registrationTable);
            System.out.println("插入结果更新行数"+result);
        }
    }
    @Test
    public void setDesignerTest(){
        Integer result = registrationTableService.setDesigner(10039,100008,2);
        System.out.println("更新行数"+result);
    }
    @Test
    public void seeVersionTest(){
        Integer version = registrationTableService.seeVersion(100008);
        System.out.println(version);
    }
    @Test
    public void Test(){
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationWithAppointmentByStatus(1);
        for (RegistrationTable r:registrationTables){
            System.out.println(r.toString());
            if (r.getAppointment()!=null)
                System.out.println(r.getAppointment().toString());
        }
    }

    @Test
    public void compareHousepicAndConusmerTest(){
        boolean i = registrationTableService.compareHousepicAndConusmer("1111111",55);
        System.out.println(i);
    }
    @Test
    public void saveRegistrationTest(){
        Employee employee = new Employee();
        for (int i=200;i<700;i++){
            RegistrationTable registrationTable = new RegistrationTable();
            registrationTable.setRegistrationTime(new Date(System.currentTimeMillis()));
            registrationTable.setRegistrationName("测试"+i);
            registrationTable.setRegistrationPhone("12345678912");
            registrationTable.setRegistrationArea("100");
            registrationTable.setCityId(8);
            registrationTable.setRegistrationAddress("测试自动插入市场人员数据"+i);
            registrationTable.setRegistrationSource("公司活动");
            registrationTable.setUserName("");
            Integer result = registrationTableService.saveRegistration(registrationTable);
            System.out.println("插入结果更新行数"+result);
        }
    }
}

