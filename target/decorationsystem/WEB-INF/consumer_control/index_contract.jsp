<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/2
  Time: 22:59
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

                <li><a href="${pageContext.request.contextPath}/welcome/housepic?registrationId=${registrationId}">户型图</a></li>
                <li><a href="${pageContext.request.contextPath}/welcome/designerpic?registrationId=${registrationId}">设计图</a></li>
                <li class="active">
                    <a href="#home" data-toggle="tab">
                        合同
                    </a>
                </li>
            </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="home" style="font-size: 18px;">
                        <c:if test="${requestScope.resultStatus == true}">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-4">合同编号：${contract.contractId}</div>

                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-4">合同名字：${contract.contractName}</div>
                                    <div class="col-sm-4">开工日期：${contract.startTime}</div>
                                    <div class="col-sm-4">竣工日期：${contract.endTime}</div>
                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-4">合同金额：${contract.contractMoney}RMB</div>
                                    <div class="col-sm-4">合同描述：${contract.contractDescribe}</div>
                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-4">装修模式：${contract.decorationMode.modeName}</div>
                                    <div class="col-sm-4">上传时间：<fmt:formatDate value="${contract.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                </div>
                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-2">合同图片：</div>
                                    <div class="col-sm-8">
                                        <c:forEach items="${contractFiles}" var="c">
                                            <img src="${c.fileUrl}" class="img-responsive" alt="Responsive image">
                                        </c:forEach>
                                    </div>
                                </div>
                        </c:if>
                        <c:if test="${requestScope.resultStatus==false}">
                            <p>项目经理未上传合同</p>
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
            contractId:'${contract.contractId}',
            version:'${contract.version}'
        }
        $.post({
            url: "${pageContext.request.contextPath}/welcome/setcontractStatus",
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
            contractId:'${contract.contractId}',
            version:'${contract.version}'
        }
        $.post({
            url: "${pageContext.request.contextPath}/welcome/setcontractStatusFalse",
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
