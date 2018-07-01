<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/3
  Time: 9:22
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
    <script type="text/javascript" src="<%=basePath%>js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <link href="<%=basePath%>css/indexstyle.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/nav.js"></script>
    <style type="text/css">
        .red{
            color: red;
        }
        .blue{
            color: blue;
        }
        .green{
            color: green;
        }
    </style>
</head>
<body style="padding-top:50px;">

<div class="container-fluid ">
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
        <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-0">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li>客户管理</li>
                    <li class="active">设计图</li>
                </ol>
            </div>
            <div class="row" style="font-size: 18px;">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li><a href="${pageContext.request.contextPath}/project_manager/housepic?registrationId=${registrationId}">户型图审批</a></li>
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                设计图审批
                            </a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/project_manager/contract?registrationId=${registrationId}">合同审批</a></li>
                    </ul>
                    <div class="panel-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <c:if test="${requestScope.resultStatus == true}">
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-4">设计图编号：${designerpic.designerpicId}</div>
                                        <div class="col-sm-4">设计图名字：${designerpic.designerpicName}</div>
                                        <c:if test="${requestScope.designerpic.status==3}">
                                            <div class="col-sm-4">
                                                <button class="btn btn-primary" type="button" id="setstatusbtn">同意设计图</button>
                                                <button class="btn btn-danger" type="button" id="setstatusfalsebtn">拒绝设计图</button>
                                            </div>
                                        </c:if>

                                    </div>
                                    <div class="row" style="margin-top: 10px;">

                                        <div class="col-sm-4">设计预算：${designerpic.designerpicMoney}</div>
                                        <div class="col-sm-4">上传时间：<fmt:formatDate value="${designerpic.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-4">房子类型：${designerpic.houseType.houseName}</div>
                                        <div class="col-sm-4">面积类型：${designerpic.areaType.areaName}平米</div>
                                        <div class="col-sm-4">设计风格：${designerpic.decorationStyle.styleName}</div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">

                                        <div class="col-sm-4">设计图描述：${designerpic.designerpicDescribe}</div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-10">审批状态：
                                            <c:if test="${requestScope.designerpic.status==1}"><span class="blue">客户未确认</span></c:if>
                                            <c:if test="${requestScope.designerpic.status==2}"><span class="red">客户拒绝设计图，审批未通过请与客户联系</span></c:if>
                                            <c:if test="${requestScope.designerpic.status==3}"><span class="green">客户确认设计图，等待项目经理审批</span></c:if>
                                            <c:if test="${requestScope.designerpic.status==4}"><span class="red">项目经理拒绝设计图，请跟项目经理联系</span></c:if>
                                            <c:if test="${requestScope.designerpic.status==5}"><span class="green">项目经理确认设计图，审批通过</span></c:if>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-2">设计图图片：</div>
                                        <div class="col-sm-8">
                                            <c:forEach items="${designerpicFiles}" var="df">
                                                <div>${df.fileName}</div>
                                                <p>${df.fileDescribe}</p>
                                                <img src="${df.fileUrl}" class="img-responsive" alt="图片缺失">
                                            </c:forEach>

                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${requestScope.resultStatus==false}">
                                    <p>无设计图，请与设计师联系</p>
                                </c:if>
                            </div>
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


<script type="text/javascript">
    $.sidebarMenu($('.sidebar-menu'))

    $('#setstatusbtn').click(function () {
        var json = {
            designerpicId:${designerpic.designerpicId},
            version:${designerpic.version}
        }
        $.post({
            url: "${pageContext.request.contextPath}/project_manager/setdesignerpicStatus",
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
            designerpicId:${designerpic.designerpicId},
            version:${designerpic.version}
        }
        $.post({
            url: "${pageContext.request.contextPath}/project_manager/setdesignerpicStatusFalse",
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
