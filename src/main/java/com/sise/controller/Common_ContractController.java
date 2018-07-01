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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:16 2018/3/3
 */
@Controller
@RequestMapping("/common_contract")
public class Common_ContractController {
    @Autowired
    private RegistrationTableService registrationTableService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ProjectTimeService projectTimeService;

    @Autowired
    private InspectionReportService inspectionReportService;

    @Autowired
    private GuaranteeService guaranteeService;

    @RequestMapping(value = "/common_management_contract",method = RequestMethod.GET)
    public String common_contract_management(@RequestParam(value = "startTime",defaultValue = "",required = false) String startTime,@RequestParam(value = "endTime",defaultValue = "",required = false) String endTime,@RequestParam(value = "sname",defaultValue = "",required = false) String sname,
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
        PageHelper.startPage(pageNum, 8);
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationwithContractByStatusAndSearchContent(4,searchContent);
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        for (RegistrationTable r:registrationTables){
            if (r.getStartTime()!=null&&r.getEndTime()!=null){
                Integer s = tosetProjectStatus(r.getStartTime(),r.getEndTime(),r.getRegistrationId());
                r.setProjectStatus(s);
            }

        }
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        model.addAttribute("registrationTables",registrationTables);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "common_control/consumer_management_contract";
    }

    public Integer tosetProjectStatus(Date stime,Date etime,Integer registrationId){
        Date ntime = new Date(System.currentTimeMillis());
        if (ntime.getTime()<stime.getTime())
            return 1;//代表未开工
        if (ntime.getTime()>=stime.getTime() && ntime.getTime()<=etime.getTime())
            return 2;//代表施工中
        if (ntime.getTime()>etime.getTime()){
            InspectionReport inspectionReport = inspectionReportService.searchInspectionReportByRegistrationId(registrationId);
            if (inspectionReport==null) {
                return 3;//代表施工逾期
            }else{
                if (inspectionReport.getReportStatus()==0)
                    return 4;//代表验收未通过
                if (inspectionReport.getReportStatus()==1)
                    return 5;//代表验收通过
            }
        }
        return 0;//错误状态
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
        return "common_control/project_time";
    }


