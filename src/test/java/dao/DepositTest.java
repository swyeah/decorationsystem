package dao;

import com.sise.po.Deposit;
import com.sise.service.DepositService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:39 2018/2/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class DepositTest {
    @Autowired
    private DepositService depositService;

    @Test
    public void searchDepositByRegistrationIdTest(){
        Deposit deposit = depositService.searchDepositByRegistrationId(100001);
        System.out.println(deposit.toString());
        File file1 = new File(deposit.getDepositUrl());
        System.out.println(file1.getName());
        System.out.println(file1.getName());
        System.out.println(file1.getName());
        System.out.println(file1.getName());
        System.out.println(file1.getName());
        System.out.println(file1.getName());
        File file2 = new File("D:\\ProjectUpload\\deposit"+File.separator+file1.getName());
        file2.delete();
    }
    @Test
    public  void dropDepositByRegistrationIdTest(){
        Integer column = depositService.dropDepositByRegistrationId(100009);
        System.out.println(column);
    }

    @Test
    public void compareTime(){
        String  beginTime ="2018-01-01 23:23:23";
        String endTime = "2018-01-01 23:23:22";
        Integer i=beginTime.compareTo(endTime);
        System.out.println(i);
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String time = simpleDateFormat.format(new Date());
        i=beginTime.compareTo(time);
        System.out.println(i);
        System.out.println(time);
    }

    @Test
    public void searchUploadTimeByIdTest(){
        Date date = depositService.searchUploadTimeById(2);
        System.out.println(date);
    }

    @Test
    public void Test(){
        Date date = new Date();
        Date date1 = depositService.searchUploadTimeById(2);

        System.out.println(date.getTime()<=date1.getTime());
    }

}
