package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sise.po.*;
import com.sise.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/welcome")
public class IndexController {
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private AreaTypeService areaTypeService;

    @Autowired
    private HouseTypeService houseTypeService;

    @Autowired
    private DecorationStyleService decorationStyleService;

    @Autowired
    private DesignerShowService designerShowService;

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

    @Autowired
    private ProjectTimeService projectTimeService;

    @Autowired
    private InspectionReportService inspectionReportService;

    @RequestMapping("/index")
    public String goIndex(@RequestParam(value = "sname",defaultValue = "",required = false) String sname,@RequestParam(value = "houseId",defaultValue = "0",required = false) Integer houseId,
                          @RequestParam(value = "styleId",defaultValue = "0",required = false) Integer styleId,
                          @RequestParam(value = "areaId",defaultValue = "0",required = false) Integer areaId,Model model, @RequestParam(defaultValue = "1",required = false) Integer pageNum){
        SearchDesignerShow searchContent =new SearchDesignerShow();

        if (sname!=null)
            searchContent.setSname(sname);
        if (styleId !=null)
            searchContent.setStyleId(styleId);
        if (houseId !=null)
            searchContent.setHouseId(houseId);
        if(areaId!=null)
            searchContent.setAreaId(areaId);
        System.out.println(searchContent.toString());
        PageHelper.startPage(pageNum, 8);
        List<DesignerShow> designerShows = designerShowService.searchDesignerToIndex(searchContent);
        for (DesignerShow d:designerShows){
            Integer collectcount = designerShowService.getCountCollectionByShowId(d.getShowId());
            Integer praisecount = designerShowService.getCountPraiseByShowId(d.getShowId());
            d.setCollectCount(collectcount);
            d.setPraiseCount(praisecount);
        }
        PageInfo<DesignerShow> pageInfo = new PageInfo<DesignerShow>(designerShows);
        List<AreaType> areaTypes = areaTypeService.findAllAreaType();
        List<HouseType> houseTypes = houseTypeService.findAllHousetype();
        List<DecorationStyle> decorationStyles = decorationStyleService.searchAllDecorationStyle();
        model.addAttribute("areaTypes",areaTypes);
        model.addAttribute("houseTypes",houseTypes);
        model.addAttribute("decorationStyles",decorationStyles);
        model.addAttribute("designerShows",designerShows);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "index";
    }
    /*跳转到客户装修意向提交*/
    @RequestMapping("/consumer_intention")
    public String GoConsumerIntention(Model model){
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        return "consumer_control/consumer_intention";
    }

