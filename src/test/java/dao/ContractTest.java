package dao;

import com.sise.dao.*;
import com.sise.po.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:35 2018/2/23
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class ContractTest {
    @Autowired
    private ContractDao contractDao;

    @Autowired
    private DecorationModeDao decorationModeDao;

    @Test
    public void insertContractTest(){
        DecorationMode decorationMode = new DecorationMode(1);
        Contract contract = new Contract(new BigDecimal(23),"测试名字","测试描述",new java.sql.Date(System.currentTimeMillis()),new java.sql.Date(System.currentTimeMillis()),
                100002,new Date(),decorationMode);
        Integer column = contractDao.insertContract(contract);
        System.out.println(column);

    }

    @Test
    public void searchContractByRegistrationIdTest(){
        Contract contract = contractDao.searchContractByRegistrationId(100002);
        System.out.println(contract.toString());
        System.out.println(contract.getDecorationMode().toString());
    }

    @Test
    public void findDecorationModeByIdTest(){
        DecorationMode decorationMode = decorationModeDao.findDecorationModeById(1);
        System.out.println(decorationMode.toString());
    }
    @Test
    public void searchStatusByIdTest(){
        Integer column = contractDao.searchStatusById(1);
        System.out.println(column);
    }

    @Test
    public void delContractByIdTest(){
        Integer column = contractDao.delContractById(2);
        System.out.println(column);
    }

    @Test
    public void updateStatusByIdTest(){
        Integer column = contractDao.updateStatusById(1,3);
    }

    @Test
    public void searchContractFileByContractIdTest(){
        List<ContractFile> contractFiles = contractDao.searchContractFileByContractId(1);
        for (ContractFile h:contractFiles){
            System.out.println(h.toString());
        }
    }

    @Test
    public void findAllDecorationModeTest(){
        List<DecorationMode> decorationModes = decorationModeDao.findAllDecorationMode();
        for (DecorationMode h:decorationModes) {
            System.out.println(h.toString());
        }
        }

        @Test
    public void Test(){
        //housepicDao.delAllHousepicFileByHousepicId()
           Integer version =  contractDao.getVersionById(1);
            System.out.println(version);
        }


}
