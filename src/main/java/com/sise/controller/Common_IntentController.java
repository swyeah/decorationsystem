package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sise.dao.HouseTypeDao;
import com.sise.po.*;
import com.sise.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.krb5.internal.crypto.Des;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:17 2018/2/15
 */
@Controller
@RequestMapping("/common_intent")
public class Common_IntentController {

    @Autowired
    private RegistrationTableService registrationTableService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private AreaTypeService areaTypeService;

    @Autowired
    private HousepicService housepicService;

    @Autowired
    private HouseTypeService houseTypeService;

    @Autowired
    private DecorationStyleService decorationStyleService;

    @Autowired
    private DesignerpicService designerpicService;


   /* @RequestMapping("/consumer_management_intent")
    public String goConsumer_management_intent(Model model,@RequestParam(defaultValue = "1",required = false) Integer pageNum){
        PageHelper.startPage(pageNum, 8);
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationWithAppointmentByStatus(2);
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        for (RegistrationTable r:registrationTables){
            System.out.println(r.toString());
            System.out.println(r.getAppointment().toString());
        }
        model.addAttribute("registrationTables",registrationTables);
        model.addAttribute("pageInfo",pageInfo);
        return "common_control/consumer_management_intent";
    }*/

    @RequestMapping(value = "/consumer_management_intent",method = RequestMethod.GET)
    public String goConsumer_management_intent(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime, @RequestParam(value = "endTime",defaultValue = "",required = false) String endTime, @RequestParam(value = "sname",defaultValue = "",required = false) String sname,
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
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationWithAppByStatusSearchContent(2,searchContent);
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        model.addAttribute("registrationTables",registrationTables);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "common_control/consumer_management_intent";

    }

    @RequestMapping("/consumer_appointment")
    public String goConsumer_appointment(@RequestParam(defaultValue = "1",required = false) Integer pageNum,Model model,@RequestParam("registrationId") Integer registrationId){
        RegistrationTable registrationTable = registrationTableService.searchRegistrationWithAppointmentById(registrationId);
        PageHelper.startPage(pageNum, 8);
        List<Appointment> appointments = appointmentService.searchAppointmentByRegistrationId(registrationId);
        PageInfo<Appointment> pageInfo = new PageInfo<Appointment>(appointments);
        model.addAttribute("appointments",appointments);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("registration",registrationTable);
        return "common_control/consumer_appointment";
    }

