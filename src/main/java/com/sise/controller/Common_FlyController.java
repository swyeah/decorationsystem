package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.*;
import com.sise.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 13:04 2018/3/6
 */
@Controller
@RequestMapping("/common_fly")
public class Common_FlyController {
    @Autowired
    private RegistrationTableService registrationTableService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private CancelTableService cancelTableService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private DesignerpicService designerpicService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private HousepicService housepicService;

    @Autowired
    private InspectionReportService inspectionReportService;


    @RequestMapping(value = "/consumer_management_fly",method = RequestMethod.GET)
    public String goConsumer_management(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime, @RequestParam(value = "endTime",defaultValue = "",required = false) String endTime, @RequestParam(value = "sname",defaultValue = "",required = false) String sname,
                                        @RequestParam(value = "provinceId",defaultValue = "0",required = false) Integer provinceId, @RequestParam(value = "cityId",defaultValue = "0",required = false) Integer cityId, Model model, @RequestParam(defaultValue = "1",required = false) Integer pageNum, HttpSession session){
        SearchContent searchContent =new SearchContent();
        System.out.println(sname);
        if (!startTime.equals(""))
            searchContent.setStartTime(Date.valueOf(startTime));
        if (!endTime.equals(""))
            searchContent.setEndTime(Date.valueOf(endTime));
        if (sname!=null)
            searchContent.setSname(sname);
        if (provinceId !=null)
            searchContent.setProvinceId(provinceId);
        if (cityId!=null)
            searchContent.setCityId(cityId);
        Employee employee = (Employee) session.getAttribute("employee");
        searchContent.setEmployee(employee);
        PageHelper.startPage(pageNum, 8);
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationWithSearchContentByStatus(6,searchContent);
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        List<Integer> days = new ArrayList<Integer>();
        for (RegistrationTable r:registrationTables){
            Integer day = calculateDate(r.getNotrackingDays());
            r.setDays(day);
        }
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        model.addAttribute("registrationTables",registrationTables);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "common_control/consumer_management_fly";

    }
    public Integer calculateDate(Date date){
        long from = date.getTime();
        long to = new Date(System.currentTimeMillis()).getTime();
        Integer days = (int) ((to - from)/(1000 * 60 * 60 * 24));
        return days;
    }

    @RequestMapping(value = "/getCancelTable",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCancelTable(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer registrationId = jsonObject.getInt("registrationId");
        CancelTable cancelTable = cancelTableService.searchCancelTableByRegistrationId(registrationId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("cancelTable",cancelTable);
        return jsonObject1.toString();

    }
    @RequestMapping(value = "/delCancelTable",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delCancelTable(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer registrationId = jsonObject.getInt("registrationId");
        JSONObject jsonObject1 = new JSONObject();
        CancelTable cancelTable = cancelTableService.searchCancelTableByRegistrationId(registrationId);
        if(cancelTable !=null){
            try {
                delProject(registrationId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            jsonObject1.put("msg","无飞单信息！");
            return jsonObject1.toString();
        }
        cancelTableService.dropCancelTableByRegistrationId(registrationId);
        Integer result = registrationTableService.dropRegistrationTableById(registrationId);
        if (result==0){
            jsonObject1.put("msg","飞单删除失败！");
        }else {
            jsonObject1.put("msg","飞单删除成功！");
        }
        return  jsonObject1.toString();

    }

    public void delProject(Integer registrationId) throws IOException {
       Deposit deposit = depositService.searchDepositByRegistrationId(registrationId);
       if (deposit!=null){
           depositService.dropDepositByRegistrationId(registrationId);
           Housepic housepic = housepicService.findHousepicByRegistrationId(registrationId);
           Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
           Contract contract = contractService.findContractByRegistrationId(registrationId);
           if (housepic!=null){
               FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\housepic\\"+registrationId));
               housepicService.dropAllHousepicFileByHouseId(housepic.getHousepicId());
               housepicService.dripHousepicById(housepic.getHousepicId());
           }
           if (designerpic!=null){
               FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\designerpic\\"+registrationId));
               designerpicService.dropDesignerpicFileByDesignerpicId(designerpic.getDesignerpicId());
               designerpicService.dropDesignerpicByDesignerpicId(designerpic.getDesignerpicId());
           }
           if (contract!=null){
               FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\contract\\"+registrationId));
               contractService.dropAllContractFileByContractId(contract.getContractId());
               contractService.dropContractById(contract.getContractId());
           }
       }
    }

    @RequestMapping(value = "/resetCancelTable",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String resetCancelTable(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer registrationId = jsonObject.getInt("registrationId");
        JSONObject jsonObject1 = new JSONObject();
        CancelTable cancelTable = cancelTableService.searchCancelTableByRegistrationId(registrationId);
        if(cancelTable !=null){
           Integer setresult= resetProject(registrationId);
           cancelTableService.dropCancelTableByRegistrationId(registrationId);
            System.out.println(setresult);
           Integer column =registrationTableService.changStatusByRegistrationId(registrationId,setresult);
           if (column==0){
               jsonObject1.put("msg","操作错误！");
               return jsonObject1.toString();
           }else {
               String msg = "";
               if (setresult==1)
                   msg="客户变回登记客户！";
               if (setresult==2)
                   msg="客户变回意向客户！";
               if (setresult==3)
                   msg="客户变回协议客户！";
               jsonObject1.put("msg","操作成功！"+msg);
               return jsonObject1.toString();
           }
        }else {
            jsonObject1.put("msg","无飞单信息！");
            return jsonObject1.toString();
        }

    }

    public Integer resetProject(Integer registrationId) {
        Integer result = 1;
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        try {
            result = registrationTable.getDesigner().getEmployeeId();
        }catch (NullPointerException e){
            result=1;
            return 1;
        }
            result = 2;
            Deposit deposit = depositService.searchDepositByRegistrationId(registrationId);
            if (deposit!=null){
                result=3;
                /*Contract contract = contractService.findContractByRegistrationId(registrationId);
                if (contract.getStatus()==6){
                    result=4;
                    InspectionReport inspectionReport = inspectionReportService.searchInspectionReportByRegistrationId(registrationId);
                    if (inspectionReport!=null){
                        if (inspectionReport.getReportStatus()==1){
                            result=5;
                        }
                    }
                }*/
            }
       return result;
    }

}
