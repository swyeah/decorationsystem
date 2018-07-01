package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.po.MaterialClazz;
import com.sise.po.MaterialSmallclazz;
import com.sise.service.MaterialClazzService;
import com.sise.service.MaterialSmallclazzService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:18 2018/2/25
 */
@Controller
@RequestMapping("/materialClazz_management")
public class MaterialClazzController {
    @Autowired
    private MaterialSmallclazzService materialSmallclazzService;

    @Autowired
    private MaterialClazzService materialClazzService;

    @RequestMapping("/materialSmallclazz")
    public String materialSmallclazz(@RequestParam(value = "searchContent", defaultValue = "", required = false) String searchContent, Model model, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<MaterialSmallclazz> materialSmallclazzes =materialSmallclazzService.searchMaterialSmallclazzBySearchMaterialClazz(searchContent);
        PageInfo<MaterialSmallclazz> pageInfo = new PageInfo<MaterialSmallclazz>(materialSmallclazzes);
        List<MaterialClazz> materialClazzes = materialClazzService.searchAllMaterClazz();
        model.addAttribute("materialClazzes", materialClazzes);
        model.addAttribute("materialSmallclazzes", materialSmallclazzes);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("searchContent", searchContent);
        return "material_control/materialSmallclazz_management";
    }
    @RequestMapping(value = "/saveMaterialSmallclazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveMaterialSmallclazz(@Valid @RequestBody MaterialSmallclazz materialSmallclazz , Errors errors) {
        JSONObject resultJson = new JSONObject();
        String msg ="";
        if(errors.hasErrors()){
            //获取错误信息
            System.out.println("进入获取错误信息");
            List<FieldError> errorList = errors.getFieldErrors();

            for (FieldError e:errorList){

                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg = msg+e.getDefaultMessage()+"    ";
            }

            resultJson.put("msg",msg);
            return resultJson.toString();
        }
        Integer column =  materialSmallclazzService.saveMaterialSmallclazz(materialSmallclazz);
        if (column==1){
            msg="成功添加！";
        }else{
            msg="添加失败！";
        }
        resultJson.put("msg",msg);
        return resultJson.toString();

    }

    @RequestMapping(value = "/updateMaterialSmallclazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateMaterialSmallclazz(@Valid @RequestBody MaterialSmallclazz materialSmallclazz ,Errors errors) {
        JSONObject resultJson = new JSONObject();
        String msg ="";
        if(errors.hasErrors()){
            //获取错误信息
            System.out.println("进入获取错误信息");
            List<FieldError> errorList = errors.getFieldErrors();

            for (FieldError e:errorList){

                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg = msg+e.getDefaultMessage()+"    ";
            }

            resultJson.put("msg",msg);
            return resultJson.toString();
        }
        Integer column =  materialSmallclazzService.changeMaterialSmallclazz(materialSmallclazz);
        if (column==1){
            msg="更新成功！";
        }else{
            msg="更新失败！";
        }
        resultJson.put("msg",msg);
        return resultJson.toString();

    }

