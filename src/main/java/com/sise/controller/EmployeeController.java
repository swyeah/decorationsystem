package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.Employee;
import com.sise.po.Province;
import com.sise.po.RegistrationTable;
import com.sise.po.SearchContent;
import com.sise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 0:30 2018/3/1
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

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
    @RequestMapping(value = "/consumer_management",method = RequestMethod.GET)
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
        if (employee.getJob().getJobId()!=10005){
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
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

    public Integer calculateDate(Date date){
        long from = date.getTime();
        long to = new Date(System.currentTimeMillis()).getTime();
        Integer days = (int) ((to - from)/(1000 * 60 * 60 * 24));
        return days;
    }
}
