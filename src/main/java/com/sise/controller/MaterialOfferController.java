package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sise.po.MaterialOffer;
import com.sise.service.MaterialOfferService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:39 2018/2/25
 */
@Controller
@RequestMapping("/material_offer_management")
public class MaterialOfferController {
    @Autowired
    private MaterialOfferService materialOfferService;

    @RequestMapping("/materialOffer")
    public String materialOffer(@RequestParam(value = "searchContent", defaultValue = "", required = false) String searchContent,  Model model, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<MaterialOffer> materialOffers =materialOfferService.searchMaterialOfferBySearchContent(searchContent);
        PageInfo<MaterialOffer> pageInfo = new PageInfo<MaterialOffer>(materialOffers);
        model.addAttribute("materialOffers", materialOffers);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("searchContent", searchContent);
        return "material_control/materialOffer_management";
    }
    @RequestMapping(value = "/saveMaterialOffer", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveMaterialOffer(@Valid @RequestBody MaterialOffer materialOffer ,Errors errors) {
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
       Integer column =  materialOfferService.saveMaterialOffer(materialOffer);
        if (column==1){
            msg="成功添加！";
        }else{
            msg="添加失败！";
        }
        resultJson.put("msg",msg);
        return resultJson.toString();

    }

    @RequestMapping(value = "/updateMaterialOffer", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateMaterialOffer(@Valid @RequestBody MaterialOffer materialOffer ,Errors errors) {
        System.out.println(materialOffer);
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
        Integer column =  materialOfferService.changeMaterialOffer(materialOffer);
        if (column==1){
            msg="更新成功！";
        }else{
            msg="更新失败！";
        }
        resultJson.put("msg",msg);
        return resultJson.toString();

    }

    @RequestMapping(value = "/getMaterialOffer", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMaterialOffer(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer offerId = Integer.parseInt(jsonObject.getString("offerId"));
        MaterialOffer materialOffer = materialOfferService.searchMaterialOfferById(offerId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("materialOffer", materialOffer);
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/delMaterialOffer", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delMaterialOffer(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer offerId = Integer.parseInt(jsonObject.getString("offerId"));
        JSONObject jsonObject1 = new JSONObject();
        String msg = "";
        Integer result = 0;
        try{
            MaterialOffer materialOffer = materialOfferService.searchMaterialOfferById(offerId);
            if (materialOffer!=null){
                result=materialOfferService.dropMaterialOffer(offerId);
            }else {
                msg = "供应商已被人删除，请重新刷新";
                jsonObject1.put("msg",msg);
                return jsonObject1.toString();
            }
        }catch (NullPointerException e){
            msg = "供应商已被人删除，请重新刷新";
            jsonObject1.put("msg",msg);
            return jsonObject1.toString();
        }
        if (result==1){
            msg="供应商删除成功！";
        }else{
            msg="供应商删除失败";
        }
        jsonObject1.put("msg",msg);
        return jsonObject1.toString();
    }
}
