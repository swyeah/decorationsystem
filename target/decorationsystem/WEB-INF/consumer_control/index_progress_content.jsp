<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/1
  Time: 21:43
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
        .write{
            color: #ffffff;
        }
        .list-group-item span{
            float: left;
            margin-left: 10px;
        }
        .list-group-item a{
            display: block;
            text-decoration: none;

        }
        .list-group-item a:hover,a:active{
            color: red;
        }
        .red{
            color: red;
        }
        .row{
            margin: 0 0 0 0;
        }
    </style>

</head>
<body>
<div class="container-fluid" style="padding-left: 0;padding-right: 0;">
    <%--顶部导航条--%>
        <jsp:include page="../indexHeader.jsp" flush="true"></jsp:include>

        <div class="row">
        <div class="col-sm-8 col-sm-offset-2" style="padding-top: 20px;">
            <div class="row">
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                工程进度内容
                            </a>
                        </li>
                    </ul>
                    <div class="panel-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <div class="row">
                                    <div class="col-sm-2">工程进度日期</div>
                                    <div class="col-sm-3"><fmt:formatDate value="${projectTime.projectDay}" pattern="yyyy-MM-dd"/></div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        工程进度内容
                                    </div>
                                </div>
                                <div class="row">
                                    <c:forEach items="${progressContents}" var="p">
                                        <c:if test="${progressContent.progressId==p.progressId}">
                                            <button onclick="window.location.href='${pageContext.request.contextPath}/welcome/progressContent?projectId=${p.projectId}&progressId=${p.progressId}'" class="btn btn-success col-sm-2" style="margin-left: 10px; margin-top: 10px;">${p.progressTitle}</button>
                                        </c:if>
                                        <c:if test="${progressContent.progressId!=p.progressId}">
                                            <button onclick="window.location.href='${pageContext.request.contextPath}/welcome/progressContent?projectId=${p.projectId}&progressId=${p.progressId}'" class="btn btn-default col-sm-2"  style="margin-left: 10px; margin-top: 10px;">${p.progressTitle}</button>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-4">
                                        上传时间：<fmt:formatDate value="${progressContent.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </div>
                                    <div class="col-sm-4">
                                        上传人：${progressContent.employee.employeeName}
                                    </div>
                                </div>
                                <div class="row" style="padding-left: 20px;">
                                    ${progressContent.progressContent}
                                </div>
                            </div><%--home--%>
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
                </div>
        </div><!-- /.modal -->

    </div>

</div>


</body>
</html>