    @RequestMapping(value = "/addAppointment" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addTrackingInfo(@Valid @RequestBody Appointment appointment, Errors errors){
        JSONObject jsonObject = new JSONObject();
        if (errors.hasErrors()){
            System.out.println("错误："+appointment.toString());
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

        Integer column = appointmentService.addAppointment(appointment);
        if (column == 1){
            registrationTableService.changAppointmentIdById(appointment.getRegistrationId(),appointment.getAppointmentId());
        }
        jsonObject.put("result","true");
        return jsonObject.toString();
    }
    @RequestMapping(value = "/updateAppointment" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateAppointment(@RequestBody String id){
        JSONObject jsonObject = JSONObject.fromObject(id);
        Integer appointmentId = jsonObject.getInt("appointmentId");
        Appointment appointment = appointmentService.findAppointmentById(appointmentId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("appointment",appointment);
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/changeAppointment" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeAppointment(@Valid @RequestBody Appointment appointment, Errors errors){
        JSONObject jsonObject = new JSONObject();
        if (errors.hasErrors()){
            System.out.println("错误："+appointment.toString());
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
        System.out.println(appointment.toString());
        Integer column = appointmentService.changeAppointment(appointment);
        System.out.println("更新记录Id "+column);
        if (column !=null){
            registrationTableService.changAppointmentIdById(appointment.getRegistrationId(),appointment.getAppointmentId());

        }
        jsonObject.put("result","true");
        return jsonObject.toString();
    }

    @RequestMapping(value = "/deleteAppointment" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteAppointment(@RequestBody String id){
        JSONObject jsonObject = JSONObject.fromObject(id);
        Integer appointmentId = jsonObject.getInt("appointmentId");
        Integer registrationId = jsonObject.getInt("registrationId");
        Integer column = appointmentService.dropAppointmentById(appointmentId);
        JSONObject jsonObject1 = new JSONObject();
        Integer i = registrationTableService.searchAppointmentIdById(registrationId);
        if (i!=null && i==appointmentId &&column==1){
            registrationTableService.changAppointmentIdById(registrationId,null);
            jsonObject1.put("result","删除操作成功");
        }else if (column==1){
            jsonObject1.put("result","删除操作成功");
        }else {
            jsonObject1.put("result","删除操作失败");
        }
        return jsonObject1.toString();
    }

    @RequestMapping("/deposit")
    public String deposit(@RequestParam("registrationId") Integer registrationId,@RequestParam("employeeId") Integer employeeId, Model model){
        model.addAttribute("registrationId",registrationId);
        model.addAttribute("employeeId",employeeId);
        Deposit deposit = null;
        try {
            deposit = depositService.searchDepositByRegistrationId(registrationId);
        }catch (NullPointerException e){
            deposit=null;
        }
        if (deposit!=null){
            model.addAttribute("resultStatus","true");
            model.addAttribute("deposit",deposit);
        }else{
            model.addAttribute("resultStatus","false");
        }
        System.out.println(model.toString());
        return "common_control/deposit_agreement";
    }
    @RequestMapping(value = "/uploadDeposit", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadDeposit(HttpServletRequest request, @RequestParam(value = "file")MultipartFile file) throws IOException, ParseException {
        BigDecimal depositMoney =new BigDecimal( request.getParameter("depositMoney"));
        String depositDescribe = request.getParameter("depositDescribe");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String time = request.getParameter("depositTime");

        Date depositTime = Date.valueOf(time);
        depositMoney = depositMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        String path = request.getSession().getServletContext().getRealPath(
                "/upload/");
        // 上传文件名
        Deposit deposit2 = depositService.searchDepositByRegistrationId(registrationId);
        JSONObject jsonObject = new JSONObject();
        if (deposit2==null) {
            String filename = file.getOriginalFilename();
            String newFilename = UUID.randomUUID()
                    + filename.substring(filename
                    .lastIndexOf("."));
            File filepath = new File("D:\\ProjectUpload\\deposit\\"+registrationId);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.exists()) {
                filepath.mkdirs();

            }

            try {
                file.transferTo(
                        new File("D:\\ProjectUpload\\deposit\\"+registrationId + File.separator + newFilename)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            String depositUrl = "/decorationsystem/upload/deposit/"+registrationId +"/"+ newFilename;
            System.out.println("url  " + depositUrl);
            Deposit deposit1 = new Deposit(depositTime, depositMoney, depositUrl, depositDescribe, registrationId, filename, employeeId, new java.util.Date());
            System.out.println(deposit1.toString());
            Integer column1 = depositService.saveDeposit(deposit1);
            System.out.println("更新行数为"+column1);

        }else {
            jsonObject.put("msg","已存在协议，请先删除！");
            return jsonObject.toString();
        }
        registrationTableService.changStatusByRegistrationId(registrationId,3);
        jsonObject.put("msg","上传成功,客户已成为协议客户");
        return jsonObject.toString();
    }
    @RequestMapping(value = "/changeDeposit", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeDeposit(HttpServletRequest request, @RequestParam(value = "file")MultipartFile file) throws IOException, ParseException {
        BigDecimal depositMoney =new BigDecimal( request.getParameter("depositMoney"));
        String depositDescribe = request.getParameter("depositDescribe");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String time = request.getParameter("depositTime");
        Integer depositId = Integer.parseInt(request.getParameter("depositId"));
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date depositTime = sdf.parse(time);*/
        Date depositTime = Date.valueOf(time);
        String uploadTime = request.getParameter("uploadTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date newtime = sdf.parse(uploadTime);
        java.util.Date sqlTime = depositService.searchUploadTimeById(depositId);
        depositMoney = depositMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        String path = request.getSession().getServletContext().getRealPath(
                "/upload/");
        // 上传文件名
        if (newtime.getTime()>=sqlTime.getTime()){
        String filename = file.getOriginalFilename();
        String newFilename = UUID.randomUUID()
                +filename.substring(filename
                .lastIndexOf("."));
            File filepath = new File("D:\\ProjectUpload\\deposit\\"+registrationId);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.exists()) {
                filepath.mkdirs();

            }
        Deposit deposit =deposit = depositService.searchDepositByRegistrationId(registrationId);

        try {
            file.transferTo(
                    new File("D:\\ProjectUpload\\deposit\\"+registrationId+"\\"+File.separator+newFilename)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        String depositUrl = "/decorationsystem/upload/deposit/"+registrationId+"/"+newFilename;
        Deposit deposit1 = new Deposit(depositTime,depositMoney,depositUrl,depositDescribe,registrationId,filename,employeeId,new java.util.Date());
        deposit1.setDepositId(depositId);
        Integer column1 = depositService.changeDepositById(deposit1);
        if (deposit!=null) {
            File file1 = new File(deposit.getDepositUrl());
            File file2 = new File("D:\\ProjectUpload\\deposit\\"+registrationId+"\\" + File.separator + file1.getName());
            try {
                if (file2.delete()) {
                    System.out.println(file2.getName() + "is deleted");
                } else {
                    System.out.println("Delete failed.");
                }
            } catch (Exception e) {
                System.out.println("Exception occured");
                e.printStackTrace();
            }
        }
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","协议被修改过，请刷新！");
            return jsonObject.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","更新成功");
        return jsonObject.toString();
    }


    @RequestMapping(value = "/delDeposit" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delDeposit( @RequestBody String str) throws ParseException {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer registrationId = jsonObject.getInt("registrationId");
        Deposit deposit =  depositService.searchDepositByRegistrationId(registrationId);
        JSONObject jsonObject2 = new JSONObject();
        if (deposit!=null){
        Integer column = depositService.dropDepositByRegistrationId(registrationId);
        if (column==1){
            File file1 = new File(deposit.getDepositUrl());
            File file2 = new File("D:\\ProjectUpload\\deposit\\"+registrationId+"\\" + File.separator + file1.getName());
            try {
                if (file2.delete()) {
                    System.out.println(file2.getName() + "is deleted");
                } else {
                    System.out.println("Delete failed.");
                }
            } catch (Exception e) {
                System.out.println("Exception occured");
                e.printStackTrace();
            }
            jsonObject2.put("result","删除成功");
        }else{
            jsonObject2.put("result","删除失败");
        }}else {
            jsonObject2.put("result","协议已被人删除！");
            return jsonObject2.toString();
        }

        return jsonObject2.toString();
    }


}
