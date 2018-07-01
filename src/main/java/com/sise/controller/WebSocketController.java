package com.sise.controller;

import com.google.gson.Gson;
import com.sise.po.Consumer;
import com.sise.po.Employee;
import com.sise.po.Message;
import com.sise.service.ConsumerService;
import com.sise.service.EmployeeService;
import com.sise.webSocket.MyWebSocketHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:35 2018/2/26
 */
@Controller
@RequestMapping("/webSocket")
public class WebSocketController {
    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @RequestMapping("/employee_communication")
    public String employee_communication(@RequestParam("toid") String toid, HttpSession session,Model model){
        System.out.println("toid:"+toid);
        session.setAttribute("toid",toid);
        Consumer consumer = consumerService.searchConsumerByConsId(Integer.parseInt(toid));
        model.addAttribute("consumer",consumer);
        return "common_control/employee_communication";
    }

    @RequestMapping("/employee_communications")
    public String employee_communications( ){
        return "common_control/employee_communications";
    }

    @RequestMapping("/consumer_communication")
    public String consumer_communication(@RequestParam("toid") String toid, HttpSession session, Model model){
        System.out.println("toid:"+toid);
        session.setAttribute("toid",toid);
        Employee employee = employeeService.searchEmployeeById(Integer.parseInt(toid));
        model.addAttribute("employee",employee);
        return "consumer_control/index_communication";
    }

    @RequestMapping(value = "/sendMessge", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String sendMessge(@RequestBody Message message, HttpSession session){
        String toid = (String) session.getAttribute("toid");
        Consumer consumer = (Consumer) session.getAttribute("consumer");
        Employee employee = employeeService.searchEmployeeById(Integer.parseInt(toid));
        JSONObject jsonObject = new JSONObject();
        if (employee!=null){
            message.setFromName(consumer.getName());
            message.setFromId(consumer.getConsId());
            message.setSendDate(new Date());
            message.setToId(Integer.parseInt(toid));
            message.setToName(employee.getEmployeeName());
        }else {
            jsonObject.put("result","1");
            return jsonObject.toString();
        }
        System.out.println(message);
        Gson gson = new Gson();
        boolean bn = myWebSocketHandler.sendMessageToUser(toid, new TextMessage(gson.toJson(message)));
        if (!bn){
            myWebSocketHandler.sendMessageToUser(consumer.getConsId().toString(), new TextMessage("用户不在线！"));
        }
        jsonObject.put("result","2");
        return jsonObject.toString();
    }
    @RequestMapping(value = "/sendMessgetoUser", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String sendMessgetoUser(@RequestBody Message message, HttpSession session){
        String toid = (String) session.getAttribute("toid");
        Employee employee = (Employee) session.getAttribute("employee");
        Consumer consumer = consumerService.searchConsumerByConsId(Integer.parseInt(toid));
        JSONObject jsonObject = new JSONObject();
        if (consumer!=null){
            message.setToName(consumer.getUserName());
            message.setFromId(employee.getEmployeeId());
            message.setSendDate(new Date());
            message.setToId(Integer.parseInt(toid));
        }else {
            jsonObject.put("result","1");
            return jsonObject.toString();
        }
        System.out.println(message);
        Gson gson = new Gson();
        boolean bn=myWebSocketHandler.sendMessageToUser(toid, new TextMessage(gson.toJson(message)));
        if (!bn){
            myWebSocketHandler.sendMessageToUser(employee.getEmployeeId().toString(), new TextMessage("用户不在线！"));
        }
        jsonObject.put("result","2");
        return jsonObject.toString();
    }


}
