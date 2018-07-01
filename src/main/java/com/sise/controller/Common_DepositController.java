package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.*;
import com.sise.service.*;
import com.sise.util.MyAuthUtils;
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
 * @Date:Create in 20:04 2018/2/23
 */
@Controller
@RequestMapping("/common_deposit")
public class Common_DepositController {

    @Autowired
    private RegistrationTableService registrationTableService;


    @Autowired
    private ConsumerService consumerService;

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

    @Autowired
    private DecorationModeService decorationModeService;

    @Autowired
    private ContractService contractService;


    @RequestMapping(value = "/consumer_management_deposit",method = RequestMethod.GET)
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
        System.out.println(searchContent.toString());
        PageHelper.startPage(pageNum, 8);
        List<RegistrationTable> registrationTables =registrationTableService.searchRegistrationWithProjectStatusByStatusAndSearchContent(3,searchContent);
        PageInfo<RegistrationTable> pageInfo = new PageInfo<RegistrationTable>(registrationTables);
        List<Province> provinces = consumerService.searchAllProvince();
        model.addAttribute("provinces",provinces);
        model.addAttribute("registrationTables",registrationTables);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchContent",searchContent);
        return "common_control/consumer_management_deposit";

    }


    @RequestMapping("/housepic")
    public String housepic(@RequestParam("registrationId") Integer registrationId,@RequestParam("employeeId") Integer employeeId, Model model){
        model.addAttribute("registrationId",registrationId);
        model.addAttribute("employeeId",employeeId);
        List<AreaType> areaTypes = areaTypeService.findAllAreaType();
        List<HouseType> houseTypes = houseTypeService.findAllHousetype();
        Housepic housepic = housepicService.findHousepicByRegistrationId(registrationId);
        model.addAttribute("areaTypes",areaTypes);
        model.addAttribute("houseTypes",houseTypes);
        model.addAttribute("housepic",housepic);
        if (housepic==null){
            model.addAttribute("resultStatus","false");
        }else {
            List<HousepicFile> housepicFiles = housepicService.findHousepicFileByHousepicId(housepic.getHousepicId());
            model.addAttribute("housepicFiles",housepicFiles);
            model.addAttribute("resultStatus","true");
        }
        return "common_control/housepic";
    }

    @RequestMapping(value = "/saveHousepic", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveHousepic(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String housepicName =request.getParameter("housepicName");
        String housepicDescrible = request.getParameter("housepicDescrible");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer houseId = Integer.parseInt(request.getParameter("houseId"));
        Integer areaId =  Integer.parseInt(request.getParameter("areaId"));
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date depositTime = sdf.parse(time);*/
        String path = request.getSession().getServletContext().getRealPath(
                "/upload/");
        if (files.length>20){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","上传文件不能超过20个");
            return jsonObject1.toString();
        }
        Housepic housepic1 = housepicService.findHousepicByRegistrationId(registrationId);
        String msg="";
        System.out.println(housepic1);
        if (housepic1==null){
            Housepic housepic = new Housepic();
            if (!housepicName.trim().equals("") &&  registrationId!=null&&houseId!=null&areaId!=null){
                housepic.setStatus(1);
                housepic.setHousepicName(housepicName);
                housepic.setHousepicDescrible(housepicDescrible);
                housepic.setRegistrationId(registrationId);
                HouseType houseType = new HouseType();
                houseType.setHouseId(houseId);
                housepic.setHouseType(houseType);
                AreaType areaType = new AreaType();
                areaType.setAreaId(areaId);
                housepic.setAreaType(areaType);
                housepic.setUploadTime(new java.util.Date());

            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","填写信息除户型描述外，其它不能为空");
                return jsonObject1.toString();
            }

            List<HousepicFile> housepicFiles = new ArrayList<HousepicFile>();
            Integer resultColumn = housepicService.addHousepic(housepic);

            if (resultColumn!=0){
                Housepic housepic2 = housepicService.findHousepicByRegistrationId(registrationId);
                File filepath = new File("D:\\ProjectUpload\\housepic\\"+housepic2.getRegistrationId());
                // 判断路径是否存在，如果不存在就创建一个
                if (!filepath.exists()) {
                    filepath.mkdirs();

                }
                System.out.println("上传路径："+filepath.getParentFile());
                for (int i=0;i<files.length;i++){
                    // 上传文件名
                    String filename = files[i].getOriginalFilename();
                    HousepicFile housepicFile =new HousepicFile();
                    housepicFile.setFileName(filename);
                    housepicFile.setHousepicId(housepic2.getHousepicId());
                    String newFilename = UUID.randomUUID()
                            +filename.substring(filename
                            .lastIndexOf("."));
                    String url = "";
                    try {
                        files[i].transferTo(
                                new File("D:\\ProjectUpload\\housepic\\"+housepic2.getRegistrationId()+File.separator+newFilename)
                        );
                        url="/decorationsystem/upload/housepic/"+housepic2.getRegistrationId()+"/"+newFilename;
                        housepicFile.setFileUrl(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        url="";
                        msg="文件储存错误";
                        FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\housepic\\"+housepic2.getRegistrationId()));
                        Integer column = housepicService.dripHousepicById(housepic2.getHousepicId());
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("msg",msg);
                        return jsonObject1.toString();
                    }
                    System.out.println(housepicFile.toString());
                    housepicFiles.add(housepicFile);
                }
            }else{
                msg="户型图资料上传失败！";
            }
            Integer uploadcout =housepicService.saveHousepicFile(housepicFiles);
            msg = "上传"+files.length+"文件,成功上传"+uploadcout+"个文件";

        }else {
            msg = "已存在户型图资料，请先删除！";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/changeHousepic", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeHousepic(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String housepicName =request.getParameter("housepicName");
        String housepicDescrible = request.getParameter("housepicDescrible");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer housepicId = Integer.parseInt(request.getParameter("housepicId"));
        Integer version = Integer.parseInt(request.getParameter("version"));
        Integer houseId = Integer.parseInt(request.getParameter("houseId"));
        Integer areaId =  Integer.parseInt(request.getParameter("areaId"));
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date depositTime = sdf.parse(time);*/
        Housepic housepic1 = housepicService.findHousepicByRegistrationId(registrationId);
        String msg="";
        System.out.println(housepic1);
        if (housepic1!=null){
            Housepic housepic = new Housepic();
            if (!housepicName.trim().equals("") &&  registrationId!=null&&houseId!=null&areaId!=null&version!=null){
                housepic.setStatus(1);
                housepic.setHousepicName(housepicName);
                housepic.setHousepicDescrible(housepicDescrible);
                housepic.setRegistrationId(registrationId);
                HouseType houseType = new HouseType();
                houseType.setHouseId(houseId);
                housepic.setHouseType(houseType);
                AreaType areaType = new AreaType();
                areaType.setAreaId(areaId);
                housepic.setAreaType(areaType);
                housepic.setUploadTime(new java.util.Date());
                housepic.setVersion(version);
                housepic.setHousepicId(housepicId);

            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","填写信息除户型描述外，其它不能为空");
                return jsonObject1.toString();
            }

            List<HousepicFile> housepicFiles = new ArrayList<HousepicFile>();
            System.out.println("更新资料："+housepic.toString());
            Integer resultColumn =0;
            System.out.println("当前版本号："+version);
            System.out.println("数据库版本号："+housepicService.searchVersionById(housepicId));
            if (version>=housepicService.searchVersionById(housepicId)){
                housepic.setVersion(version+1);
                resultColumn = housepicService.changeHousepic(housepic);
            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","该户型图资料已被更新，请重新刷新！");
                return jsonObject1.toString();
            }
            FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\housepic\\"+housepic.getRegistrationId()));
            housepicService.dropAllHousepicFileByHouseId(housepicId);
            if (resultColumn!=0){
                File filepath = new File("D:\\ProjectUpload\\housepic\\"+housepic.getRegistrationId());
                // 判断路径是否存在，如果不存在就创建一个
                if (!filepath.exists()) {
                    filepath.mkdirs();

                }
                System.out.println("上传路径："+filepath.getParentFile());
                for (int i=0;i<files.length;i++){
                    // 上传文件名
                    String filename = files[i].getOriginalFilename();
                    System.out.println("原始文件名为："+filename);
                    HousepicFile housepicFile =new HousepicFile();
                    housepicFile.setFileName(filename);
                    housepicFile.setHousepicId(housepic.getHousepicId());
                    String newFilename = UUID.randomUUID()
                            +filename.substring(filename
                            .lastIndexOf("."));
                    String url="/decorationsystem/upload/housepic/"+housepic.getRegistrationId()+"/"+newFilename;
                    housepicFile.setFileUrl(url);
                    try {
                        files[i].transferTo(
                                new File("D:\\ProjectUpload\\housepic\\"+housepic.getRegistrationId()+File.separator+newFilename)
                        );

                    } catch (IOException e) {
                        e.printStackTrace();
                        url="";
                        msg="文件储存错误";
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("msg",msg);
                        return jsonObject1.toString();
                    }
                    System.out.println(housepicFile.toString());
                    housepicFiles.add(housepicFile);
                }
            }else{
                msg="户型图资料上传失败！";
            }
            Integer uploadcout =housepicService.saveHousepicFile(housepicFiles);
            msg = "上传"+files.length+"文件,成功上传"+uploadcout+"个文件";

        }else {
            msg = "请先上传户型图";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/delHousepic",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delHousepic(@RequestBody String str) throws IOException {
        JSONObject jsonObject1= JSONObject.fromObject(str);
        Integer housepicId = jsonObject1.getInt("housepicId");
        Integer registrationId = jsonObject1.getInt("registrationId");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer versionsql = housepicService.searchVersionById(housepicId);
        if (versionsql!=null){

            if (housepicId!=null){
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\housepic\\"+registrationId));
                housepicService.dropAllHousepicFileByHouseId(housepicId);
                housepicService.dripHousepicById(housepicId);
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

    @RequestMapping("/designerpic")
    public String designerpic(@RequestParam("registrationId") Integer registrationId,@RequestParam("employeeId") Integer employeeId, Model model){
        model.addAttribute("registrationId",registrationId);
        model.addAttribute("employeeId",employeeId);
        List<AreaType> areaTypes = areaTypeService.findAllAreaType();
        List<HouseType> houseTypes = houseTypeService.findAllHousetype();
        List<DecorationStyle> decorationStyles = decorationStyleService.searchAllDecorationStyle();
        Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
        model.addAttribute("areaTypes",areaTypes);
        model.addAttribute("houseTypes",houseTypes);
        model.addAttribute("decorationStyles",decorationStyles);
        model.addAttribute("designerpic",designerpic);
        if (designerpic==null){
            model.addAttribute("resultStatus","false");
        }else {
            List<DesignerpicFile> designerpicFiles = designerpicService.searchAllDesignerpicFileByDesignerId(designerpic.getDesignerpicId());
            model.addAttribute("designerpicFiles",designerpicFiles);
            model.addAttribute("resultStatus","true");
        }
        return "common_control/designerpic";
    }

    @RequestMapping(value = "/saveDesignerpic", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveDesignerpic(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String designerpicName =request.getParameter("designerpicName");
        String designerpicDescribe = request.getParameter("designerpicDescribe");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer houseId = Integer.parseInt(request.getParameter("houseId"));
        Integer areaId =  Integer.parseInt(request.getParameter("areaId"));
        Integer styleId =  Integer.parseInt(request.getParameter("styleId"));
        BigDecimal designerpicMoney =new BigDecimal( request.getParameter("designerpicMoney"));
        designerpicMoney = designerpicMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date depositTime = sdf.parse(time);*/

        if (files.length>20){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","上传文件不能超过20个");
            return jsonObject1.toString();
        }
        String msg="";
        Housepic housepic = housepicService.findHousepicByRegistrationId(registrationId);
        if (housepic!=null){
        Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);

        System.out.println(designerpic);
        if (designerpic==null){
            Designerpic savedesignerpic = new Designerpic();
            if (!designerpicName.trim().equals("") &&  registrationId!=null&&houseId!=null&areaId!=null&&styleId!=null&&designerpicMoney!=null){
               savedesignerpic.setAreaType(new AreaType(areaId));
               savedesignerpic.setDesignerpicMoney(designerpicMoney);
               savedesignerpic.setDecorationStyle(new DecorationStyle(styleId));
               savedesignerpic.setHouseType(new HouseType(houseId));
               savedesignerpic.setDesignerpicDescribe(designerpicDescribe);
               savedesignerpic.setDesignerpicName(designerpicName);
               savedesignerpic.setRegistrationId(registrationId);
               savedesignerpic.setUploadTime(new java.util.Date());
            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","填写信息除设计图描述外，其它不能为空");
                return jsonObject1.toString();
            }

            List<DesignerpicFile> designerpicFiles = new ArrayList<DesignerpicFile>();
            Integer resultColumn = designerpicService.saveDesigner(savedesignerpic);

            if (resultColumn!=0){
                Designerpic designerpicget = designerpicService.searchDesignerpicByRegistrationId(registrationId);
                File filepath = new File("D:\\ProjectUpload\\designerpic\\"+designerpicget.getRegistrationId());
                // 判断路径是否存在，如果不存在就创建一个
                if (!filepath.exists()) {
                    filepath.mkdirs();

                }
                System.out.println("上传路径："+filepath.getParentFile());
                for (int i=0;i<files.length;i++){
                    // 上传文件名
                    String filename = files[i].getOriginalFilename();
                    DesignerpicFile designerpicFile = new DesignerpicFile();
                    designerpicFile.setFileName(filename);
                    designerpicFile.setDesignerpicId(designerpicget.getDesignerpicId());
                    String newFilename = UUID.randomUUID()
                            +filename.substring(filename
                            .lastIndexOf("."));
                    String url = "";
                    try {
                        files[i].transferTo(
                                new File("D:\\ProjectUpload\\designerpic\\"+designerpicget.getRegistrationId()+File.separator+newFilename)
                        );
                        url="/decorationsystem/upload/designerpic/"+designerpicget.getRegistrationId()+"/"+newFilename;
                        designerpicFile.setFileUrl(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        url="";
                        msg="文件储存错误";
                        FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\designerpic\\"+designerpicget.getRegistrationId()));
                        Integer column = designerpicService.dropDesignerpicByDesignerpicId(designerpicget.getDesignerpicId());
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("msg",msg);
                        return jsonObject1.toString();
                    }
                    System.out.println(designerpicFile.toString());
                    designerpicFiles.add(designerpicFile);
                }
            }else{
                msg="设计图资料上传失败！";
            }
            Integer uploadcout =designerpicService.saveDesignerpicFile(designerpicFiles);
            msg = "上传"+files.length+"文件,成功上传"+uploadcout+"个文件";

        }else {
            msg = "已存在设计图资料，请先删除！";
        }
        }else{
            msg="请先上传户型图!";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/changeDesignerpic", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeDesignerpic(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String designerpicName =request.getParameter("designerpicName");
        String designerpicDescribe = request.getParameter("designerpicDescribe");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer houseId = Integer.parseInt(request.getParameter("houseId"));
        Integer areaId =  Integer.parseInt(request.getParameter("areaId"));
        Integer styleId =  Integer.parseInt(request.getParameter("styleId"));
        BigDecimal designerpicMoney =new BigDecimal( request.getParameter("designerpicMoney"));
        designerpicMoney = designerpicMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        Integer version = Integer.parseInt(request.getParameter("version"));
        Integer designerpicId =  Integer.parseInt(request.getParameter("designerpicId"));
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date depositTime = sdf.parse(time);*/
        Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
        String msg = "";
        System.out.println(designerpic);
        if (designerpic!=null){
            Designerpic savedesignerpic = new Designerpic();
            if (!designerpicName.trim().equals("") &&  registrationId!=null&&houseId!=null&areaId!=null&&styleId!=null&&designerpicMoney!=null){
                savedesignerpic.setAreaType(new AreaType(areaId));
                savedesignerpic.setDesignerpicMoney(designerpicMoney);
                savedesignerpic.setDecorationStyle(new DecorationStyle(styleId));
                savedesignerpic.setHouseType(new HouseType(houseId));
                savedesignerpic.setDesignerpicDescribe(designerpicDescribe);
                savedesignerpic.setDesignerpicName(designerpicName);
                savedesignerpic.setRegistrationId(registrationId);
                savedesignerpic.setUploadTime(new java.util.Date());
                savedesignerpic.setDesignerpicId(designerpicId);
                savedesignerpic.setStatus(1);
            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","填写信息除设计图描述外，其它不能为空");
                return jsonObject1.toString();
            }

            List<DesignerpicFile> designerpicFiles = new ArrayList<DesignerpicFile>();
            Integer resultColumn =0;
            if (version>=designerpicService.searchVersionByDesignerpicId(designerpicId)){
                savedesignerpic.setVersion(version+1);
                resultColumn = designerpicService.changeDesignerpicByDesignerpicId(savedesignerpic);
            }else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","该设计图资料已被更新，请重新刷新！");
                return jsonObject1.toString();
            }
            FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\designerpic\\"+savedesignerpic.getRegistrationId()));
            designerpicService.dropDesignerpicFileByDesignerpicId(savedesignerpic.getDesignerpicId());
            if (resultColumn!=0){
                File filepath = new File("D:\\ProjectUpload\\designerpic\\"+savedesignerpic.getRegistrationId());
                // 判断路径是否存在，如果不存在就创建一个
                if (!filepath.exists()) {
                    filepath.mkdirs();

                }
                for (int i=0;i<files.length;i++){
                    // 上传文件名
                    String filename = files[i].getOriginalFilename();
                    DesignerpicFile designerpicFile =new DesignerpicFile();
                    designerpicFile.setFileName(filename);
                    designerpic.setDesignerpicId(savedesignerpic.getDesignerpicId());
                    String newFilename = UUID.randomUUID()
                            +filename.substring(filename
                            .lastIndexOf("."));
                    String url="/decorationsystem/upload/designerpic/"+savedesignerpic.getRegistrationId()+"/"+newFilename;
                    designerpicFile.setFileUrl(url);
                    designerpicFile.setDesignerpicId(savedesignerpic.getDesignerpicId());
                    try {
                        files[i].transferTo(
                                new File("D:\\ProjectUpload\\designerpic\\"+savedesignerpic.getRegistrationId()+File.separator+newFilename)
                        );

                    } catch (IOException e) {
                        e.printStackTrace();
                        url="";
                        msg="文件储存错误";
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("msg",msg);
                        return jsonObject1.toString();
                    }
                    System.out.println(designerpicFile.toString());
                    designerpicFiles.add(designerpicFile);
                }
            }else{
                msg="设计图资料上传失败！";
            }
            Integer uploadcout =designerpicService.saveDesignerpicFile(designerpicFiles);
            msg = "上传"+files.length+"文件,成功上传"+uploadcout+"个文件";

        }else {
            msg = "请先上传设计图";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/delDesignerpic",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delDesignerpic(@RequestBody String str) throws IOException {
        JSONObject jsonObject1= JSONObject.fromObject(str);
        Integer designerpicId = jsonObject1.getInt("designerpicId");
        Integer registrationId = jsonObject1.getInt("registrationId");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer versionsql = designerpicService.searchVersionByDesignerpicId(designerpicId);
        if (versionsql!=null){

            if (designerpicId!=null){
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\designerpic\\"+registrationId));
                designerpicService.dropDesignerpicFileByDesignerpicId(designerpicId);
                designerpicService.dropDesignerpicByDesignerpicId(designerpicId);
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

    /*@RequestMapping(value = "/addFileDescribe",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addFileDescribe(@RequestBody String str)  {
        JSONObject jsonObject1= JSONObject.fromObject(str);
        Integer designerpicId = jsonObject1.getInt("designerpicId");
        Gson gson = new Gson();
        List<DesignerpicFile> designerpicFiles = designerpicService.searchAllDesignerpicFileByDesignerId(designerpicId);
        gson.toJson(designerpicFiles);
        return gson.toString();
    }*/

    @RequestMapping("/contract_approval")
    public String contract_approval(@RequestParam("registrationId") Integer registrationId,@RequestParam("employeeId") Integer employeeId, Model model){
        model.addAttribute("registrationId",registrationId);
        model.addAttribute("employeeId",employeeId);
        List<DecorationMode> decorationModes = decorationModeService.searchAllDecorationMode();
        model.addAttribute("decorationModes",decorationModes);
        Contract contract = contractService.findContractByRegistrationId(registrationId);
        model.addAttribute("contract",contract);
        if (contract==null){
            model.addAttribute("resultStatus","false");
        }else {
            List<ContractFile> contractFiles = contractService.findContractFileByContractId(contract.getContractId());

            model.addAttribute("contractFiles",contractFiles);
            model.addAttribute("resultStatus","true");
        }
        return "common_control/contract_approval";
    }

    @RequestMapping(value = "/saveContract", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveContract(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String contractName =request.getParameter("contractName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer modeId = Integer.parseInt(request.getParameter("modeId"));
        String contractDesribe = request.getParameter("contractDesribe");
        BigDecimal contractMoney =new BigDecimal( request.getParameter("contractMoney"));
        contractMoney = contractMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        Date sTime = Date.valueOf(startTime);
        Date eTime = Date.valueOf(endTime);
        if (eTime.getTime()<sTime.getTime()){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","开工日期不能大于竣工日期！");
            return jsonObject1.toString();
        }
        if (files.length>20){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","上传文件不能超过20个");
            return jsonObject1.toString();
        }
        String msg="";
        Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
        if (designerpic!=null ){
            Contract contract = contractService.findContractByRegistrationId(registrationId);
            if (contract==null){
                Contract savecontract = new Contract();
                if (!contractName.trim().equals("") &&  registrationId!=null&&modeId!=null&startTime!=null&&endTime!=null&&contractMoney!=null){
                    savecontract.setContractDescribe(contractDesribe);
                    savecontract.setContractMoney(contractMoney);
                    savecontract.setContractName(contractName);
                    savecontract.setEndTime(eTime);
                    savecontract.setStartTime(sTime);
                    savecontract.setRegistrationId(registrationId);
                    savecontract.setUploadTime(new java.util.Date());
                    DecorationMode decorationMode = new DecorationMode(modeId);
                    savecontract.setDecorationMode(decorationMode);

                }else {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg","填写信息除合同描述外，其它不能为空");
                    return jsonObject1.toString();
                }

                List<ContractFile> contractFiles = new ArrayList<ContractFile>();
                Integer resultColumn = contractService.saveContract(savecontract);

                if (resultColumn!=0){
                    Contract contractget = contractService.findContractByRegistrationId(registrationId);
                    File filepath = new File("D:\\ProjectUpload\\contract\\"+contractget.getRegistrationId());
                    // 判断路径是否存在，如果不存在就创建一个
                    if (!filepath.exists()) {
                        filepath.mkdirs();

                    }
                    System.out.println("上传路径："+filepath.getParentFile());
                    for (int i=0;i<files.length;i++){
                        // 上传文件名
                        String filename = files[i].getOriginalFilename();
                        ContractFile contractFile = new ContractFile();
                        contractFile.setFileName(filename);
                        contractFile.setContractId(contractget.getContractId());
                        String newFilename = UUID.randomUUID()
                                +filename.substring(filename
                                .lastIndexOf("."));
                        String url = "";
                        try {
                            files[i].transferTo(
                                    new File("D:\\ProjectUpload\\contract\\"+contractget.getRegistrationId()+File.separator+newFilename)
                            );
                            url="/decorationsystem/upload/contract/"+contractget.getRegistrationId()+"/"+newFilename;
                            contractFile.setFileUrl(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            url="";
                            msg="文件储存错误";
                            FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\contract\\"+contractget.getRegistrationId()));
                            Integer column = contractService.dropContractById(contractget.getContractId());
                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("msg",msg);
                            return jsonObject1.toString();
                        }
                        System.out.println(contractFile.toString());
                        contractFiles.add(contractFile);
                    }
                }else{
                    msg="合同资料上传失败！";
                }
                Integer uploadcout =contractService.saveContractFile(contractFiles);
                registrationTableService.changStatusByRegistrationId(registrationId,4);
                msg = "上传" + files.length + "文件,成功上传" + uploadcout + "个文件,客户已成为合同客户";

            }else {
                msg = "已存在合同资料，请先删除！";
            }
        }else{
            msg="请先上传设计图!";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/changeContract", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changeContract(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String contractName =request.getParameter("contractName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer modeId = Integer.parseInt(request.getParameter("modeId"));
        Integer version = Integer.parseInt(request.getParameter("version"));
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        String contractDesribe = request.getParameter("contractDesribe");
        BigDecimal contractMoney =new BigDecimal( request.getParameter("contractMoney"));
        contractMoney = contractMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        Date sTime = Date.valueOf(startTime);
        Date eTime = Date.valueOf(endTime);
        if (eTime.getTime()<sTime.getTime()){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","开工日期不能大于竣工日期！");
            return jsonObject1.toString();
        }
        if (files.length>20){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","上传文件不能超过20个");
            return jsonObject1.toString();
        }
        String msg="";
        Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
        if (designerpic!=null){
            Contract contract = contractService.findContractByRegistrationId(registrationId);
            if (contract!=null) {
                Contract savecontract = new Contract();
                if (!contractName.trim().equals("") && registrationId != null && modeId != null & startTime != null && endTime != null && contractMoney != null) {
                    savecontract.setContractDescribe(contractDesribe);
                    savecontract.setContractMoney(contractMoney);
                    savecontract.setContractName(contractName);
                    savecontract.setEndTime(eTime);
                    savecontract.setStartTime(sTime);
                    savecontract.setRegistrationId(registrationId);
                    savecontract.setContractId(contractId);
                    savecontract.setUploadTime(new java.util.Date());
                    DecorationMode decorationMode = new DecorationMode(modeId);
                    savecontract.setDecorationMode(decorationMode);
                    savecontract.setStatus(1);

                } else {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg", "填写信息除合同描述外，其它不能为空");
                    return jsonObject1.toString();
                }

                List<ContractFile> contractFiles = new ArrayList<ContractFile>();
                Integer resultColumn = 0;
                if (version >= contractService.searchVersionById(contractId)) {
                    savecontract.setVersion(version + 1);
                    resultColumn = contractService.changeContract(savecontract);
                } else {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg", "该合同资料已被更新，请重新刷新！");
                    return jsonObject1.toString();
                }
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\contract\\" + savecontract.getRegistrationId()));
                contractService.dropAllContractFileByContractId(savecontract.getContractId());
                if (resultColumn != 0) {
                    File filepath = new File("D:\\ProjectUpload\\contract\\" + savecontract.getRegistrationId());
                    // 判断路径是否存在，如果不存在就创建一个
                    if (!filepath.exists()) {
                        filepath.mkdirs();

                    }
                    for (int i = 0; i < files.length; i++) {
                        // 上传文件名
                        String filename = files[i].getOriginalFilename();
                        ContractFile contractFile = new ContractFile();
                        contractFile.setFileName(filename);
                        String newFilename = UUID.randomUUID()
                                + filename.substring(filename
                                .lastIndexOf("."));
                        String url = "/decorationsystem/upload/contract/" + savecontract.getRegistrationId() + "/" + newFilename;
                        contractFile.setFileUrl(url);
                        contractFile.setContractId(contractId);
                        try {
                            files[i].transferTo(
                                    new File("D:\\ProjectUpload\\contract\\" + savecontract.getRegistrationId() + File.separator + newFilename)
                            );

                        } catch (IOException e) {
                            e.printStackTrace();
                            url = "";
                            msg = "文件储存错误";
                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("msg", msg);
                            return jsonObject1.toString();
                        }
                        System.out.println(contractFile.toString());
                        contractFiles.add(contractFile);
                    }
                } else {
                    msg = "合同资料更新失败！";
                }
                Integer uploadcout = contractService.saveContractFile(contractFiles);
                msg = "上传" + files.length + "文件,成功上传" + uploadcout + "个文件";
            }else{
                msg="请先上传合同";
            }

        }else {
            msg = "请先上传设计图";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/newsContract", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String newsContract(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String contractName =request.getParameter("contractName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Integer registrationId = Integer.parseInt(request.getParameter("registrationId"));
        Integer modeId = Integer.parseInt(request.getParameter("modeId"));
        Integer version = Integer.parseInt(request.getParameter("version"));
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        String contractDesribe = request.getParameter("contractDesribe");
        BigDecimal contractMoney =new BigDecimal( request.getParameter("contractMoney"));
        contractMoney = contractMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        Date sTime = Date.valueOf(startTime);
        Date eTime = Date.valueOf(endTime);
        if (files.length>20){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","上传文件不能超过20个");
            return jsonObject1.toString();
        }
        String msg="";
        Designerpic designerpic = designerpicService.searchDesignerpicByRegistrationId(registrationId);
        if (designerpic!=null){
            Contract contract = contractService.findContractByRegistrationId(registrationId);
            if (contract!=null) {
                Contract savecontract = new Contract();
                if (!contractName.trim().equals("") && registrationId != null && modeId != null & startTime != null && endTime != null && contractMoney != null) {
                    savecontract.setContractDescribe(contractDesribe);
                    savecontract.setContractMoney(contractMoney);
                    savecontract.setContractName(contractName);
                    savecontract.setEndTime(eTime);
                    savecontract.setStartTime(sTime);
                    savecontract.setRegistrationId(registrationId);
                    savecontract.setContractId(contractId);
                    savecontract.setUploadTime(new java.util.Date());
                    DecorationMode decorationMode = new DecorationMode(modeId);
                    savecontract.setDecorationMode(decorationMode);
                    savecontract.setStatus(6);

                } else {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg", "填写信息除合同描述外，其它不能为空");
                    return jsonObject1.toString();
                }

                List<ContractFile> contractFiles = new ArrayList<ContractFile>();
                Integer resultColumn = 0;
                if (version >= contractService.searchVersionById(contractId)) {
                    savecontract.setVersion(version + 1);
                    resultColumn = contractService.changeContract(savecontract);
                } else {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg", "该合同资料已被更新，请重新刷新！");
                    return jsonObject1.toString();
                }
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\contract\\" + savecontract.getRegistrationId()));
                contractService.dropAllContractFileByContractId(savecontract.getContractId());
                if (resultColumn != 0) {
                    File filepath = new File("D:\\ProjectUpload\\contract\\" + savecontract.getRegistrationId());
                    // 判断路径是否存在，如果不存在就创建一个
                    if (!filepath.exists()) {
                        filepath.mkdirs();

                    }
                    for (int i = 0; i < files.length; i++) {
                        // 上传文件名
                        String filename = files[i].getOriginalFilename();
                        ContractFile contractFile = new ContractFile();
                        contractFile.setFileName(filename);
                        String newFilename = UUID.randomUUID()
                                + filename.substring(filename
                                .lastIndexOf("."));
                        String url = "/decorationsystem/upload/contract/" + savecontract.getRegistrationId() + "/" + newFilename;
                        contractFile.setFileUrl(url);
                        contractFile.setContractId(contractId);
                        try {
                            files[i].transferTo(
                                    new File("D:\\ProjectUpload\\contract\\" + savecontract.getRegistrationId() + File.separator + newFilename)
                            );

                        } catch (IOException e) {
                            e.printStackTrace();
                            url = "";
                            msg = "文件储存错误";
                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("msg", msg);
                            return jsonObject1.toString();
                        }
                        System.out.println(contractFile.toString());
                        contractFiles.add(contractFile);
                    }
                } else {
                    msg = "合同资料更新失败！";
                }
                Integer uploadcout = contractService.saveContractFile(contractFiles);
                contractService.changeStatusById(contractId,6);
                registrationTableService.changStatusByRegistrationId(registrationId,4);
                msg = "上传" + files.length + "文件,成功上传" + uploadcout + "个文件,客户已成为合同客户";
            }else{
                msg="请先上传合同";
            }

        }else {
            msg = "请先上传设计图";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toString();
    }



        @RequestMapping(value = "/delContract",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
        @ResponseBody
        public String delContract(@RequestBody String str) throws IOException {
            JSONObject jsonObject1= JSONObject.fromObject(str);
            Integer contractId = jsonObject1.getInt("contractId");
            Integer registrationId = jsonObject1.getInt("registrationId");
            JSONObject jsonObject2 = new JSONObject();
            String msg="";
            Integer versionsql = contractService.searchVersionById(contractId);
            if (versionsql!=null){

                if (contractId!=null){
                    FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\contract\\"+registrationId));
                    contractService.dropAllContractFileByContractId(contractId);
                    contractService.dropContractById(contractId);
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
    @RequestMapping(value = "/setcontractStatus",method =RequestMethod.POST,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String setcontractStatus(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer contractId = jsonObject1.getInt("contractId");

        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        Employee employee = (Employee) session.getAttribute("employee");
        if (!MyAuthUtils.findAuth(employee.getJob().getMyAuths(),61)){
              jsonObject2.put("msg","权限不够！");
              return jsonObject2.toString();
        }

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
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        Employee employee = (Employee) session.getAttribute("employee");
        if (!MyAuthUtils.findAuth(employee.getJob().getMyAuths(),61)){
            jsonObject2.put("msg","权限不够！");
            return jsonObject2.toString();
        }
        if (version>=contractService.searchVersionById(contractId)){
                column = contractService.changeStatusById(contractId,2);

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

    @RequestMapping(value = "/setdesignerpicStatus",method =RequestMethod.POST,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String setdesignerpicStatus(@RequestBody String str,HttpSession session){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer designerpicId = jsonObject1.getInt("designerpicId");
        Integer version = jsonObject1.getInt("version");
        JSONObject jsonObject2 = new JSONObject();
        String msg="";
        Integer column = 0;
        Employee employee = (Employee) session.getAttribute("employee");
        if (!MyAuthUtils.findAuth(employee.getJob().getMyAuths(),60)){
            jsonObject2.put("msg","权限不够！");
            return jsonObject2.toString();
        }
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
        Employee employee = (Employee) session.getAttribute("employee");
        if (!MyAuthUtils.findAuth(employee.getJob().getMyAuths(),60)){
            jsonObject2.put("msg","权限不够！");
            return jsonObject2.toString();
        }
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

}
