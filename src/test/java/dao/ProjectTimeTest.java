package dao;

import com.sise.dao.*;
import com.sise.po.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:29 2018/3/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class ProjectTimeTest {
    @Autowired
    private ProjectTimeDao projectTimeDao;

    @Test
    public void insertProjectTime(){
        for (Integer i=0;i<=30;i++){
            ProjectTime projectTime = new ProjectTime();
            projectTime.setProjectDay(getPreDoneScore(new java.sql.Date(System.currentTimeMillis()),i));
            projectTime.setRegistrationId(100076);
            Integer column = projectTimeDao.insertProjectTime(projectTime);
            System.out.println(projectTime.getProjectId());
        }
    }

    //Date是java.sql.Date类型
    protected java.sql.Date getPreDoneScore(java.sql.Date holdDate,Integer i) {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(holdDate);
        calendar.add(calendar.DATE, i);
// calendar的time转成java.util.Date格式日期
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date)calendar.getTime();
//java.util.Date日期转换成转成java.sql.Date格式
        java.sql.Date newDate =new java.sql.Date(utilDate.getTime());
        return newDate;
    }
    /*删除一个天数*/
    @Test
    public void delProjectTime(){
        projectTimeDao.delProjectTime(1);

    }

    /*更新一个天数*/
    @Test
    public void updateProjectTime(){
        ProjectTime projectTime = new ProjectTime();
        projectTime.setProjectDay(new java.sql.Date(System.currentTimeMillis()));
        projectTime.setRegistrationId(100075);
        projectTime.setProjectId(2);
        Integer column = projectTimeDao.updateProjectTime(projectTime);
    }

    /*插入一个天数内容*/
    @Test
    public void insertProgressContent(){
        for (int i=0;i<10;i++){
            ProgressContent progressContent = new ProgressContent();
            Employee employee = new Employee();
            employee.setEmployeeId(10001);
            progressContent.setEmployee(employee);
            progressContent.setProgressContent("测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据");
            progressContent.setProgressTitle("水电改造");
            progressContent.setProjectId(2);
            projectTimeDao.insertProgressContent(progressContent);
        }

    }

    /*删除一个天数内容*/
    @Test
    public void delProgressContentByProgressContentId(){
        projectTimeDao.delProgressContentByProgressContentId(1);

    }

    /*删除一个天数的全部内容*/
    @Test
    public void delProgressContentByProjectId(){
        projectTimeDao.delProgressContentByProjectId(3);

    }

    /*修改一个天数的内容*/
    @Test
    public void updateProgressContent(){
        ProgressContent progressContent = new ProgressContent();
        Employee employee = new Employee();
        employee.setEmployeeId(10002);
        progressContent.setEmployee(employee);
        progressContent.setProgressContent("更新测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据");
        progressContent.setProgressTitle("土木改造");
        progressContent.setProjectId(2);
        progressContent.setProgressId(2);
        projectTimeDao.updateProgressContent(progressContent);

    }

    /*查找一个天数的全部内容*/
    @Test
    public void findProgressContentByProjectId(){
        List<ProgressContent> progressContents = projectTimeDao.findProgressContentByProjectId(2);
        for (ProgressContent p:progressContents){
            System.out.println(p);
        }

    }


    @Test
    public void test(){
        java.sql.Date date = setguarantee(new java.sql.Date(System.currentTimeMillis()),1,6);
        System.out.println(date.toString());
        date = setguarantee(new java.sql.Date(System.currentTimeMillis()),1,0);
        System.out.println(date.toString());
        date = setguarantee(new java.sql.Date(System.currentTimeMillis()),0,6);
        System.out.println(date.toString());
        date = setguarantee(new java.sql.Date(System.currentTimeMillis()),3,6);
        System.out.println(date.toString());
    }

    //Date是java.sql.Date类型
    protected java.sql.Date setguarantee(java.sql.Date holdDate,Integer y,Integer m) {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(holdDate);
        calendar.add(calendar.YEAR, y);
        calendar.add(calendar.MONTH,m);
        // calendar的time转成java.util.Date格式日期
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date)calendar.getTime();
        //java.util.Date日期转换成转成java.sql.Date格式
        java.sql.Date newDate =new java.sql.Date(utilDate.getTime());
        return newDate;
    }




}
