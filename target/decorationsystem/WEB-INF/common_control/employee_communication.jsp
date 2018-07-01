<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/7
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <title>Title</title>
    <link href="<%=basePath%>css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=basePath%>dist/css/bootstrapValidator.css" rel="stylesheet"/>
    <link href="<%=basePath%>css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>dist/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <link href="<%=basePath%>css/indexstyle.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/nav.js"></script>
    <style type="text/css">
        .mm {
            word-break: break-all;
            word-wrap: break-word;
        }
    </style>
</head>
<body>
<div class="container-fluid" style="padding-top: 50px;">

    <c:if test="${sessionScope.employee.job.jobId==10005 }">
        <jsp:include page="../header.jsp" flush="true"></jsp:include>
    </c:if>
    <c:if test="${sessionScope.employee.job.jobId==10004}">
        <jsp:include page="../header_project_manager.jsp" flush="true"></jsp:include>
    </c:if>
    <c:if test="${sessionScope.employee.job.jobId==10001}">
        <jsp:include page="../header_a.jsp" flush="true"></jsp:include>
    </c:if>
    <c:if test="${sessionScope.employee.job.jobId==10002}">
        <jsp:include page="../header_designer.jsp" flush="true"></jsp:include>
    </c:if>
    <div class="row"  style="margin-right: 0;">
        <%--占位div--%>
        <div class="col-sm-2"></div>
        <%--主要内容--%>
        <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-0" style="padding-bottom: 50px;">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li class="active">在线交流</li>
                </ol>
            </div>
            <div class="panel panel-info">
                <!-- Default panel contents -->
                <div class="panel-heading">在线交流</div>

                    <div class="panel-body">
                        <div style="margin: 5px 0 0 0; height: 400px;">

                            <!-- 消息展示框 -->
                            <div id="msg" style="width: 100%; height: 70%;padding-left: 20px;  overflow: auto;"></div>

                            <hr/>
                            <!-- 消息编辑框 -->
                            <textarea id="tx" style="width: 100%; height: 20%;"></textarea>

                            <!-- 消息发送按钮 -->
                            <div class="col-sm-3 pull-right">
                                <button id="submit" class="btn btn-primary" >发送</button>
                            </div>

                        </div>
                    </div>
            </div>
        </div>


            <%--弹出提示框--%>
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


        </div>
    </div>
</div>
<script>
    $.sidebarMenu($('.sidebar-menu'));

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
        var str = evnt.data
        var json =JSON.parse(str)
        console.log(json)
        var myDate = new Date();
        var name="${employee.employeeName}"
        $("#msg").append("<div class='row'>"+json.msguserId+"</div><div class='row' style='text-align: center'>"+myDate.toLocaleString()+"</div><div class='row'>"+json.name+"</div> <div class='row'><div class='col-sm-5 mm' >" + json.msgContent + "</div></div>");
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
        var text = $("#tx").val();
        var msg = {
            msgContent: text,
            postsId: 1
        };
        $.post({
            url: "${pageContext.request.contextPath}/webSocket/sendMessgetoUser",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(msg),
            aysnc:false,
            success : function (data) {
                console.log(data.result);
                $("#msg").append("<div class='row'><div class='col-sm-4 pull-right mm'>" + text + "</div></div>");
                $("#tx").val("")

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