    protected java.sql.Date getPreDoneScore(java.sql.Date holdDate,Integer i) {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(holdDate);
        calendar.add(calendar.DATE, i);
// calendar的time转成java.util.Date格式日期
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date)calendar.getTime();
//java.util.Date日期转换成转成java.sql.Date格式
        java.sql.Date newDate =new java.sql.Date(utilDate.getTime());
        return newDate;
    }


    @RequestMapping(value = "/addprojectTime", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addprojectTime(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        String projectDay = jsonObject.getString("projectDay");
        Integer registrationId = jsonObject.getInt("registrationId");
        Integer column = 0;
        JSONObject jsonObject1 = new JSONObject();
        if (!projectDay.equals("") && registrationId!=null ){
            ProjectTime projectTime= new ProjectTime();
            projectTime.setProjectDay(Date.valueOf(projectDay));
            projectTime.setRegistrationId(registrationId);
            ProjectTime projectTime1 = projectTimeService.searchProjectTimeByProjectDay(projectTime);
            if (projectTime1==null){
                column = projectTimeService.saveProjectTime(projectTime);
            }else {
                jsonObject1.put("msg","已存在该日期，添加失败！");
                return jsonObject1.toString();
            }
        }

        if (column==0){
            jsonObject1.put("msg","添加失败！");
        }else {
            jsonObject1.put("msg","添加成功！");
        }
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/delprojectTime", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delprojectTime(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        String projectDay = jsonObject.getString("projectDay");
        Integer registrationId = jsonObject.getInt("registrationId");
        System.out.println("输出数据"+projectDay);
        System.out.println("登记"+registrationId);
        Integer column = 0;
        JSONObject jsonObject1 = new JSONObject();
        if (!projectDay.equals("") && registrationId!=null ){
            ProjectTime projectTime= new ProjectTime();
            projectTime.setProjectDay(Date.valueOf(projectDay));
            projectTime.setRegistrationId(registrationId);
            ProjectTime projectTime1 = projectTimeService.searchProjectTimeByProjectDay(projectTime);

            if (projectTime1!=null){
                projectTimeService.dropProgressContentByProjectId(projectTime1.getProjectId());
                column = projectTimeService.dropProjectTime(projectTime1.getProjectId());
            }else {
                jsonObject1.put("msg","该日期不存在，删除失败！");
                return jsonObject1.toString();
            }
        }

        if (column==0){
            jsonObject1.put("msg","删除失败！");
        }else {
            jsonObject1.put("msg","删除成功！");
        }
        return jsonObject1.toString();
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
        return "common_control/progress_content";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String msg="";
        Date date = new Date(System.currentTimeMillis());
        String dateset = date.toString();
        File filepath = new File("D:\\ProjectUpload\\ProjectTime\\"+dateset+"\\");
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.exists()) {
            filepath.mkdirs();

        }
        System.out.println("上传路径："+filepath.getParentFile());
        List<String> urls = new ArrayList<String>();
        for (int i=0;i<files.length;i++){
            // 上传文件名
            String filename = files[i].getOriginalFilename();
            System.out.println("图片原始名字："+filename);
            String newFilename = UUID.randomUUID()
                    +filename.substring(filename
                    .lastIndexOf("."));
            String url = "";
            try {
                files[i].transferTo(
                        new File("D:\\ProjectUpload\\ProjectTime\\"+dateset+File.separator+newFilename)
                );
                url="/decorationsystem/upload/ProjectTime/"+dateset+"/"+newFilename;
                urls.add(url);
            } catch (IOException e) {
                e.printStackTrace();
                url="";
                msg="文件储存错误";
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\ProjectTime\\"+dateset));
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg",msg);
                return jsonObject1.toString();
            }
        }

        JSONObject jsonObject = new JSONObject();
        System.out.println("访问路径为："+msg);
        jsonObject.put("urls",urls);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/addprogress", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addprogress( @RequestBody ProgressContent progressContent, HttpSession session){
        System.out.println(progressContent);
        JSONObject jsonObject = new JSONObject();
        if (progressContent.getProgressTitle().trim().equals("")){
            jsonObject.put("msg","标题不能为空");
            return jsonObject.toString();
        }
        if (progressContent.getProgressContent().trim().equals("<p><br></p>")){
            jsonObject.put("msg","内容不能为空");
            return jsonObject.toString();
        }
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            jsonObject.put("msg","登录过期，请重新登录！");
            return jsonObject.toString();
        }
        progressContent.setEmployee(employee);
        progressContent.setUploadTime(new java.util.Date());

        Integer column = projectTimeService.saveProgressContent(progressContent);
        if (column == 0){
            jsonObject.put("msg","添加失败！");
        }else{
            jsonObject.put("msg","添加成功！");
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/delprogress", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delprogress(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONObject jsonObject1 = new JSONObject();
        Integer progressId = jsonObject.getInt("progressId");
        System.out.println(progressId);
        if (projectTimeService.getProgressContentVersionByProgressId(progressId)!=null){
            Integer column = projectTimeService.dropProgressContentByProgressContentId(progressId);;
            if (column==0){
                jsonObject1.put("msg","删除失败！");
                return jsonObject1.toString();
            }else{
                jsonObject1.put("msg","删除成功！");
                return jsonObject1.toString();
            }
        }else {
            jsonObject1.put("msg","数据已被人删除");
            return jsonObject1.toString();
        }
    }
    @RequestMapping(value = "/getprogress", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getprogress(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONObject jsonObject1 = new JSONObject();
        Integer progressId = jsonObject.getInt("progressId");
        System.out.println(progressId);
        if (projectTimeService.getProgressContentVersionByProgressId(progressId)!=null){
           ProgressContent progressContent = projectTimeService.searchProgressContentByProgressId(progressId);;
            jsonObject1.put("progressContent",progressContent);
            return jsonObject1.toString();
        }else {
            jsonObject1.put("msg","数据已被人删除");
            return jsonObject1.toString();
        }
    }

    @RequestMapping(value = "/updateprogress", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateprogress( @RequestBody ProgressContent progressContent, HttpSession session){
        System.out.println(progressContent);
        JSONObject jsonObject = new JSONObject();
        if (progressContent.getProgressTitle().trim().equals("")){
            jsonObject.put("msg","标题不能为空");
            return jsonObject.toString();
        }
        if (progressContent.getProgressContent().trim().equals("<p><br></p>")){
            jsonObject.put("msg","内容不能为空");
            return jsonObject.toString();
        }
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            jsonObject.put("msg","登录过期，请重新登录！");
            return jsonObject.toString();
        }
        progressContent.setEmployee(employee);
        progressContent.setUploadTime(new java.util.Date());

        if (progressContent.getVersion()>=projectTimeService.getProgressContentVersionByProgressId(progressContent.getProgressId())){
            progressContent.setVersion(progressContent.getVersion()+1);
            Integer column = projectTimeService.changeProgressContent(progressContent);
            if (column == 0){
                jsonObject.put("msg","更新失败！");
                return jsonObject.toString();
            }else{
                jsonObject.put("msg","更新成功！");
                return jsonObject.toString();

            }
        }else {
            jsonObject.put("msg","该数据已被更新，请刷新！");
            return jsonObject.toString();

        }

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
        return "common_control/inspection_report";
    }

    @RequestMapping(value = "/saveInspectionReport", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveInspectionReport(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        Integer registrationId =Integer.parseInt(request.getParameter("registrationId"));
        Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Integer reportStatus =Integer.parseInt( request.getParameter("reportStatus"));
        if (files.length>20){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","上传文件不能超过20个");
            return jsonObject1.toString();
        }
        String msg="";
        InspectionReport inspectionReport = inspectionReportService.searchInspectionReportByRegistrationId(registrationId);
        if (inspectionReport==null ){
                InspectionReport saveinpectionReport = new InspectionReport();
                if (registrationId!=null &&  employeeId!=null&&reportStatus!=null){
                    saveinpectionReport.setUploadTime(new java.util.Date());
                    saveinpectionReport.setRegistrationId(registrationId);
                    saveinpectionReport.setReportStatus(reportStatus);
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeId);
                    saveinpectionReport.setEmployee(employee);
                }else {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg","信息不完整！");
                    return jsonObject1.toString();
                }

                List<InspectionReportPic> inspectionReportPics = new ArrayList<InspectionReportPic>();
                Integer resultColumn = inspectionReportService.saveInspectionReport(saveinpectionReport);

                if (resultColumn!=0){
                    File filepath = new File("D:\\ProjectUpload\\inspection_report\\"+saveinpectionReport.getRegistrationId());
                    // 判断路径是否存在，如果不存在就创建一个
                    if (!filepath.exists()) {
                        filepath.mkdirs();

                    }
                    System.out.println("上传路径："+filepath.getParentFile());
                    for (int i=0;i<files.length;i++){
                        // 上传文件名
                        String filename = files[i].getOriginalFilename();
                        InspectionReportPic inspectionReportPic = new InspectionReportPic();
                        inspectionReportPic.setFileName(filename);
                        inspectionReportPic.setReportId(saveinpectionReport.getReportId());
                        String newFilename = UUID.randomUUID()
                                +filename.substring(filename
                                .lastIndexOf("."));
                        String url = "";
                        try {
                            files[i].transferTo(
                                    new File("D:\\ProjectUpload\\inspection_report\\"+saveinpectionReport.getRegistrationId()+File.separator+newFilename)
                            );
                            url="/decorationsystem/upload/inspection_report/"+saveinpectionReport.getRegistrationId()+"/"+newFilename;
                            inspectionReportPic.setFileUrl(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            url="";
                            msg="文件储存错误";
                            FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\inspection_report\\"+saveinpectionReport.getRegistrationId()));
                            Integer column = inspectionReportService.dropInspectionReportPicByReportId(saveinpectionReport.getReportId());
                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("msg",msg);
                            return jsonObject1.toString();
                        }
                        inspectionReportPics.add(inspectionReportPic);
                    }
                }else{
                    msg="验收报告上传失败！";
                }
                Integer uploadcout =inspectionReportService.saveInspectionReportPic(inspectionReportPics);
                msg = "上传"+files.length+"文件,成功上传"+uploadcout+"个文件";

        }else{
            msg="已存在验收报告，请先删除!";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }


    @RequestMapping(value = "/changeInspectionReport", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeInspectionReport(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        Integer registrationId =Integer.parseInt(request.getParameter("registrationId"));
        Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Integer reportStatus =Integer.parseInt( request.getParameter("reportStatus"));
        Integer version =Integer.parseInt( request.getParameter("version"));
        if (files.length>20){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","上传文件不能超过20个");
            return jsonObject1.toString();
        }
        String msg="";
        InspectionReport inspectionReport = inspectionReportService.searchInspectionReportByRegistrationId(registrationId);
        if (inspectionReport!=null ){
            InspectionReport saveinpectionReport = new InspectionReport();
            if (registrationId!=null &&  employeeId!=null&&reportStatus!=null&&version!=null ){
                saveinpectionReport.setUploadTime(new java.util.Date());
                saveinpectionReport.setRegistrationId(registrationId);
                saveinpectionReport.setReportStatus(reportStatus);
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                saveinpectionReport.setEmployee(employee);
            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","信息不完整！");
                return jsonObject1.toString();
            }

            List<InspectionReportPic> inspectionReportPics = new ArrayList<InspectionReportPic>();
            Integer resultColumn=0;
            if (version>=inspectionReportService.getInspectionReportVersionByRegistrationId(registrationId)){
                saveinpectionReport.setVersion(version+1);
                resultColumn = inspectionReportService.changeInspectionReport(saveinpectionReport);
            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","验收报告已被修改！");
                return jsonObject1.toString();
            }
            FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\inspection_report\\" + inspectionReport.getRegistrationId()));
            inspectionReportService.dropInspectionReportPicByReportId(inspectionReport.getReportId());
            if (resultColumn!=0){
                File filepath = new File("D:\\ProjectUpload\\inspection_report\\"+saveinpectionReport.getRegistrationId());
                // 判断路径是否存在，如果不存在就创建一个
                if (!filepath.exists()) {
                    filepath.mkdirs();

                }
                System.out.println("上传路径："+filepath.getParentFile());
                for (int i=0;i<files.length;i++){
                    // 上传文件名
                    String filename = files[i].getOriginalFilename();
                    InspectionReportPic inspectionReportPic = new InspectionReportPic();
                    inspectionReportPic.setFileName(filename);
                    inspectionReportPic.setReportId(inspectionReport.getReportId());
                    String newFilename = UUID.randomUUID()
                            +filename.substring(filename
                            .lastIndexOf("."));
                    String url = "";
                    try {
                        files[i].transferTo(
                                new File("D:\\ProjectUpload\\inspection_report\\"+saveinpectionReport.getRegistrationId()+File.separator+newFilename)
                        );
                        url="/decorationsystem/upload/inspection_report/"+saveinpectionReport.getRegistrationId()+"/"+newFilename;
                        inspectionReportPic.setFileUrl(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        url="";
                        msg="文件储存错误";
                        FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\inspection_report\\"+saveinpectionReport.getRegistrationId()));
                        Integer column = inspectionReportService.dropInspectionReportPicByReportId(saveinpectionReport.getReportId());
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("msg",msg);
                        return jsonObject1.toString();
                    }
                    inspectionReportPics.add(inspectionReportPic);
                }
            }else{
                msg="验收报告上传失败！";
            }
            Integer uploadcout =inspectionReportService.saveInspectionReportPic(inspectionReportPics);
            msg = "上传"+files.length+"文件,成功上传"+uploadcout+"个文件";

        }else{
            msg="验收报告不存在！";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/delInspectionReport",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delContract(@RequestBody String str) throws IOException {
        JSONObject jsonObject1= JSONObject.fromObject(str);
        Integer reportId = jsonObject1.getInt("reportId");
        Integer registrationId = jsonObject1.getInt("registrationId");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer versionsql = inspectionReportService.getInspectionReportVersionByRegistrationId(registrationId);
        if (versionsql!=null){

            if (reportId!=null){
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\inspection_report\\"+registrationId));
                inspectionReportService.dropInspectionReportPicByReportId(reportId);
                inspectionReportService.dropInspectionReport(registrationId);
                msg="1";
            }else {
                msg = "2";
            }
        }else {
            msg="3";
        }
        jsonObject2.put("result",msg);
        return jsonObject2.toString();
    }

    @RequestMapping(value = "/saveGuarantee",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveGuarantee(@Valid @RequestBody Guarantee guarantee,Errors errors) {
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
        Integer coulmn = guaranteeService.saveGuarantee(guarantee);
        if (coulmn==0){
            jsonObject.put("msg","添加失败！");
        }else{
            registrationTableService.changStatusByRegistrationId(guarantee.getRegistrationId(),5);
            jsonObject.put("msg","添加成功！客户已成为完工客户");
        }
        return jsonObject.toString();
    }

}
