package com.sise.controller;

import com.sise.po.Consumer;
import com.sise.po.Employee;
import com.sise.service.LoginService;
import com.sise.util.MD5Utils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }


    /**
     * 获取验证码
     *
     * @param response
     * @param session
     */
    @RequestMapping("/getVerifyCode")
    public void generate(HttpServletResponse response, HttpSession session) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String verifyCodeValue = drawImg(output);

        session.setAttribute("verifyCodeValue", verifyCodeValue);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘画验证码
     *
     * @param output
     * @return
     */
    private String drawImg(ByteArrayOutputStream output) {
        String code = "";
        // 随机产生4个字符
        for (int i = 0; i < 4; i++) {
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        // 调用Graphics2D绘画验证码
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66, 2, 82);
        g.setColor(color);
        g.setBackground(new Color(226, 226, 240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 随机参数一个字符
     *
     * @return
     */
    private char randomChar() {
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }

/**
 * 设置验证码
 * @param code 验证码
 */

    @RequestMapping(value = "/checkcode", method = RequestMethod.POST)
    @ResponseBody
    public  String setJson(@RequestBody String code, HttpSession session) {
        System.out.println(code);
        String verifyCode=code.substring(11,15);
        //System.out.println("用户输入验证码为"+verifyCode);
        String verifyCodeValue=(String) session.getAttribute("verifyCodeValue");
       // System.out.println("session的验证码为"+verifyCodeValue);
        Map<String,String> mv= new HashMap<String, String>() ;
       // String result = null;
        if(verifyCodeValue.equals(verifyCode.toUpperCase())){
            System.out.println("用户输入的验证码和图片生成的验证码相等，登陆成功");
            mv.put("valid","true");
        }else{
            System.out.println("用户输入的验证码和图片生成的验证码不相等");
            mv.put("valid","false");
        }
        JSONObject jsonObject = JSONObject.fromObject(mv);
        String result = jsonObject.toString();
        System.out.println(result);
        return result;

    }
/**
 * 检查用户名是否存在
 * @param name 用户输入用户名
 */
    @RequestMapping(value = "/register/checkName" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkName(@RequestBody String name){
        String checkName = name.substring(5);
        System.out.println("input输入的用户名为"+checkName);
        Map<String,String> map= new HashMap<String, String>();
        if(loginService.checkName(checkName)==null){
            System.out.println("用户名可以使用");
            map.put("valid","true");
        }else{
            System.out.println("用户名已存在");
            map.put("valid","false");
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);
        return result;
        }
/**
 * ajax查询账号是否存在
 * @param userName 用户输入的账号
 */
    @RequestMapping(value = "/register/checkUserName" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(@RequestBody String userName){
        String checkUserName = userName.substring(9);
        System.out.println("input输入的账号为"+checkUserName);
        Map<String,String> map= new HashMap<String, String>();
        if(loginService.checkUserName(checkUserName)==null){
            System.out.println("账号可以使用");
            map.put("valid","true");
        }else{
            System.out.println("账号已存在");
            map.put("valid","false");
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);
        return result;
    }

/**
 * 校验用户信息，并注册用户，
 *
 * @param consumer
 * @param errors 错误信息
 */
    @RequestMapping(value = "/doregister",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String doregister(@Valid @RequestBody Consumer consumer, Errors errors){
        //System.out.println(consumer.toString());
        consumer.setPassword(MD5Utils.generatePassword(consumer.getPassword()));
        consumer.setRegistrationTime(new Date());
        JSONObject resultJson = new JSONObject();
        if(errors.hasErrors()){
            //获取错误信息
            System.out.println("进入获取错误信息");
            List<FieldError> errorList = errors.getFieldErrors();
            for (FieldError e:errorList){

                //打印错误信息
                System.err.println("fied:"+e.getField() +"\t" +"msg:"+e.getDefaultMessage());
            }

            resultJson.put("result","false");
            return resultJson.toString();
        }

       Integer result = loginService.saveConsumer(consumer);
        if (result>0){
            resultJson.put("result","true");
        }else{
            resultJson.put("result","false");
        }

        return resultJson.toString();
    }
    @RequestMapping(value = "/goIndex" ,method = RequestMethod.POST)
    @ResponseBody
    public String goIndex(@RequestBody String cons,HttpSession session){
        JSONObject jsonObject = JSONObject.fromObject(cons);
        String name = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        Integer identifier = Integer.parseInt(jsonObject.getString("identifier"));
        JSONObject resultJson = new JSONObject();
        if (!name.equals("") && !password.equals("")){
            String psw = MD5Utils.generatePassword(password);
            if (identifier==0){
                Consumer consumer =null;
                consumer = loginService.login(name,psw);
                if (consumer!=null){
                    if (!consumer.getConsId().equals("") && !consumer.getName().equals("")){
                        session.setAttribute("consumer",consumer);
                        session.setAttribute("name",consumer.getName());
                        session.setAttribute("id",consumer.getConsId());
                        resultJson.put("result","true");
                        return resultJson.toString();
                    }else{
                        resultJson.put("result","false");
                        return resultJson.toString();
                    }
                }
            }else if (identifier==1){
                Employee employee=null;
                employee = loginService.loginEmployee(Integer.parseInt(name),psw);
                System.out.println(employee);
                if (employee!=null){
                    if (employee.getEmployeeId()!=null && !employee.getEmployeeName().equals("")){
                        session.setAttribute("employee",employee);
                        session.setAttribute("name",employee.getEmployeeName());
                        resultJson.put("result","employee");
                        return resultJson.toString();
                    }else{
                        resultJson.put("result","false");
                        return resultJson.toString();
                    }
                }
            }
        }
        resultJson.put("result","notnull");
        return resultJson.toString();
    }


}