    @RequestMapping(value = "/getMaterialSmallclazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMaterialSmallclazz(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer smallId = Integer.parseInt(jsonObject.getString("smallId"));
        MaterialSmallclazz materialSmallclazz = materialSmallclazzService.searchMaterialSmallclazzById(smallId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("materialSmallclazz", materialSmallclazz);
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/delMaterialSmallclazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delMaterialSmallclazz(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer smallId = Integer.parseInt(jsonObject.getString("smallId"));
        JSONObject jsonObject1 = new JSONObject();
        String msg = "";
        Integer result = 0;
        try{
            MaterialSmallclazz materialSmallclazz = materialSmallclazzService.searchMaterialSmallclazzById(smallId);
            if (materialSmallclazz!=null){
                result=materialSmallclazzService.dropMaterialSmallclazz(smallId);
            }else {
                msg = "材料小分类已被人删除，请重新刷新";
                jsonObject1.put("msg",msg);
                return jsonObject1.toString();
            }
        }catch (NullPointerException e){
            msg = "材料小分类已被人删除，请重新刷新";
            jsonObject1.put("msg",msg);
            return jsonObject1.toString();
        }
        if (result==1){
            msg="材料小分类删除成功！";
        }else{
            msg="材料小分类删除失败";
        }
        jsonObject1.put("msg",msg);
        return jsonObject1.toString();
    }


    @RequestMapping("/materialClazz")
    public String materialClazz(@RequestParam(value = "searchContent", defaultValue = "", required = false) String searchContent, Model model, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<MaterialClazz> materialClazzes =materialClazzService.searchMaterialClazzBySearch(searchContent);
        PageInfo<MaterialClazz> pageInfo = new PageInfo<MaterialClazz>(materialClazzes);
        model.addAttribute("materialClazzes", materialClazzes);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("searchContent", searchContent);
        return "material_control/materialClazz_management";
    }
    @RequestMapping(value = "/saveMaterialClazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveMaterialClazz(@Valid @RequestBody MaterialClazz materialClazz , Errors errors) {
        JSONObject resultJson = new JSONObject();
        String msg ="";
        if(errors.hasErrors()){
            //获取错误信息
            System.out.println("进入获取错误信息");
            List<FieldError> errorList = errors.getFieldErrors();

            for (FieldError e:errorList){

                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg = msg+e.getDefaultMessage()+"    ";
            }

            resultJson.put("msg",msg);
            return resultJson.toString();
        }
        Integer column =  materialClazzService.saveMaterialClazz(materialClazz);
        if (column==1){
            msg="成功添加！";
        }else{
            msg="添加失败！";
        }
        resultJson.put("msg",msg);
        return resultJson.toString();

    }

    @RequestMapping(value = "/updateMaterialClazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateMaterialClazz(@Valid @RequestBody MaterialClazz materialClazz ,Errors errors) {
        JSONObject resultJson = new JSONObject();
        String msg ="";
        if(errors.hasErrors()){
            //获取错误信息
            System.out.println("进入获取错误信息");
            List<FieldError> errorList = errors.getFieldErrors();

            for (FieldError e:errorList){

                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
                msg = msg+e.getDefaultMessage()+"    ";
            }

            resultJson.put("msg",msg);
            return resultJson.toString();
        }
        Integer column =  materialClazzService.changeMaterialClazz(materialClazz);
        if (column==1){
            msg="更新成功！";
        }else{
            msg="更新失败！";
        }
        resultJson.put("msg",msg);
        return resultJson.toString();

    }

    @RequestMapping(value = "/getMaterialClazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMaterialClazz(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer bigId = Integer.parseInt(jsonObject.getString("bigId"));
        MaterialClazz materialClazz = materialClazzService.searchMaterClazzById(bigId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("materialClazz", materialClazz);
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/delMaterialClazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delMaterialClazz(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer bigId = Integer.parseInt(jsonObject.getString("bigId"));
        JSONObject jsonObject1 = new JSONObject();
        String msg = "";
        Integer result = 0;
        String delStr="";
        try{
            MaterialClazz materialClazz = materialClazzService.searchMaterClazzById(bigId);
            if (materialClazz!=null){
                 delStr = materialClazzService.dropMaterialClazz(bigId);
            }else {
                msg = "材料大分类已被人删除，请重新刷新";
                jsonObject1.put("msg",msg);
                return jsonObject1.toString();
            }
        }catch (NullPointerException e){
            msg = "材料大分类已被人删除，请重新刷新";
            jsonObject1.put("msg",msg);
            return jsonObject1.toString();
        } catch (IOException e) {
            e.printStackTrace();
            msg = "材料图片删除出错，请重新刷新";
            jsonObject1.put("msg",msg);
            return jsonObject1.toString();

        }
        msg = delStr;
        jsonObject1.put("msg",msg);
        return jsonObject1.toString();
    }
}