    @RequestMapping(value = "/getcity",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCity(@RequestBody String provinceId){
        Gson gson = new Gson();
        JSONObject jsonObject = JSONObject.fromObject(provinceId);
        String province = jsonObject.getString("provinceId");
        List<City> cities = consumerService.searchCityByProvinceId(Integer.parseInt(province));
        String result = gson.toJson(cities);
        return result;

    }

    @RequestMapping(value = "/setgood",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setgood(@RequestBody String showId){
        JSONObject jsonObject1 = JSONObject.fromObject(showId);
        String msg = "";;
        Integer show = jsonObject1.getInt("showId");
        Integer praiseMan = jsonObject1.getInt("praiseMan");
        Praise praise = new Praise(praiseMan,show);
        JSONObject jsonObject2 = new JSONObject();
        if (designerShowService.findPraiseByShowId(praise)){
             jsonObject2.put("msg","已点赞过，不能再点！");
             return jsonObject2.toString();
        }else {
            designerShowService.savePraise(praise);
            msg = "1";
        }
        jsonObject2.put("msg",msg);
        return jsonObject2.toString();

    }

    @RequestMapping("/designershow")
    public String designershow(@RequestParam("showId") Integer showId,Model model){
        DesignerShow designerShow = designerShowService.searchDesignerShowByShowId(showId);
        Integer collectcount = designerShowService.getCountCollectionByShowId(showId);
        Integer praisecount = designerShowService.getCountPraiseByShowId(showId);
        Employee employee = employeeService.searchEmployeeById(designerShow.getEmployeeId());
        designerShow.setCollectCount(collectcount);
        designerShow.setPraiseCount(praisecount);
        model.addAttribute("designerShow",designerShow);
        model.addAttribute("employee",employee);
        return "consumer_control/index_designershow";
    }

    @RequestMapping(value = "/setcollect",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setcollect(@RequestBody String showId){
        JSONObject jsonObject1 = JSONObject.fromObject(showId);
        String msg = "";;
        Integer show = jsonObject1.getInt("showId");
        Integer collectman = jsonObject1.getInt("collectman");
        CollectionShow collectionShow = new CollectionShow(collectman,show);
        JSONObject jsonObject2 = new JSONObject();
        if (designerShowService.findCollectByShowId(collectionShow)){
            jsonObject2.put("msg","已收藏过，不能再点！");
            return jsonObject2.toString();
        }else {
            designerShowService.saveCollectionShow(collectionShow);
            msg = "1";
        }
        jsonObject2.put("msg",msg);
        return jsonObject2.toString();

    }
    @RequestMapping("/index_register")
    public String index_register(Model model){
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        return "consumer_control/index_register";
    }

    @RequestMapping(value = "/doregistration" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String doregistration(@RequestBody RegistrationTable registrationTable){
        registrationTable.setRegistrationTime(new Date(System.currentTimeMillis()));
        System.out.println(registrationTable);
        Integer column = registrationTableService.saveRegistration(registrationTable);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","true");
        return jsonObject.toString();
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

    @RequestMapping("/myproject")
    public String myproject(HttpSession session,Model model,@RequestParam(defaultValue = "1",required = false) Integer pageNum){
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        if (consumer!=null){
            String userName = consumer.getUserName();
            PageHelper.startPage(pageNum, 3);
            List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationByUserName(userName);
            PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
            model.addAttribute("resultStatus","1");
            model.addAttribute("registrationTables",registrationTables);
            model.addAttribute("pageInfo",pageInfo);
        }else {
            model.addAttribute("resultStatus","0");
        }
        return "consumer_control/myproject";
    }

    @RequestMapping("/housepic")
    public String housepic(@RequestParam("registrationId") Integer registrationId, Model model,HttpSession session){
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        if (consumer!=null&registrationTable!=null){
           if (consumer.getUserName().equals(registrationTable.getUserName())){
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
        }
        model.addAttribute("registrationId", registrationId);
        return "consumer_control/index_housepic";
    }

    @RequestMapping(value = "/setStatus",method =RequestMethod.POST,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String setStatus(@RequestBody String str){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer housepicId = jsonObject1.getInt("housepicId");
        String userName = jsonObject1.getString("userName");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=housepicService.searchVersionById(housepicId)){
            if (registrationTableService.compareHousepicAndConusmer(userName,housepicId)){
                column = housepicService.changeStatusById(housepicId,3);

            }else {
                msg="无法访问别的客户信息！";
                jsonObject2.put("msg",msg);
                return jsonObject2.toString();
            }
        }else {
            msg="数据已被刷新，请重新访问！";
            jsonObject2.put("msg",msg);
            return jsonObject2.toString();
        }
        if (column==1){
            jsonObject2.put("msg","确认成功！");
        }else{
            jsonObject2.put("msg","确认失败！");
        }

        return jsonObject2.toString();
    }

    @RequestMapping(value = "/setStatusFalse" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setStatusFalse(@RequestBody String str){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer housepicId = jsonObject1.getInt("housepicId");
        String userName = jsonObject1.getString("userName");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=housepicService.searchVersionById(housepicId)){
            if (registrationTableService.compareHousepicAndConusmer(userName,housepicId)){
                column = housepicService.changeStatusById(housepicId,2);

            }else {
                msg="无法访问别的客户信息！";
                jsonObject2.put("msg",msg);
                return jsonObject2.toString();
            }
        }else {
            msg="数据已被刷新，请重新访问！";
            jsonObject2.put("msg",msg);
            return jsonObject2.toString();
        }
        if (column==1){
            msg = "确认户型图错误待修改！";
        }else{
            msg = "请刷新界面，重新确认！";
        }
        jsonObject2.put("msg",msg);
        return jsonObject2.toString();
    }

    @RequestMapping("/designerpic")
    public String designer(@RequestParam("registrationId") Integer registrationId, Model model,HttpSession session){
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        if (consumer!=null&registrationTable!=null){
            if (consumer.getUserName().equals(registrationTable.getUserName())){
                Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
                if (designerpic == null) {
                    model.addAttribute("resultStatus", "false");
                } else {
                    List<DesignerpicFile> designerpicFiles = designerpicService.searchAllDesignerpicFileByDesignerId(designerpic.getDesignerpicId());
                    model.addAttribute("designerpic", designerpic);
                    model.addAttribute("designerpicFiles", designerpicFiles);
                    model.addAttribute("resultStatus", "true");
                }
            }
        }
        model.addAttribute("registrationId", registrationId);
        return "consumer_control/index_designerpic";
    }

    @RequestMapping(value = "/setdesignerpicStatus",method =RequestMethod.POST,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String setdesignerpicStatus(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer designerpicId = jsonObject1.getInt("designerpicId");
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=designerpicService.searchVersionByDesignerpicId(designerpicId)){
            if (consumer!=null && registrationTableService.compareDesignerpicAndConusmer(consumer.getUserName(),designerpicId)){
                column = designerpicService.changeStatusByDesignerpicId(designerpicId,3);

            }else {
                msg="无法访问别的客户信息！";
                jsonObject2.put("msg",msg);
                return jsonObject2.toString();
            }
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
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=designerpicService.searchVersionByDesignerpicId(designerpicId)){
            if (consumer!=null && registrationTableService.compareDesignerpicAndConusmer(consumer.getUserName(),designerpicId)){
                column = designerpicService.changeStatusByDesignerpicId(designerpicId,2);

            }else {
                msg="无法访问别的客户信息！";
                jsonObject2.put("msg",msg);
                return jsonObject2.toString();
            }
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
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        RegistrationTable registrationTable = registrationTableService.searchRegistrationById(registrationId);
        if (consumer!=null&registrationTable!=null){
            if (consumer.getUserName().equals(registrationTable.getUserName())){
                Contract contract = contractService.findContractByRegistrationId(registrationId);
                if (contract == null) {
                    model.addAttribute("resultStatus", "false");
                } else {
                    List<ContractFile> contractFiles = contractService.findContractFileByContractId(contract.getContractId());
                    model.addAttribute("contract", contract);
                    model.addAttribute("contractFiles", contractFiles);
                    model.addAttribute("resultStatus", "true");
                }
            }
        }
        model.addAttribute("registrationId", registrationId);
        return "consumer_control/index_contract";
    }

    @RequestMapping(value = "/setcontractStatus",method =RequestMethod.POST,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String setcontractStatus(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer contractId = jsonObject1.getInt("contractId");
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=contractService.searchVersionById(contractId)){
            if (consumer!=null && registrationTableService.compareContractAndConusmer(consumer.getUserName(),contractId)){
                column = contractService.changeStatusById(contractId,5);

            }else {
                msg="无法访问别的客户信息！";
                jsonObject2.put("msg",msg);
                return jsonObject2.toString();
            }
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
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        if (version>=contractService.searchVersionById(contractId)){
            if (consumer!=null && registrationTableService.compareContractAndConusmer(consumer.getUserName(),contractId)){
                column = contractService.changeStatusById(contractId,4);

            }else {
                msg="无法访问别的客户信息！";
                jsonObject2.put("msg",msg);
                return jsonObject2.toString();
            }
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

    @RequestMapping("/project_time")
    public String project_time(@RequestParam("registrationId") Integer registrationId,@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime,@RequestParam(value = "endTime",defaultValue = "",required = false) String endTime,
                               Model model,@RequestParam(defaultValue = "1",required = false) Integer pageNum){
        Map<String ,Object> map = new HashMap<String ,Object>();
        if (startTime!="")
            map.put("startTime",startTime);
        if (endTime!="")
            map.put("endTime",endTime);
        map.put("registrationId",registrationId);
        PageHelper.startPage(pageNum, 30);
        List<ProjectTime> projectTimes =projectTimeService.searchProjectTimeBySearch(map);
        PageInfo<ProjectTime> pageInfo = new PageInfo<ProjectTime>(projectTimes);
        model.addAttribute("projectTimes",projectTimes);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("map",map);
        return "consumer_control/index_project_time";
    }
    @RequestMapping("/progressContent")
    public String progressContent(@RequestParam(value = "progressId",required = false) Integer progressId,@RequestParam(value = "projectId" ) Integer projectId,Model model){
        List<ProgressContent> progressContents = new ArrayList<ProgressContent>();
        if (projectId!=null){
            progressContents = projectTimeService.searchAllProgressContentTitleByProjectId(projectId);
        }
        ProgressContent progressContent = null;
        ProjectTime projectTime=null;
        if (progressContents.size()>0){
            if (progressId==null){
                progressId = progressContents.get(0).getProgressId();
            }
            projectTime = projectTimeService.searchProjectTimeByProjectId(projectId);
            progressContent = projectTimeService.searchProgressContentByProgressId(progressId);
        }
        model.addAttribute("projectId",projectId);
        model.addAttribute("projectTime",projectTime);
        model.addAttribute("progressContents",progressContents);
        model.addAttribute("progressContent",progressContent);
        return "consumer_control/index_progress_content";
    }

    @RequestMapping("/inspection_report")
    public String inspection_report(@RequestParam("registrationId")Integer registrationId, Model model){
        if (registrationId!=null){
            InspectionReport inspectionReport = inspectionReportService.searchInspectionReportByRegistrationId(registrationId);
            if (inspectionReport!=null){
                List<InspectionReportPic> inspectionReportPics = inspectionReportService.searchInspectionReportpicByReportId(inspectionReport.getReportId());
                model.addAttribute("resultStatus","1");
                model.addAttribute("inspectionReport",inspectionReport);
                model.addAttribute("inspectionReportPics",inspectionReportPics);
            }else {
                model.addAttribute("resultStatus",0);
            }

        }
        model.addAttribute("registrationId",registrationId);
        return "consumer_control/index_inspection_report";
    }

    @RequestMapping(value = "/logout" ,method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String logout(HttpSession session){
        session.removeAttribute("consumer");
        session.removeAttribute("name");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","退出成功！");
        return jsonObject.toString();
    }

}
