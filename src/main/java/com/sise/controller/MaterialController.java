package com.sise.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sise.po.*;
import com.sise.service.MaterialClazzService;
import com.sise.service.MaterialOfferService;
import com.sise.service.MaterialService;
import com.sise.service.MaterialSmallclazzService;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:04 2018/2/24
 */
@Controller
@RequestMapping("/material_management")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialSmallclazzService materialSmallclazzService;

    @Autowired
    private MaterialClazzService materialClazzService;

    @Autowired
    private MaterialOfferService materialOfferService;


    @RequestMapping(value = "/material", method = RequestMethod.GET)
    public String material(@RequestParam(value = "materialName", defaultValue = "", required = false) String materialName, @RequestParam(value = "offerId", defaultValue = "0", required = false) Integer offerId,
                           @RequestParam(value = "bigId", defaultValue = "0", required = false) Integer bigId, @RequestParam(value = "smallId", defaultValue = "0", required = false) Integer smallId, Model model, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        SearchMaterial searchContent = new SearchMaterial();
        if (materialName != null)
            searchContent.setMaterialName(materialName);
        if (offerId != null)
            searchContent.setOfferId(offerId);
        if (bigId != null)
            searchContent.setBigId(bigId);
        if (smallId != null)
            searchContent.setSmallId(smallId);
        System.out.println(searchContent.toString());
        PageHelper.startPage(pageNum, 8);
        List<Material> materials = materialService.searchAllMaterial(searchContent);
        PageInfo<Material> pageInfo = new PageInfo<Material>(materials);
        List<MaterialOffer> materialOffers = materialOfferService.searchAllMaterialOffer();
        List<MaterialClazz> materialClazzes = materialClazzService.searchAllMaterClazz();
        model.addAttribute("materialOffers", materialOffers);
        model.addAttribute("materialClazzes", materialClazzes);
        model.addAttribute("materials", materials);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("searchContent", searchContent);
        return "material_control/material_management";

    }

    @RequestMapping(value = "/getMaterialSmallclazz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCity(@RequestBody String bigId) {
        Gson gson = new Gson();
        JSONObject jsonObject = JSONObject.fromObject(bigId);
        Integer big = jsonObject.getInt("bigId");
        List<MaterialSmallclazz> smallclazzes = materialSmallclazzService.searchMaterialSmallclazzByBigId(big);
        String result = gson.toJson(smallclazzes);
        return result;

    }

    @RequestMapping(value = "/saveMaterial", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveMaterial(HttpServletRequest request, @RequestParam(value = "files") MultipartFile[] files) throws IOException, ParseException {
        String materialName = request.getParameter("materialName");
        String materialCompany = request.getParameter("materialCompany");
        String materialBrand = request.getParameter("materialBrand");
        String materialStandard = request.getParameter("materialStandard");
        Integer offerId = Integer.parseInt(request.getParameter("offerId"));
        Integer smallId = Integer.parseInt(request.getParameter("smallId"));
        String materialDescribe = request.getParameter("materialDescribe");
        BigDecimal materialPrice = new BigDecimal(request.getParameter("materialPrice"));
        materialPrice = materialPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (files.length > 20) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg", "上传文件不能超过20个");
            return jsonObject1.toString();
        }
        String msg = "";
        Material materialsave = new Material();
        if (!materialName.trim().equals("") && offerId != null && smallId != null) {
            materialsave.setMaterialName(materialName);
            materialsave.setMaterialBrand(materialBrand);
            materialsave.setMaterialCompany(materialCompany);
            materialsave.setMaterialDescribe(materialDescribe);
            materialsave.setMaterialStandard(materialStandard);
            materialsave.setMaterialPrice(materialPrice);
            materialsave.setUploadTime(new java.util.Date());
            MaterialOffer materialOffer = new MaterialOffer();
            MaterialSmallclazz materialSmallclazz = new MaterialSmallclazz();
            materialOffer.setOfferId(offerId);
            materialSmallclazz.setSmallId(smallId);
            materialsave.setMaterialOffer(materialOffer);
            materialsave.setMaterialSmallclazz(materialSmallclazz);

        } else {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg", "材料名不能为空");
            return jsonObject1.toString();
        }

        List<MaterialFile> materialFiles = new ArrayList<MaterialFile>();
        Integer resultColumn = materialService.saveMaterial(materialsave);
        System.out.println(materialsave);
        if (resultColumn != 0) {
            File filepath = new File("D:\\ProjectUpload\\material\\" + smallId + "\\" + materialsave.getMaterialId());
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.exists()) {
                filepath.mkdirs();

            }
            System.out.println("上传路径：" + filepath.getParentFile());
            for (int i = 0; i < files.length; i++) {
                // 上传文件名
                String filename = files[i].getOriginalFilename();
                MaterialFile materialFile = new MaterialFile();
                materialFile.setFileName(filename);
                materialFile.setMaterialId(materialsave.getMaterialId());
                String newFilename = UUID.randomUUID()
                        + filename.substring(filename
                        .lastIndexOf("."));
                String url = "";
                try {
                    files[i].transferTo(
                            new File("D:\\ProjectUpload\\material\\" + smallId + "\\" + materialsave.getMaterialId() + File.separator + newFilename)
                    );
                    url = "/decorationsystem/upload/material/" + smallId + "/" + materialsave.getMaterialId() + "/" + newFilename;
                    materialFile.setFileUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    url = "";
                    msg = "文件储存错误";
                    FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\material\\" + smallId + "\\" + materialsave.getMaterialId()));
                    Integer column = materialService.dropAllMaterialFileByMaterialId(materialsave.getMaterialId());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg", msg);
                    return jsonObject1.toString();
                }
                System.out.println(materialFile.toString());
                materialFiles.add(materialFile);
            }
        } else {
            msg = "材料上传失败！";
        }
        Integer uploadcout = materialService.saveMaterialFile(materialFiles);
        msg = "成功添加，上传" + files.length + "文件,成功上传" + uploadcout + "个文件";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/getMaterial", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMaterial(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer materialId = Integer.parseInt(jsonObject.getString("materialId"));
        System.out.println(materialId);
        Material material = materialService.searchMaterialyById(materialId);
        System.out.println(material);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("material", material);
        Integer bigId = material.getMaterialSmallclazz().getMaterialClazz().getBigId();
        List<MaterialSmallclazz> materialSmallclazzes = materialSmallclazzService.searchMaterialSmallclazzByBigId(bigId);
        String appstr = "";
        for (MaterialSmallclazz m : materialSmallclazzes) {
            appstr = appstr + "<option value='" + m.getSmallId() + "' >" + m.getSmallName() + "</option>";
        }
        jsonObject1.put("appstr", appstr);
        System.out.println(jsonObject1.toString());
        return jsonObject1.toString();
    }

    @RequestMapping(value = "/updateMaterial", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateMaterial(HttpServletRequest request, @RequestParam(value = "files") MultipartFile[] files) throws IOException, ParseException {
        Integer version = Integer.parseInt(request.getParameter("version"));
        Integer materialId = Integer.parseInt(request.getParameter("materialId"));
        String materialName = request.getParameter("materialName");
        String materialCompany = request.getParameter("materialCompany");
        String materialBrand = request.getParameter("materialBrand");
        String materialStandard = request.getParameter("materialStandard");
        Integer offerId = Integer.parseInt(request.getParameter("offerId"));
        Integer smallId = Integer.parseInt(request.getParameter("smallId"));
        String materialDescribe = request.getParameter("materialDescribe");
        BigDecimal materialPrice = new BigDecimal(request.getParameter("materialPrice"));
        materialPrice = materialPrice.setScale(2, BigDecimal.ROUND_HALF_UP);

        if (files.length > 20) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg", "上传文件不能超过20个");
            return jsonObject1.toString();
        }
        System.out.println("价格" + materialPrice);
        String msg = "";
        Material materialsave = new Material();
        if (!materialName.trim().equals("") && offerId != null && smallId != null && materialId != null) {
            materialsave.setMaterialName(materialName);
            materialsave.setMaterialBrand(materialBrand);
            materialsave.setMaterialCompany(materialCompany);
            materialsave.setMaterialDescribe(materialDescribe);
            materialsave.setMaterialStandard(materialStandard);
            materialsave.setMaterialPrice(materialPrice);
            materialsave.setUploadTime(new java.util.Date());
            MaterialOffer materialOffer = new MaterialOffer();
            MaterialSmallclazz materialSmallclazz = new MaterialSmallclazz();
            materialOffer.setOfferId(offerId);
            materialSmallclazz.setSmallId(smallId);
            materialsave.setMaterialOffer(materialOffer);
            materialsave.setMaterialSmallclazz(materialSmallclazz);
            materialsave.setMaterialId(materialId);
            System.out.println(materialsave);

        } else {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg", "材料名不能为空");
            return jsonObject1.toString();
        }

        List<MaterialFile> materialFiles = new ArrayList<MaterialFile>();
        Integer resultColumn = 0;
        try {
            if (version >= materialService.searchVersionById(materialId)) {
                materialsave.setVersion(version + 1);
                resultColumn = materialService.changeMaterial(materialsave);
            }else{
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg", "材料已被别人更新，请重新刷新");
                return jsonObject1.toString();
            }
        }catch (NullPointerException e){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg", "材料已被删除，请重新刷新");
            return jsonObject1.toString();
        }
        materialService.dropAllMaterialFileByMaterialId(materialId);
        FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\material\\" +smallId + "\\" + materialsave.getMaterialId()));

        System.out.println(materialsave);
        if (resultColumn != 0) {
            File filepath = new File("D:\\ProjectUpload\\material\\" + smallId + "\\" + materialsave.getMaterialId());
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.exists()) {
                filepath.mkdirs();

            }
            System.out.println("上传路径：" + filepath.getParentFile());
            for (int i = 0; i < files.length; i++) {
                // 上传文件名
                String filename = files[i].getOriginalFilename();
                MaterialFile materialFile = new MaterialFile();
                materialFile.setFileName(filename);
                materialFile.setMaterialId(materialsave.getMaterialId());
                String newFilename = UUID.randomUUID()
                        + filename.substring(filename
                        .lastIndexOf("."));
                String url = "";
                try {
                    files[i].transferTo(
                            new File("D:\\ProjectUpload\\material\\" + smallId + "\\" + materialsave.getMaterialId() + File.separator + newFilename)
                    );
                    url = "/decorationsystem/upload/material/" + smallId + "/" + materialsave.getMaterialId() + "/" + newFilename;
                    materialFile.setFileUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    url = "";
                    msg = "文件储存错误";
                    FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\material\\" + smallId + "\\" + materialsave.getMaterialId()));
                    Integer column = materialService.dropAllMaterialFileByMaterialId(materialsave.getMaterialId());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("msg", msg);
                    return jsonObject1.toString();
                }
                System.out.println(materialFile.toString());
                materialFiles.add(materialFile);
            }
        } else {
            msg = "材料上传失败！";
        }
        Integer uploadcout = materialService.saveMaterialFile(materialFiles);
        msg = "成功更新，上传" + files.length + "文件,成功上传" + uploadcout + "个文件";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/delMaterial", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delMaterial(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer materialId = Integer.parseInt(jsonObject.getString("materialId"));
        System.out.println(materialId);
        JSONObject jsonObject1 = new JSONObject();
        String msg = "";
        try{
            Material material = materialService.searchMaterialyById(materialId);
            if (material!=null){
                materialService.dropAllMaterialFileByMaterialId(materialId);
                materialService.dropMaterialById(materialId);
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\material\\" + material.getMaterialSmallclazz().getSmallId() + "\\" + material.getMaterialId()));
            }else {
                msg = "材料已被人删除，请重新刷新";
                jsonObject1.put("msg",msg);
                return jsonObject1.toString();
            }
        }catch (NullPointerException e){
            msg = "材料已被人删除，请重新刷新";
            jsonObject1.put("msg",msg);
            return jsonObject1.toString();
        } catch (IOException e) {
            e.printStackTrace();
            msg = "材料删除异常，请重新刷新";
            jsonObject1.put("msg",msg);
            return jsonObject1.toString();
        }
        msg = "成功删除";
        jsonObject1.put("msg",msg);
        return jsonObject1.toString();
    }

    @RequestMapping("/material_content")
    public String material_content(@RequestParam("materialId") Integer materialId,Model model){
        Material material = null;
        List<MaterialFile> materialFiles = new ArrayList<MaterialFile>();
        if (materialId!=null){
            material = materialService.searchMaterialyById(materialId);
            materialFiles = materialService.searchMaterialFileByMaterialId(materialId);
        }
        model.addAttribute("material",material);
        model.addAttribute("materialFiles",materialFiles);
        return "material_control/material_content";
    }



}
