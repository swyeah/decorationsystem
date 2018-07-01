package com.sise.webSocket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sise.po.Consumer;
import com.sise.po.Employee;
import com.sise.po.Message;
import com.sise.service.ConsumerService;
import com.sise.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:08 2018/2/26
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
   @Autowired
   private EmployeeService employeeService;
   @Autowired
   private ConsumerService consumerService;

    private static final Logger log = Logger.getLogger(MyWebSocketHandler.class);
    // 保存所有的用户session
    private final static List<WebSocketSession> users = Collections.synchronizedList(new ArrayList<WebSocketSession>());    // 连接 就绪时
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("connect websocket success.......");

        users.add(webSocketSession);
        Integer uid = (Integer) webSocketSession.getAttributes().get("uid");
        System.out.println(uid);
        /*if (uid!=null){
            webSocketSession.sendMessage(new TextMessage("连接成功"));
        }*/
        Employee employee = employeeService.searchEmployeeById(uid);
        Consumer consumer = consumerService.searchConsumerByConsId(uid);
        ;
        // 与客户端完成连接后调用
        System.out.println("afterConnectionEstablished");
        System.out.println("getId:" + webSocketSession.getId());
        System.out.println("getLocalAddress" + webSocketSession.getLocalAddress().toString());
        System.out.println("getTextMessageSizeLimit:" + webSocketSession.getTextMessageSizeLimit());
        System.out.println("getUri:" + webSocketSession.getUri().toString());
        System.out.println("getPrincipal:" + webSocketSession.getPrincipal());
        Gson gson = new Gson();
        Message message = new Message();
        message.setFromName("系统提示：");
        if (employee!=null)
            message.setMsgContent("连接成功，你好！请不要下线，等待客户连接。");
        if (consumer!=null)
            message.setMsgContent("连接成功，你好！");
        String msg =gson.toJson(message);
        webSocketSession.sendMessage(new TextMessage(msg));
    }
    // 处理信息
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        Gson gson = new Gson();
        Integer uid = getClientId(webSocketSession);
        System.out.println("handleMessage输出"+uid);
        // 将消息JSON格式通过Gson转换成Map
        // message.getPayload().toString() 获取消息具体内容
        Map<String, Object> msg = gson.fromJson(webSocketMessage.getPayload().toString(),
                new TypeToken<Map<String, Object>>() {}.getType());
        System.out.println("msg:"+msg);

        log.info("handleMessage......."+webSocketMessage.getPayload()+"..........."+msg);
//      session.sendMessage(message);
        // 处理消息 msgContent消息内容
        TextMessage textMessage = new TextMessage(webSocketMessage.getPayload().toString(), true);
        // 调用方法（发送消息给所有人）

       sendMessageToUser((String) msg.get("toId"),textMessage);
       /*if (!res){
           Message message = new Message();
           message.setFromName("系统提示：");
           message.setFromId(Integer.parseInt((String) msg.get("toId")) );
           message.setMsgContent("用户已下线");
           Gson gson1 = new Gson();
           textMessage = new TextMessage(gson1.toJson(message),true);
           sendMessageToUser(msg.get("fromId").toString(),textMessage);

       }*/
    }

    // 处理传输时异常
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
        log.debug("websocket chat connection closed......");
        users.remove(webSocketSession);
    }

    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("connect websocket closed.......");

        users.remove(webSocketSession);
    }

    public boolean supportsPartialMessages() {
        return false;
    }
    // 给所有用户发送 信息
    public void sendMsgToAllUsers(WebSocketMessage<?> message) throws Exception{

        for (WebSocketSession user : users) {
            user.sendMessage(message);
        }

    }
    /**
     * 发送信息给指定用户
     * @param uid
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String uid, TextMessage message) {
        for (WebSocketSession user : users) {
            System.out.println("websoct得到的id"+user.getAttributes().get("uid").toString());
            System.out.println("uid为："+uid);
            System.out.println(user.getAttributes().get("uid").toString().equals(uid));
            if (user.getAttributes().get("uid").toString().equals(uid)) {
                try {
                    if (user.isOpen()) {
                        System.out.println("111");
                        user.sendMessage(message);
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get("uid");
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }
}
