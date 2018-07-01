package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.*;
import com.sise.service.*;
import com.sise.util.MyAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:02 2018/2/24
 */
@Controller
@RequestMapping("/picture_management")
public class PictureController {

    @Autowired
    private HousepicService housepicService;

    @Autowired
    private HouseTypeService houseTypeService;

    @Autowired
    private AreaTypeService areaTypeService;

    @Autowired
    private DecorationStyleService decorationStyleService;

    @Autowired
    private DesignerpicService designerpicService;

    @Autowired
    private DecorationModeService decorationModeService;

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/house_management",method = RequestMethod.GET)
    public String goConsumer_management_intent(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime, @RequestParam(value = "endTime",defaultValue = "",required = false) String endTime, @RequestParam(value = "hname",defaultValue = "",required = false) String hname,
                                               @RequestParam(value = "houseId",defaultValue = "0",required = false) Integer houseId, @RequestParam(value = "areaId",defaultValue = "0",required = false) Integer areaId, Model model, @RequestParam(defaultValue = "1",required = false) Integer pageNum, HttpSession session){
        SearchHousepic searchContent =new SearchHousepic();
        System.out.println(hname);
        if (!startTime.equals(""))
            searchContent.setStartTime(new java.util.Date(Date.valueOf(startTime).getTime()));
        if (!endTime.equals(""))
            searchContent.setEndTime(Date.valueOf(endTime));
        if (hname!=null)
            searchContent.setHousepicName(hname);
        if (houseId !=null)
            searchContent.setHouseId(houseId);
        if (areaId!=null)
            searchContent.setAreaId(areaId);
        System.out.println(searchContent.toString());
        Employee employee = (Employee) session.getAttribute("employee");
        if (MyAuthUtils.findAuth(employee.getJob().getMyAuths(),62)){
            searchContent.setEmployeeId(0);
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        PageHelper.startPage(pageNum, 8);
        List<Housepic> housepics =housepicService.searchAllHousepic(searchContent);
        PageInfo<Housepic> pageInfo = new PageInfo<Housepic>(housepics);
        List<HouseType> houseTypes = houseTypeService.findAllHousetype();
        List<AreaType> areaTypes = areaTypeService.findAllAreaType();
        model.addAttribute("houseTypes",houseTypes);
        model.addAttribute("areaTypes",areaTypes);
        model.addAttribute("housepics",housepics);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "picture_control/housepic_management";

    }

    @RequestMapping(value = "/designerpic_management",method = RequestMethod.GET)
    public String designerpic_management(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime, @RequestParam(value = "endTime",defaultValue = "",required = false) String endTime, @RequestParam(value = "dname",defaultValue = "",required = false) String dname,
                                         @RequestParam(value = "styleId",defaultValue = "0",required = false) Integer styleId,@RequestParam(value = "houseId",defaultValue = "0",required = false) Integer houseId, @RequestParam(value = "areaId",defaultValue = "0",required = false) Integer areaId, Model model, @RequestParam(defaultValue = "1",required = false) Integer pageNum,HttpSession session){
        SearchDesignerpic searchContent =new SearchDesignerpic();
        if (!startTime.equals(""))
            searchContent.setStartTime(new java.util.Date(Date.valueOf(startTime).getTime()));
        if (!endTime.equals(""))
            searchContent.setEndTime(Date.valueOf(endTime));
        if (dname!=null)
            searchContent.setDesignerpicName(dname);
        if (houseId !=null)
            searchContent.setHouseId(houseId);
        if (areaId!=null)
            searchContent.setAreaId(areaId);
        if (styleId!=null)
            searchContent.setStyleId(styleId);
        System.out.println(searchContent.toString());
        Employee employee = (Employee) session.getAttribute("employee");
        if (MyAuthUtils.findAuth(employee.getJob().getMyAuths(),63)){
            searchContent.setEmployeeId(0);
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        PageHelper.startPage(pageNum, 8);
        List<Designerpic> designerpics = designerpicService.searchAllDesignerpicBySearch(searchContent);
        PageInfo<Designerpic> pageInfo = new PageInfo<Designerpic>(designerpics);
        List<HouseType> houseTypes = houseTypeService.findAllHousetype();
        List<AreaType> areaTypes = areaTypeService.findAllAreaType();
        List<DecorationStyle> styles = decorationStyleService.searchAllDecorationStyle();
        model.addAttribute("houseTypes",houseTypes);
        model.addAttribute("areaTypes",areaTypes);
        model.addAttribute("styles",styles);
        model.addAttribute("designerpics",designerpics);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "picture_control/designerpic_management";

    }
    @RequestMapping(value = "/contract_management",method = RequestMethod.GET)
    public String designerpic_management(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime, @RequestParam(value = "endTime",defaultValue = "",required = false) String endTime, @RequestParam(value = "cname",defaultValue = "",required = false) String cname,
                                         @RequestParam(value = "modeId",defaultValue = "0",required = false) Integer modeId, Model model, @RequestParam(defaultValue = "1",required = false) Integer pageNum,HttpSession session){
        SearchContract searchContent =new SearchContract();
        if (!startTime.equals(""))
            searchContent.setStartTime(new java.util.Date(Date.valueOf(startTime).getTime()));
        if (!endTime.equals(""))
            searchContent.setEndTime(Date.valueOf(endTime));
        if (cname!=null)
            searchContent.setContractName(cname);
        if (modeId !=null)
            searchContent.setModeId(modeId);
        System.out.println(searchContent.toString());
        Employee employee = (Employee) session.getAttribute("employee");
        if (MyAuthUtils.findAuth(employee.getJob().getMyAuths(),64)){
            searchContent.setEmployeeId(0);
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        PageHelper.startPage(pageNum, 8);
        List<Contract> contracts = contractService.searchAllBySearch(searchContent);
        PageInfo<Contract> pageInfo = new PageInfo<Contract>(contracts);
        List<DecorationMode> decorationModes = decorationModeService.searchAllDecorationMode();
        model.addAttribute("decorationModes",decorationModes);
        model.addAttribute("contracts",contracts);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "picture_control/contract_management";

    }
}
