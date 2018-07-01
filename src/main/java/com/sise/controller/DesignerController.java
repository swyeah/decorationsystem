package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.*;
import com.sise.service.AreaTypeService;
import com.sise.service.DecorationStyleService;
import com.sise.service.DesignerShowService;
import com.sise.service.HouseTypeService;
import com.sise.util.MyAuthUtils;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
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
import java.text.ParseException;
import java.util.*;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:27 2018/2/27
 */
@Controller
@RequestMapping("/designer_show")
public class DesignerController {
    @Autowired
    private DesignerShowService designerShowService;

    @Autowired
    private AreaTypeService areaTypeService;

    @Autowired
    private HouseTypeService houseTypeService;

    @Autowired
    private DecorationStyleService decorationStyleService;

    @RequestMapping("/designer_up")
    public String designer_up(Model model){
        List<AreaType> areaTypes = areaTypeService.findAllAreaType();
        List<HouseType> houseTypes = houseTypeService.findAllHousetype();
        List<DecorationStyle> decorationStyles = decorationStyleService.searchAllDecorationStyle();
        model.addAttribute("areaTypes",areaTypes);
        model.addAttribute("houseTypes",houseTypes);
        model.addAttribute("decorationStyles",decorationStyles);
        return "common_control/designer_up";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam(value = "files")MultipartFile[] files) throws IOException, ParseException {
        String msg="";

        File filepath = new File("D:\\ProjectUpload\\designershow");
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
                        new File("D:\\ProjectUpload\\designershow\\"+File.separator+newFilename)
                );
                url="/decorationsystem/upload/designershow/"+newFilename;
                urls.add(url);
            } catch (IOException e) {
                e.printStackTrace();
                url="";
                msg="文件储存错误";
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\designershow"));
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

    @RequestMapping(value = "/addDesignerShow", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addDesignerShow(@Valid @RequestBody DesignerShow designerShow, Errors errors){
        JSONObject jsonObject1 = new JSONObject();
        String msg ="";
        if(errors.hasErrors()){
            //获取错误信息
            System.out.println("进入获取错误信息");
            List<FieldError> errorList = errors.getFieldErrors();
            for (FieldError e:errorList){

                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg =msg + "    " + e.getDefaultMessage();
            }

            jsonObject1.put("msg",msg);
            return jsonObject1.toString();
        }

        System.out.println(designerShow.toString());
        designerShow.setUploadTime(new Date());
        Integer column = designerShowService.saveDesignerShow(designerShow);
        if (column!=0 && column!=null){
            msg = "1";
            jsonObject1.put("showId",designerShow.getShowId());
        }else {
            msg = "0";
        }
        jsonObject1.put("msg",msg);
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/uploadTitlepic", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadDeposit(HttpServletRequest request, @RequestParam(value = "file")MultipartFile file) throws IOException, ParseException {
        Integer showId = Integer.parseInt(request.getParameter("showId"));
        String path = request.getSession().getServletContext().getRealPath(
                "/upload/");
        // 上传文件名
        JSONObject jsonObject = new JSONObject();
            String filename = file.getOriginalFilename();
            String newFilename = UUID.randomUUID()
                    + filename.substring(filename
                    .lastIndexOf("."));
            File filepath = new File("D:\\ProjectUpload\\designershow\\"+showId);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.exists()) {
                filepath.mkdirs();
            }

            try {
                file.transferTo(
                        new File("D:\\ProjectUpload\\designershow\\"+showId + File.separator + newFilename)
                );
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject.put("msg","图片储存失败，请重新上传");
                return jsonObject.toString();
            }
            String url = "/decorationsystem/upload/designershow/"+showId +"/"+ newFilename;
            System.out.println("url  " + url);
            Integer column = designerShowService.changTitlepicByShowId(showId,url);
            System.out.println("更新行数为"+column);

        jsonObject.put("msg","文章上传成功！");
        return jsonObject.toString();
    }


    @RequestMapping("/designer_show")
    public String designer_show(@Param("showId") Integer showId, Model model){
        DesignerShow designerShow = designerShowService.searchDesignerShowByShowId(showId);
        Integer prais = designerShowService.getCountPraiseByShowId(showId);
        Integer collectioncout = designerShowService.getCountCollectionByShowId(showId);
        model.addAttribute("designerShow",designerShow);
        model.addAttribute("prais",prais);
        model.addAttribute("collectioncout",collectioncout);
        List<AreaType> areaTypes = areaTypeService.findAllAreaType();
        List<HouseType> houseTypes = houseTypeService.findAllHousetype();
        List<DecorationStyle> decorationStyles = decorationStyleService.searchAllDecorationStyle();
        model.addAttribute("areaTypes",areaTypes);
        model.addAttribute("houseTypes",houseTypes);
        model.addAttribute("decorationStyles",decorationStyles);

        return "common_control/designer_show";
    }

    @RequestMapping(value = "/updateDesignerShow", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateDesignerShow(@RequestBody DesignerShow designerShow){
        JSONObject jsonObject1 = new JSONObject();
        String msg ="";

        System.out.println(designerShow.toString());
        designerShow.setUploadTime(new Date());
        Integer column = designerShowService.changeDesignerShow(designerShow);
        if (column!=0 && column!=null){
            msg = "1";
            jsonObject1.put("showId",designerShow.getShowId());
        }else {
            msg = "0";
        }
        jsonObject1.put("msg",msg);
        return jsonObject1.toString();
    }
    @RequestMapping(value = "/delDesignerShow", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delDesignerShow(@RequestBody String str){
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        Integer showId = jsonObject1.getInt("showId");
        JSONObject jsonObject2 = new JSONObject();
        Integer column = designerShowService.dropDesignerShowByShowId(showId);
        if (column==0){
            jsonObject2.put("msg","删除操作失败！");
        }else {
            jsonObject2.put("msg","删除操作成功！");
        }
        return jsonObject2.toString();
    }

    @RequestMapping(value = "/designer_show_management",method = RequestMethod.GET)
    public String designerpic_management(@RequestParam(value = "sname",defaultValue = "",required = false) String sname, @RequestParam(value = "houseId",defaultValue = "0",required = false) Integer houseId,
                                         @RequestParam(value = "styleId",defaultValue = "0",required = false) Integer styleId,
                                         @RequestParam(value = "areaId",defaultValue = "0",required = false) Integer areaId, Model model, @RequestParam(defaultValue = "1",required = false) Integer pageNum, HttpSession session){
        SearchDesignerShow searchContent =new SearchDesignerShow();

        if (sname!=null)
            searchContent.setSname(sname);
        if (styleId !=null)
            searchContent.setStyleId(styleId);
        if (houseId !=null)
            searchContent.setHouseId(houseId);
        if(areaId!=null)
            searchContent.setAreaId(areaId);
        Employee employee = (Employee) session.getAttribute("employee");
        if (MyAuthUtils.findAuth(employee.getJob().getMyAuths(),1)){
            searchContent.setEmployeeId(0);
        }else {
            searchContent.setEmployeeId(employee.getEmployeeId());
        }
        System.out.println(searchContent.toString());
        PageHelper.startPage(pageNum, 8);
        List<DesignerShow> designerShows = designerShowService.searchDesignerByEmployeeId(searchContent);
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
        return "common_control/designer_show_management";

    }

}
