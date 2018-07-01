<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/26
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <title></title>
    <link href="<%=basePath%>css/bootstrap.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/sockjs.min.js"></script>
<%--
    <script type="text/javascript" src="<%=basePath%>js/mywebsocket.js"></script>
--%>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <style type="text/css">
        .row{
            margin: 1em 0 1em 0;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 最外边框 -->
    <div style="margin: 20px auto; border: 1px solid blue; width: 300px; height: 500px;">

        <!-- 消息展示框 -->
        <div id="msg" style="width: 100%; height: 70%; border: 1px solid yellow;overflow: auto;"></div>

        <!-- 消息编辑框 -->
        <textarea id="tx" style="width: 100%; height: 20%;"></textarea>

        <!-- 消息发送按钮 -->
        <button id="TXBTN" style="width: 100%; height: 8%;">发送数据</button>

    </div>
    <form>
        <button id="submit" type="button" >测试</button>
    </form>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    提示
                </h4>
            </div>
            <div class="modal-body" id="myModalBody">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    var websocket;


    // 首先判断是否 支持 WebSocket
    if('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/decorationsystem/websocket");
    } else if('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://localhost:8080/decorationsyste/websocket");
    } else {
        websocket = new SockJS("http://localhost:8080/decorationsyste/sockjs/websocket");
    }

    // 打开时
    websocket.onopen = function(evnt) {
        console.log("  websocket.onopen  ");
    };


    // 处理消息时
    websocket.onmessage = function(evnt) {
        $("#msg").append("<p>(<font color='red'>" + evnt.data + "</font>)</p>");
        console.log("  websocket.onmessage   ");
    };


    websocket.onerror = function(evnt) {
        console.log("  websocket.onerror  ");
    };

    websocket.onclose = function(evnt) {
        console.log("  websocket.onclose  ");
    };


    // 点击了发送消息按钮的响应事件
    $("#TXBTN").click(function(){

        // 获取消息内容
        var text = $("#tx").val();

        // 判断
        if(text == null || text == ""){
            alert(" content  can not empty!!");
            return false;
        }

        var msg = {
            msgContent: text,
            postsId: 1
        };

        // 发送消息
        websocket.send(JSON.stringify(msg));

    });
    $('#submit').click(function() {
            $.post({
                url: "${pageContext.request.contextPath}/webSocket/sendMessge",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: null,
                aysnc:false,
                success : function (data) {
                    console.log(data);

                },
                error : function(){
                    $('#myModalBody').text('请重新刷新页面');
                    $('#myModal').modal('show')
                }
            });

    });
</script>
</body>
</html>
