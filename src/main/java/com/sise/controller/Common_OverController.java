package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.*;
import com.sise.service.ConsumerService;
import com.sise.service.GuaranteeService;
import com.sise.service.RegistrationTableService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 1:41 2018/3/6
 */
@Controller
@RequestMapping("/common_over")
public class Common_OverController {

    @Autowired
    private RegistrationTableService registrationTableService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private GuaranteeService guaranteeService;


    @RequestMapping(value = "/common_management_over",method = RequestMethod.GET)
    public String common_contract_management(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime, @RequestParam(value = "endTime",defaultValue = "",required = false) String endTime, @RequestParam(value = "sname",defaultValue = "",required = false) String sname,
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
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationwithGuaranteeByStatusAndSearchContent(5,searchContent);
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        model.addAttribute("registrationTables",registrationTables);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "common_control/consumer_management_over";
    }

    @RequestMapping(value = "/changeGuarantee",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeGuarantee(@Valid @RequestBody Guarantee guarantee, Errors errors) {
        JSONObject jsonObject = new JSONObject();
        String  msg="";
        if(errors.hasErrors()){
            //获取错误信息
            System.out.println("进入获取错误信息");
            List<FieldError> errorList = errors.getFieldErrors();
            for (FieldError e:errorList){

                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg = msg+e.getDefaultMessage()+"<br/>";
            }

            jsonObject.put("msg",msg);
            return jsonObject.toString();
        }
        Integer coulmn = guaranteeService.changeGuarantee(guarantee);
        if (coulmn==0){
            jsonObject.put("msg","修改失败！");
        }else{
            registrationTableService.changStatusByRegistrationId(guarantee.getRegistrationId(),5);
            jsonObject.put("msg","修改成功！");
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/getGuarantee",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeGuarantee(@RequestBody String str) {
        System.out.println(str);
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer registrationId = jsonObject.getInt("registrationId");
        Guarantee guarantee = guaranteeService.searchGuaranteeByRegistrationId(registrationId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("startTime",guarantee.getStartTime().toString());
        return jsonObject1.toString();
    }
}
