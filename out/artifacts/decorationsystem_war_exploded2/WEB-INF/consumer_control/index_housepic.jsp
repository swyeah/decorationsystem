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
        .green{
            color: green;
        }
        .blue{
            color: blue;
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
        <div class="col-sm-8 col-sm-offset-2">

            <!-- Default panel contents -->
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#home" data-toggle="tab">
                        户型图
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/welcome/designerpic?registrationId=${registrationId}">设计图</a></li>
                <li><a href="${pageContext.request.contextPath}/welcome/contract?registrationId=${registrationId}">合同</a></li>
            </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="home" style="font-size: 18px;">
                        <c:if test="${requestScope.resultStatus == true}">
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-4">户型图编号：${housepic.housepicId}</div>
                                <div class="col-sm-4">户型图名字：${housepic.housepicName}</div>
                            </div>
                            <div class="row" style="margin-top: 10px;">

                                <div class="col-sm-4">上传时间：<fmt:formatDate value="${housepic.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-4">房子类型：${housepic.houseType.houseName}</div>
                                <div class="col-sm-4">面积类型：${housepic.areaType.areaName}平米</div>
                            </div>
                            <div class="row" style="margin-top: 10px;">

                                <div class="col-sm-4">户型图描述：${housepic.housepicDescrible}</div>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-2">户型图图片：</div>
                                <div class="col-sm-8">
                                    <c:forEach items="${housepicFiles}" var="hf">
                                        <div>${hf.fileName}</div>
                                        <img src="${hf.fileUrl}" class="img-responsive" alt="图片缺失">
                                    </c:forEach>

                                </div>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.resultStatus==false}">
                            <p>设计师尚未上传户型图</p>
                        </c:if>
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
<script type="text/javascript">


    $('#setstatusbtn').click(function () {
        var json = {
            housepicId:'${housepic.housepicId}',
            userName:'${sessionScope.consumer.userName}',
            version:'${housepic.version}'
        }
        $.post({
            url: "${pageContext.request.contextPath}/welcome/setStatus",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(json),
            success : function (data) {
                console.log(data);
                $('#myModalBody').text(data.msg);
                $('#myModal').modal('show');
                $('#myModal').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error : function(){
                $('#myModalBody').text("操作失败，请重新操作");
                $('#myModal').modal('show');
                $('#myModal').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        });
    })
    $('#setstatusfalsebtn').click(function () {
        var json = {
            housepicId:'${housepic.housepicId}',
            userName:'${sessionScope.consumer.userName}',
            version:'${housepic.version}'
        }
        $.post({
            url: "${pageContext.request.contextPath}/welcome/setStatusFalse",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(json),
            success : function (data) {
                console.log(data);
                $('#myModalBody').text(data.msg);
                $('#myModal').modal('show');
                $('#myModal').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error : function(){
                $('#myModalBody').text("操作失败，请重新操作");
                $('#myModal').modal('show');
                $('#myModal').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        });
    })

</script>


</body>
</html>
