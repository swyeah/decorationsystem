package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sise.po.*;
import com.sise.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Registration;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 14:43 2018/2/4
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private RegistrationTableService registrationTableService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TrackingInfoService trackingInfoService;

    @Autowired
    private CancelTableService cancelTableService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/consumer_management",method = RequestMethod.GET)
    public String goConsumer_management(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime,@RequestParam(value = "endTime",defaultValue = "",required = false) String endTime,@RequestParam(value = "sname",defaultValue = "",required = false) String sname,
                                        @RequestParam(value = "provinceId",defaultValue = "0",required = false) Integer provinceId,@RequestParam(value = "cityId",defaultValue = "0",required = false) Integer cityId,Model model,@RequestParam(defaultValue = "1",required = false) Integer pageNum,HttpSession session){
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
        System.out.println(searchContent.toString());
        PageHelper.startPage(pageNum, 8);
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationWithSearchContentByStatus(1,searchContent);
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
        return "common_control/consumer_management";

    }




    /*前往登录表*/
    @RequestMapping(value = "/consumer_register")
    public String goConsumer_register( Model model){

        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        return "common_control/consumer_register";
    }

    @RequestMapping("/designate_designer")
    public String goDesignate_designer(@RequestParam(defaultValue = "1",required = false) Integer pageNum,Model model,@RequestParam(required = false,defaultValue = "",value = "search") String search){
        List<Employee> employees = new ArrayList<Employee>();

        if (search.equals("") || search ==null){
            PageHelper.startPage(pageNum, 8);
            employees = employeeService.searchAllDesigner();
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
            model.addAttribute("pageInfo",pageInfo);
        }else {
            PageHelper.startPage(pageNum, 8);
            employees= employeeService.searchEmployBySearch(search);
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
            model.addAttribute("pageInfo",pageInfo);
        }
        model.addAttribute("employees",employees);
        return "common_control/designate_designer";
    }
    /*返回指定设计师数据*/
    @RequestMapping(value = "/getDesigner" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDesigner(@RequestBody String requestdata){
        JSONObject jsonObject = JSONObject.fromObject(requestdata);
        Integer pageNum = Integer.parseInt(jsonObject.getString("pageNum"));
        String search =jsonObject.getString("search");
        List list = new ArrayList();
        if (search.equals("")){
            List<Employee> employees = new ArrayList<Employee>();
            PageHelper.startPage(pageNum, 5);
            employees = employeeService.searchAllDesigner();
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
            list.add(pageInfo);
            list.add(employees);
        }else {
            List<Employee> employees = new ArrayList<Employee>();
            PageHelper.startPage(pageNum, 5);
            employees= employeeService.searchEmployBySearch(search);
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
            list.add(pageInfo);
            list.add(employees);
        }
        Gson gson = new Gson();
        String result = gson.toJson(list);
        return result;
    }

    /*返回指定市场人员数据*/
    @RequestMapping(value = "/getMarketer" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMarketer(@RequestBody String requestdata){
        JSONObject jsonObject = JSONObject.fromObject(requestdata);
        Integer pageNum = Integer.parseInt(jsonObject.getString("pageNum"));
        String search =jsonObject.getString("search");
        List list = new ArrayList();
        if (search.equals("")){
            List<Employee> employees = new ArrayList<Employee>();
            PageHelper.startPage(pageNum, 5);
            employees = employeeService.searchAllMarketer();
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
            list.add(pageInfo);
            list.add(employees);
        }else {
            List<Employee> employees = new ArrayList<Employee>();
            PageHelper.startPage(pageNum, 5);
            employees= employeeService.searchMarketerBySearch(search);
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
            list.add(pageInfo);
            list.add(employees);
        }
        Gson gson = new Gson();
        String result = gson.toJson(list);
        return result;
    }


    @RequestMapping("getConsumer")
    @ResponseBody
    public String getConsumer(@RequestParam("userName") String userName){

        return "";
    }
    /*返回提交登记数据*/
    @RequestMapping(value = "/doregistration" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doregistration(@RequestBody RegistrationTable registrationTable){
        Integer column = registrationTableService.saveRegistration(registrationTable);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","true");
        return jsonObject.toString();
    }

    public Integer calculateDate(Date date){
        long from = date.getTime();
        long to = new Date(System.currentTimeMillis()).getTime();
        Integer days = (int) ((to - from)/(1000 * 60 * 60 * 24));
        return days;
    }
    /*返回指定设计师和市场人员数据*/
    @RequestMapping(value = "/management_setdesigner" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String management_setdesigner(@RequestBody String requestdata){
        JSONObject jsonObject = JSONObject.fromObject(requestdata);
        Integer employeeId = Integer.parseInt(jsonObject.getString("employeeId"));
        Integer registrationId = Integer.parseInt(jsonObject.getString("registrationId"));
        Integer versionA = Integer.parseInt(jsonObject.getString("version"));
        Integer version = registrationTableService.seeVersion(registrationId);
        String result = "";
        if (versionA>=version){
            Integer column = registrationTableService.setDesigner(employeeId,registrationId,version+1);
            registrationTableService.changStatusByRegistrationId(registrationId,2);
            result = "指定成功，客户已成为意向客户";
        }else{
            result = "指定失败,当前数据已被其他人修改";
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject.toString();
    }

    @RequestMapping("/tracking_info")
    public String goTrackingInfo(@RequestParam(defaultValue = "1",required = false) Integer pageNum,Model model,@RequestParam("registrationId") Integer registrationId,@RequestParam("employeeId") Integer employeeId){
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        PageHelper.startPage(pageNum, 8);
        List<TrackingInfo> trackingInfos = trackingInfoService.searchTrackingInfoByRegistrationId(registrationId);
        PageInfo<TrackingInfo> pageInfo = new PageInfo<TrackingInfo>(trackingInfos);
        Employee employee = employeeService.searchEmployeeById(employeeId);
        model.addAttribute("trackingInfos",trackingInfos);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("registration",registrationTable);
        model.addAttribute("employee",employee);
        return "common_control/tracking_info";
    }

    @RequestMapping(value = "/addTrackingInfo" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addTrackingInfo(@Valid @RequestBody TrackingInfo trackingInfo, Errors errors){
        JSONObject jsonObject = new JSONObject();
        if (errors.hasErrors()){
            System.out.println("错误："+trackingInfo.toString());
            List<FieldError> errorList = errors.getFieldErrors();
            String msg="";
            for (FieldError e:errorList){
                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg=msg+e.getDefaultMessage();
            }
            String result="false";
            jsonObject.put("result",result);
            jsonObject.put("msg",msg);
            return jsonObject.toString();
        }
        System.out.println(trackingInfo.toString());
        trackingInfo.setInfoTime(new java.util.Date());
        Integer column = trackingInfoService.saveTrackingInfo(trackingInfo);
        if (column==1){
            RegistrationTable registrationTable= registrationTableService.searchRegistrationById(trackingInfo.registrationId);
            RegistrationTable update = Add_ChangRegistrationInfo(registrationTable,trackingInfo);
            Integer resultcolumn = registrationTableService.changInfo(registrationTable);

        }
        jsonObject.put("result","true");
        return jsonObject.toString();
    }

    /*更新跟踪信息*/
    @RequestMapping(value = "/getTrackingInfo" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTrackingInfo(@RequestBody String infoId){
        JSONObject jsonObject = JSONObject.fromObject(infoId);
        Integer infoID = Integer.parseInt(jsonObject.getString("infoId"));
        TrackingInfo trackingInfo = trackingInfoService.searchTrackingInfo(infoID);
        JSONObject jsonObject1 = JSONObject.fromObject(trackingInfo);
        return jsonObject1.toString() ;
    }


    /*更新跟踪信息*/
    @RequestMapping(value = "/updateTrackingInfo" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateTrackingInfo(@Valid @RequestBody TrackingInfo trackingInfo, Errors errors){
        JSONObject jsonObject = new JSONObject();
        if (errors.hasErrors()){
            System.out.println("错误："+trackingInfo.toString());
            List<FieldError> errorList = errors.getFieldErrors();
            String msg="";
            for (FieldError e:errorList){
                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg=msg+e.getDefaultMessage();
            }
            String result="false";
            jsonObject.put("result",result);
            jsonObject.put("msg",msg);
            return jsonObject.toString();
        }
        System.out.println(trackingInfo.toString());
        Integer column = trackingInfoService.changTrackingInfo(trackingInfo.infoId,trackingInfo.infoContent,trackingInfo.infoType,trackingInfo.warning,trackingInfo.businessStatus);
        if (column==1){
            RegistrationTable registrationTable=registrationTableService.searchRegistrationById(trackingInfo.registrationId);
            RegistrationTable update = Update_ChangRegistrationInfo(registrationTable,trackingInfo);
            Integer resultcolumn = registrationTableService.changInfo(update);
        }
        jsonObject.put("result","true");
        return jsonObject.toString();
    }

    public RegistrationTable Add_ChangRegistrationInfo(RegistrationTable registrationTable,TrackingInfo trackingInfo){
        String businessStatus = trackingInfo.getBusinessStatus();
        Integer trackingTimes = trackingInfoService.changRistrationTrackingTime(trackingInfo.registrationId);
        Date notrackingDays = new Date(trackingInfo.getInfoTime().getTime());
        Integer warning = trackingInfoService.changRistrationWarningTimes(trackingInfo.registrationId);
        registrationTable.setBusinessStatus(businessStatus);
        registrationTable.setTrackingTimes(trackingTimes);
        registrationTable.setNotrackingDays(notrackingDays);
        registrationTable.setWarningTimes(warning);
        return registrationTable;
    }

    public RegistrationTable Update_ChangRegistrationInfo(RegistrationTable registrationTable,TrackingInfo trackingInfo){
        String businessStatus = trackingInfo.getBusinessStatus();
        Integer warning = trackingInfoService.changRistrationWarningTimes(trackingInfo.registrationId);
        registrationTable.setBusinessStatus(businessStatus);
        registrationTable.setWarningTimes(warning);
        return registrationTable;
    }
    /*插入飞单审批*/
    @RequestMapping(value = "/setCancelTable" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setCancelTable(@Valid @RequestBody CancelTable cancelTable, Errors errors){
        JSONObject jsonObject = new JSONObject();
        if (errors.hasErrors()){
            System.out.println("错误："+cancelTable.toString());
            List<FieldError> errorList = errors.getFieldErrors();
            String msg="";
            for (FieldError e:errorList){
                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg=msg+e.getDefaultMessage();
            }
            String result="操作失败，";
            jsonObject.put("result",result);
            jsonObject.put("msg",msg);
            return jsonObject.toString();
        }
        cancelTable.setCancelStatus(false);
        cancelTable.setCancelTime(new java.util.Date());
        System.out.println(cancelTable.toString());

        Integer resultcolumn = cancelTableService.saveCancelTable(cancelTable);
        if (resultcolumn==1){
            Integer s = registrationTableService.changStatusByRegistrationId(cancelTable.getRegistrationId(),6);
        }
        jsonObject.put("result","操作成功");
        return jsonObject.toString();
    }
    @RequestMapping("/resetpassword")
    public String resetpassword(){
        return "common_control/change_password";
    }

    @RequestMapping(value = "/changepassword" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changepassword(@RequestBody String str, HttpSession session){
        JSONObject jsonObject = JSONObject.fromObject(str);
        String oldpassword = jsonObject.getString("oldpassword");
        String password = jsonObject.getString("password");
        Employee employee = (Employee) session.getAttribute("employee");
        boolean result = employeeService.changeEmployeepasswordByEmployeeId(employee.getEmployeeId(),password,oldpassword);
        JSONObject jsonObject1 = new JSONObject();
        if (employee==null){
            jsonObject1.put("msg","登录已过期，请重新登录！");
            return jsonObject1.toString();
        }
        if (result){
            jsonObject1.put("msg","修改密码成功！");
        }else {
            jsonObject1.put("msg","修改密码失败,或密码错误！");
        }
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/logout" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String logout(HttpSession session){
        session.removeAttribute("employee");
        session.removeAttribute("name");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","退出成功！");
        return jsonObject.toString();
    }

    @RequestMapping("bind_username")
    public String bind_username(){
        return "common_control/bind_username";
    }
    @RequestMapping(value = "/checkUserName" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(@RequestBody String userName){
        String checkUserName = userName.substring(9);
        System.out.println("input输入的账号为"+checkUserName);
        Map<String,String> map= new HashMap<String, String>();
        if(loginService.checkUserName(checkUserName)!=null && !loginService.checkUserName(checkUserName).equals("")){
            System.out.println("账号存在，可以绑定");
            map.put("valid","true");
        }else{
            System.out.println("账号不存在");
            map.put("valid","false");
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "/checkRegistrationId" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkRegistrationId(@RequestBody String registrationId){
        String checkregistrationId = registrationId.substring(15);
        System.out.println("input输入的登记编号为"+checkregistrationId);
        Map<String,String> map= new HashMap<String, String>();
        RegistrationTable registration = registrationTableService.searchRegistrationById(Integer.parseInt(checkregistrationId));
        if(registration!=null){
            System.out.println("登记编号存在，可以绑定");
            map.put("valid","true");
        }else{
            System.out.println("登记编号不存在");
            map.put("valid","false");
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);
        return result;
    }
    @RequestMapping(value = "/changeRegistrationId" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeRegistrationId(@RequestBody String str, HttpSession session){
        JSONObject jsonObject = JSONObject.fromObject(str);
       Integer registrationId = jsonObject.getInt("registrationId");
        String userName = jsonObject.getString("userName");
        Integer status = jsonObject.getInt("status");
        Employee employee = (Employee) session.getAttribute("employee");
        JSONObject jsonObject1 = new JSONObject();
        if (employee==null){
            jsonObject1.put("msg","登录已过期，请重新登录！");
            return jsonObject1.toString();
        }
        String name = registrationTableService.searchUserNameByRegistrationId(registrationId);
        if(status==0){
            if (!name.equals("")  ){
                jsonObject1.put("resultstatus","1");
                jsonObject1.put("msg","该表单已绑定客户账号【"+name+"】，请确认是否继续!<button class='btn btn-primary' type='button' onclick='changeRegistration()' >继续绑定</button>");
                return jsonObject1.toString();
            }else {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("registrationId",registrationId);
                map.put("userName",userName);
                Integer column = registrationTableService.changeUserNameByRegistrationId(map);
                if (column==1){
                    jsonObject1.put("msg","成功绑定客户账号【"+userName+"】");
                }else {
                    jsonObject1.put("msg","请刷新确认登记记录是否存在！");
                }

            }
        }else {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("registrationId",registrationId);
            map.put("userName",userName);
            Integer column = registrationTableService.changeUserNameByRegistrationId(map);
            if (column==1){
                jsonObject1.put("msg","成功绑定客户账号【"+userName+"】");
            }else {
                jsonObject1.put("msg","请刷新确认登记记录是否存在！");
            }
        }
        jsonObject1.put("resultstatus","0");
        return jsonObject1.toString();
    }
}
