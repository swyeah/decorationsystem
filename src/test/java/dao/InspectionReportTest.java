package dao;

import com.sise.dao.InspectionReportDao;
import com.sise.po.Employee;
import com.sise.po.InspectionReport;
import com.sise.po.InspectionReportPic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 18:45 2018/3/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class InspectionReportTest {
    @Autowired
    private InspectionReportDao inspectionReportDao;

    @Test
    public void Test(){
        InspectionReport inspectionReport = new InspectionReport();
        inspectionReport.setRegistrationId(100075);
        inspectionReport.setReportStatus(0);
        inspectionReport.setUploadTime(new Date());
        Employee employee = new Employee();
        employee.setEmployeeId(10001);
        inspectionReport.setEmployee(employee);
        Integer column = inspectionReportDao.insertInspectionReport(inspectionReport);
        System.out.println("插入数据返回主键："+inspectionReport.getReportId());

    }

    /*更新一个工程验收报告*/
    @Test
    public void updateInspectionReport(){
        InspectionReport inspectionReport = new InspectionReport();
        inspectionReport.setRegistrationId(100075);
        inspectionReport.setReportStatus(1);
        inspectionReport.setUploadTime(new Date());
        Employee employee = new Employee();
        employee.setEmployeeId(10003);
        inspectionReport.setEmployee(employee);
        inspectionReport.setVersion(2);
        inspectionReportDao.updateInspectionReport(inspectionReport);
    }

    /*删除一个工程验收报告*/
    @Test
    public void delInspectionReport(){
        inspectionReportDao.delInspectionReport(100075);
    }

    /*查找registrationid工程验收报告*/
    @Test
    public void findInspectionReportByRegistrationId(){
        InspectionReport inspectionReport = inspectionReportDao.findInspectionReportByRegistrationId(100076);
        System.out.println(inspectionReport);
    }

    /*插入工程验收报告图片*/
    @Test
    public void insertInspectionReportPic(){
        InspectionReportPic inspectionReportPic = new InspectionReportPic();
        inspectionReportPic.setFileName("142");
        inspectionReportPic.setFileUrl("dsfsdf");
        inspectionReportPic.setReportId(1);
        inspectionReportDao.insertInspectionReportPic(inspectionReportPic);
    }

    /*删除工程验收报告图片*/
    @Test
    public void delInspectionReportPicByReportId(){
        inspectionReportDao.delInspectionReportPicByReportId(1);
    }

    /*根据reportId查找验收报告图片*/
    @Test
    public void findInspectionReportpicByReportId(){
        List<InspectionReportPic> inspectionReportPics = inspectionReportDao.findInspectionReportpicByReportId(1);
        for (InspectionReportPic i:inspectionReportPics){
            System.out.println(i);
        }
    }
    @Test
    public void getInspectionReportVersionByRegistrationId(){
        System.out.println(inspectionReportDao.getInspectionReportVersionByRegistrationId(100076));
    }
}
