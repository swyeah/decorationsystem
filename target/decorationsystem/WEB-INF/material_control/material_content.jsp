<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/25
  Time: 20:15
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
    .row{
        margin-bottom: 10px;
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
                    <li>材料管理</li>
                    <li class="active">材料详情</li>
                </ol>
            </div>
            <div class="row" style="font-size: 18px;">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                材料详情
                            </a>
                        </li>
                    </ul>
                    <div class="panel-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <div class="row">
                                    <div class="col-sm-3">材料编号：${material.materialId}</div>
                                    <div class="col-sm-4">材料名字：${material.materialName}</div>
                                    <div class="col-sm-3">材料价格：${material.materialPrice}RMB</div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">材料规格：${material.materialCompany}</div>
                                    <div class="col-sm-3">材料单位：${material.materialStandard}</div>
                                    <div class="col-sm-6">材料品牌：${material.materialBrand}</div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">材料大类型：${material.materialSmallclazz.materialClazz.bigName}</div>
                                    <div class="col-sm-3">材料小类型：${material.materialSmallclazz.smallName}</div>
                                    <div class="col-sm-6">上传时间：<fmt:formatDate value="${material.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">材料描述：${material.materialDescribe}</div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">材料供应商：${material.materialOffer.offerName}</div>
                                    <div class="col-sm-3">供应商电话：${material.materialOffer.offerPhone}</div>
                                    <div class="col-sm-3">供应商地址：${material.materialOffer.offerAddress}</div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">材料图片：</div>
                                    <div class="col-sm-8">
                                        <c:forEach items="${materialFiles}" var="df">
                                            <div>${df.fileName}</div>
                                            <img src="${df.fileUrl}" class="img-responsive" alt="图片缺失">
                                        </c:forEach>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script>
    $.sidebarMenu($('.sidebar-menu'));
</script>

</body>
</html>
