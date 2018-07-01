package com.sise.webSocket;

import com.sise.po.Consumer;
import com.sise.po.Employee;
import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:14 2018/2/26
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private static final Logger log = Logger.getLogger(MyWebSocketHandler.class);
    // 握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            Employee employee = (Employee) servletRequest.getSession().getAttribute("employee");
            System.out.println(employee);
            Consumer consumer = (Consumer) servletRequest.getSession().getAttribute("consumer");
            // 使用userName区分WebSocketHandler，以便定向发送消息
            // String userName = (String)
            // session.getAttribute("WEBSOCKET_USERNAME");
            if (employee!=null){
                attributes.put("uid", employee.getEmployeeId());
                servletRequest.getSession().setAttribute("uid",  employee.getEmployeeId());
                ;
            }
            else if (consumer!=null){
                attributes.put("uid", consumer.getConsId());
                servletRequest.getSession().setAttribute("uid", consumer.getConsId());
            }

        }
        System.out.println("++++++++++++++++ HandshakeInterceptor: beforeHandshake  ++++++++++++++"+attributes);

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }



    // 握手后
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {


        System.out.println("++++++++++++++++ HandshakeInterceptor: afterHandshake  ++++++++++++++");


        super.afterHandshake(request, response, wsHandler, ex);
    }
}
