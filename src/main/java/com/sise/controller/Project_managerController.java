package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.*;
import com.sise.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 8:41 2018/3/3
 */
@Controller
@RequestMapping("/project_manager")
public class Project_managerController {
    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RegistrationTableService registrationTableService;

    @Autowired
    private HousepicService housepicService;

    @Autowired
    private DesignerpicService designerpicService;

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/project_manager",method = RequestMethod.GET)
    public String goConsumer_management_intent(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime,@RequestParam(value = "endTime",defaultValue = "",required = false) String endTime,@RequestParam(value = "sname",defaultValue = "",required = false) String sname,
                                               @RequestParam(value = "employeeId",defaultValue = "0",required = false) Integer employeeId,@RequestParam(value = "provinceId",defaultValue = "0",required = false) Integer provinceId,@RequestParam(value = "cityId",defaultValue = "0",required = false) Integer cityId,Model model,@RequestParam(defaultValue = "1",required = false) Integer pageNum){
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
        searchContent.setEmployeeId(employeeId);
        System.out.println(searchContent.toString());
        PageHelper.startPage(pageNum, 8);
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationWithProjectStatusByStatusAndSearchContent(3,searchContent);
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        model.addAttribute("registrationTables",registrationTables);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "project_manager/project_manager";

    }

    @RequestMapping("/housepic")
    public String housepic(@RequestParam("registrationId") Integer registrationId, Model model, HttpSession session){
        Employee employee =(Employee) session.getAttribute("employee");
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        if (employee!=null&registrationTable!=null){
                Housepic housepic = housepicService.findHousepicByRegistrationId(registrationId);
                if (housepic == null) {
                    model.addAttribute("resultStatus", "false");
                } else {
                    List<HousepicFile> housepicFiles = housepicService.findHousepicFileByHousepicId(housepic.getHousepicId());
                    model.addAttribute("housepic", housepic);
                    model.addAttribute("housepicFiles", housepicFiles);
                    model.addAttribute("registrationId", registrationId);
                    model.addAttribute("resultStatus", "true");
                }
        }

        return "project_manager/project_manager_housepic";
    }
    @RequestMapping("/designerpic")
    public String designer(@RequestParam("registrationId") Integer registrationId, Model model,HttpSession session){
        Employee employee =(Employee) session.getAttribute("employee");
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        if (employee!=null&registrationTable!=null){
                Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
                if (designerpic == null) {
                    model.addAttribute("resultStatus", "false");
                } else {
                    List<DesignerpicFile> designerpicFiles = designerpicService.searchAllDesignerpicFileByDesignerId(designerpic.getDesignerpicId());
                    model.addAttribute("designerpic", designerpic);
                    model.addAttribute("designerpicFiles", designerpicFiles);
                    model.addAttribute("registrationId", registrationId);
                    model.addAttribute("resultStatus", "true");
                }
        }

        return "project_manager/project_manager_designerpic";
    }

    @RequestMapping(value = "/setdesignerpicStatus",method =RequestMethod.POST,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String setdesignerpicStatus(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer designerpicId = jsonObject1.getInt("designerpicId");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=designerpicService.searchVersionByDesignerpicId(designerpicId)){
                column = designerpicService.changeStatusByDesignerpicId(designerpicId,5);
        }else {
            msg="数据已被刷新，请重新访问！";
            jsonObject2.put("msg",msg);
            return jsonObject2.toString();
        }
        if (column==1){
            jsonObject2.put("msg","同意成功！");
        }else{
            jsonObject2.put("msg","同意失败！");
        }

        return jsonObject2.toString();
    }

    @RequestMapping(value = "/setdesignerpicStatusFalse" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setdesignerpicStatusFalse(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer designerpicId = jsonObject1.getInt("designerpicId");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=designerpicService.searchVersionByDesignerpicId(designerpicId)){
                column = designerpicService.changeStatusByDesignerpicId(designerpicId,4);
        }else {
            msg="数据已被刷新，请重新访问！";
            jsonObject2.put("msg",msg);
            return jsonObject2.toString();
        }
        if (column==1){
            msg = "操作成功！";
        }else{
            msg = "请刷新界面，重新确认！";
        }
        jsonObject2.put("msg",msg);
        return jsonObject2.toString();
    }
    @RequestMapping("/contract")
    public String contract(@RequestParam("registrationId") Integer registrationId, Model model,HttpSession session){
        Employee employee =(Employee) session.getAttribute("employee");
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        if (employee!=null&registrationTable!=null){
                Contract contract = contractService.findContractByRegistrationId(registrationId);
                if (contract == null) {
                    model.addAttribute("resultStatus", "false");
                } else {
                    List<ContractFile> contractFiles = contractService.findContractFileByContractId(contract.getContractId());
                    model.addAttribute("contract", contract);
                    model.addAttribute("contractFiles", contractFiles);
                    model.addAttribute("registrationId", registrationId);
                    model.addAttribute("resultStatus", "true");
                }
        }

        return "project_manager/project_manager_contract";
    }

    @RequestMapping(value = "/setcontractStatus",method =RequestMethod.POST,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String setcontractStatus(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer contractId = jsonObject1.getInt("contractId");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=contractService.searchVersionById(contractId)){
                column = contractService.changeStatusById(contractId,3);
        }else {
            msg="数据已被刷新，请重新访问！";
            jsonObject2.put("msg",msg);
            return jsonObject2.toString();
        }
        if (column==1){
            jsonObject2.put("msg","同意成功！");
        }else{
            jsonObject2.put("msg","同意失败！");
        }

        return jsonObject2.toString();
    }

    @RequestMapping(value = "/setcontractStatusFalse" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setcontractStatusFalse(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer contractId = jsonObject1.getInt("contractId");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=contractService.searchVersionById(contractId)){
                column = contractService.changeStatusById(contractId,4);
        }else {
            msg="数据已被刷新，请重新访问！";
            jsonObject2.put("msg",msg);
            return jsonObject2.toString();
        }
        if (column==1){
            msg = "操作成功！";
        }else{
            msg = "请刷新界面，重新确认！";
        }
        jsonObject2.put("msg",msg);
        return jsonObject2.toString();
    }

}
