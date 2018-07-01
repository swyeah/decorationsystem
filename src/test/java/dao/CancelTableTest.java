package dao;

import com.sise.po.CancelTable;
import com.sise.service.CancelTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:55 2018/2/14
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class CancelTableTest {
    @Autowired
    private CancelTableService cancelTableService;

    @Test
    public void Test(){
        CancelTable cancelTable = new CancelTable("电话是空号",new Date(),10000,100001,new Date(),false);
        Integer column = cancelTableService.saveCancelTable(cancelTable);
    }
}
