<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/24
  Time: 17:45
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
                    <li>图纸管理</li>
                    <li class="active">合同管理</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">合同管理
                        <div class="pull-right">总共${pageInfo.pages }页，总共${pageInfo.total }条记录</div>
                    </div>
                    <div class="panel-body">
                    </div>

                    <div class="row" style="margin-left: 10px;">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/picture_management/contract_management" method="get">
                            <div class="col-sm-2">
                                start
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                    <input class="form-control"  name="startTime" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <input type="hidden" id="dtp_input2" value="" /><br/>

                                <script type="text/javascript">
                                    $(".form_date").datetimepicker({
                                        language: 'zh-CN',
                                        format: "yyyy-mm-dd",
                                        autoclose: true,  //当选择一个日期之后是否立即关闭此日期时间选择器。
                                        todayBtn: true,
                                        startView: 2,
                                        minView: 2,
                                        startDate: "2000-10-01"
                                    });
                                </script>
                            </div>
                            <div class="col-sm-2">
                                end
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" name="endTime" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <input type="hidden" id="dtp_input" value="" />
                                <script type="text/javascript">
                                    $(".form_date").datetimepicker({
                                        language: 'zh-CN',
                                        format: "yyyy-mm-dd",
                                        autoclose: true,  //当选择一个日期之后是否立即关闭此日期时间选择器。
                                        todayBtn: true,
                                        startView: 2,
                                        minView: 2,
                                        startDate: "2000-10-01"
                                    });
                                </script>
                            </div>
                            <div class="col-sm-2">
                                合同名字
                                <input name="cname" class="form-control" placeholder="模糊查询">
                            </div>
                            <div class="col-sm-2">
                                合同装修模式
                                <select id="modeId" name="modeId"class="form-control">
                                    <option value="0">请选择装修模式</option>
                                    <c:forEach items="${decorationModes}" var="h">
                                        <option value="${h.modeId}">${h.modeName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm-2">
                                &nbsp;
                                <button class="btn btn-default btn-lg" type="submit"><i class="glyphicon glyphicon-search"></i> </button>
                            </div>
                        </form>
                    </div>

                    <div class="table-responsive">
                        <!-- Table -->
                        <table class="table table-hover">
                            <tr  class="text-center">
                                <th>合同编号</th>
                                <th>合同名字</th>
                                <th>合同金额</th>
                                <th>合同天数</th>
                                <th>登记编号</th>
                                <th>装修类型</th>
                                <th>上传时间</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${contracts}" var="c" varStatus="i" >
                                <tr>
                                    <td>${c.contractId}</td>
                                    <td>
                                        <c:if test="${c.contractName.length()>10}">
                                            ${c.contractName.substring(0,10)}...
                                        </c:if>
                                        <c:if test="${c.contractName.length()<=10}">
                                            ${c.contractName}
                                        </c:if>
                                    </td>
                                    <td>${c.contractMoney}</td>
                                    <td>${(c.endTime.time-c.startTime.time)/(1000 * 60 * 60 * 24)}</td>
                                    <td>${c.registrationId}</td>
                                    <td>${c.decorationMode.modeName}</td>
                                    <td><fmt:formatDate value="${c.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                        <div  class="btn-group btn-group-xs" role="group">
                                            <button type="button"   onclick="window.location.href='${pageContext.request.contextPath}/common_deposit/contract_approval?registrationId=${c.registrationId}&employeeId=${sessionScope.employee.employeeId}'" class="btn btn-primary">点击进入操作页面</button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- 分页 -->
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <nav aria-lable="Page navigation">
                                <ul class="pagination">

                                    <li><a href="${pageContext.request.contextPath}/picture_management/contract_management?pageNum=1&startTime=<fmt:formatDate value='${searchContent.startTime}' pattern='yyyy-MM-dd'/>&endTime=<fmt:formatDate value='${searchContent.endTime}' pattern='yyyy-MM-dd'/>&cname=${searchContent.contractName}&modeId=${searchContent.modeId}">首页</a></li>

                                    <c:if test="${pageInfo.hasPreviousPage  }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/picture_management/contract_management?pageNum=${pageInfo.pageNum-1}&startTime=<fmt:formatDate value='${searchContent.startTime}' pattern='yyyy-MM-dd'/>&endTime=<fmt:formatDate value='${searchContent.endTime}' pattern='yyyy-MM-dd'/>&cname=${searchContent.contractName}&modeId=${searchContent.modeId}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                        <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/picture_management/contract_management?pageNum=${page}&startTime=<fmt:formatDate value='${searchContent.startTime}' pattern='yyyy-MM-dd'/>&endTime=<fmt:formatDate value='${searchContent.endTime}' pattern='yyyy-MM-dd'/>&cname=${searchContent.contractName}&modeId=${searchContent.modeId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page==pageInfo.pageNum}">
                                            <li class="active"><a href="${pageContext.request.contextPath}/picture_management/contract_management?pageNum=${page}&startTime=<fmt:formatDate value='${searchContent.startTime}' pattern='yyyy-MM-dd'/>&endTime=<fmt:formatDate value='${searchContent.endTime}' pattern='yyyy-MM-dd'/>&cname=${searchContent.contractName}&modeId=${searchContent.modeId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/picture_management/contract_management?pageNum=${page}&startTime=<fmt:formatDate value='${searchContent.startTime}' pattern='yyyy-MM-dd'/>&endTime=<fmt:formatDate value='${searchContent.endTime}' pattern='yyyy-MM-dd'/>&cname=${searchContent.contractName}&modeId=${searchContent.modeId}">${page}</a></li>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${pageInfo.hasNextPage }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/picture_management/contract_management?pageNum=${pageInfo.pageNum+1 }&startTime=<fmt:formatDate value='${searchContent.startTime}' pattern='yyyy-MM-dd'/>&endTime=<fmt:formatDate value='${searchContent.endTime}' pattern='yyyy-MM-dd'/>&cname=${searchContent.contractName}&modeId=${searchContent.modeId}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <li><a href="${pageContext.request.contextPath}/picture_management/contract_management?pageNum=${pageInfo.pages}&startTime=<fmt:formatDate value='${searchContent.startTime}' pattern='yyyy-MM-dd'/>&endTime=<fmt:formatDate value='${searchContent.endTime}' pattern='yyyy-MM-dd'/>&cname=${searchContent.contractName}&modeId=${searchContent.modeId}">末页</a></li>

                                </ul>
                            </nav>
                        </div>
                    </div><%--分页--%>
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
<script type="text/javascript">
    $.sidebarMenu($('.sidebar-menu'))

</script>
</body>
</html>

