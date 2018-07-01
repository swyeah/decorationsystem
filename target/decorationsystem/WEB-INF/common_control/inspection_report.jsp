<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/5
  Time: 20:36
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
                    <li class="active">工程验收</li>
                </ol>
            </div>
            <div class="row" style="font-size: 18px;">
                <div class="panel panel-primary" >
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                工程验收报告
                            </a>
                        </li>
                        <li><a href="#uploadd" data-toggle="tab">上传工程验收报告</a></li>

                    </ul>
                    <div class="panel-body" >
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <c:if test="${requestScope.resultStatus == 1}">
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-4">工程验收报告编号：${inspectionReport.reportId}</div>
                                        <div class="col-sm-4">客户登记编号：${inspectionReport.registrationId}</div>
                                        <div class="col-sm-4">
                                            <button class="btn btn-primary" type="button" id="uploadbtn">更新</button>
                                            <button class="btn btn-danger" type="button" id="delbtn">删除</button>

                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-5">
                                            验收状态：
                                            <c:if test="${requestScope.inspectionReport.reportStatus==1}">
                                                <span class="green">通过</span>
                                            </c:if>
                                            <c:if test="${requestScope.inspectionReport.reportStatus==0}">
                                                <span class="red">未通过</span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-4">上传人员：${inspectionReport.employee.job.department.departmentName}&nbsp;${inspectionReport.employee.job.jobName}&nbsp;${inspectionReport.employee.employeeName}</div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-4">上传时间：<fmt:formatDate value="${inspectionReport.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                    </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-2">合同图片：</div>
                                        <div class="col-sm-8">
                                            <c:forEach items="${inspectionReportPics}" var="c">
                                                ${c.fileName}
                                                <img src="${c.fileUrl}" class="img-responsive" alt="Responsive image">
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${requestScope.resultStatus==0}">
                                    <p>无验收报告</p>
                                </c:if>
                            </div>
                            <div class="tab-pane fade" id="uploadd">
                                <form class="form-horizontal" id="uploadform" enctype="multipart/form-data" method="post">

                                    <div class="row">
                                        <div class="col-xs-4 col-sm-2">验收报告状态：</div>
                                        <div class="col-xs-4 col-sm-2 form-group">
                                                <select class="form-control" name="reportStatus" id="reportStatus">
                                                    <option value="1">通过</option>
                                                    <option value="0">未通过</option>
                                                </select>
                                        </div>
                                    </div>
                                    <div class="row"  style="margin-top: 10px;">
                                        <div class=" col-sm-2">验收报告图片：</div>
                                        <div class="col-sm-3 form-group">
                                            <input id="file" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="registrationId" id="registrationId" value='${registrationId}'/>
                                    <input type="hidden" name="employeeId" id="employeeId" value='${sessionScope.employee.employeeId}'/>
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-3 col-sm-offset-2">
                                            <button type="button" onclick="uploadFiles()" class="btn btn-primary">上传</button>
                                            <button type="reset" id="reset" class="btn btn-default">重置</button>
                                        </div>
                                    </div>
                                </form>
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

        <%--弹出提示框--%>
        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel2">
                            验收报告更新
                        </h4>
                    </div>
                    <div class="modal-body" id="myModalBody2">
                        <form class="form-horizontal" id="changform" enctype="multipart/form-data" method="post">

                            <div class="row">
                                <div class="col-xs-5 col-sm-4">验收报告状态：</div>
                                <div class="col-xs-4 col-sm-4 form-group">
                                    <select class="form-control" name="reportStatus" id="reportStatus1">
                                        <option value="1">通过</option>
                                        <option value="0">未通过</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row"  style="margin-top: 10px;">
                                <div class=" col-sm-4">验收报告图片：</div>
                                <div class="col-sm-4 form-group">
                                    <input id="file1" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                </div>
                            </div>
                            <input type="hidden" name="registrationId" id="registrationId1" value='${registrationId}'/>
                            <input type="hidden" name="employeeId" id="employeeId1" value='${sessionScope.employee.employeeId}'/>
                            <input type="hidden" name="version" id="version" value="${inspectionReport.version}"/>
                            <div class="row">
                                <div class="col-xs-6 col-sm-3 col-sm-offset-2">
                                    <button type="button" onclick="changFiles()" class="btn btn-primary">上传</button>
                                </div>
                            </div>
                        </form>

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
    $.sidebarMenu($('.sidebar-menu'));
    $('#uploadform').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            file:{
                validators:{
                    notEmpty:{
                        message:'不能为空'
                    }
                }
            }
        }
    });
    function uploadFiles(){
        $('#uploadform').bootstrapValidator('validate');
        //var bootstrapValidator = $('#login_form').data('bootstrapValidator');
        //bootstrapValidator.on('success.form.bv', function (e) {
        //e.preventDefault();
        //提交逻辑
        if($('#uploadform').data("bootstrapValidator").isValid()){
            var formData = new FormData();
            var reportStatus = $('#reportStatus').val();
            var registrationId = $('#registrationId').val();
            var employeeId = $('#employeeId').val();
            formData.append("reportStatus",reportStatus);
            formData.append("employeeId",employeeId);
            formData.append("registrationId", registrationId);
            var files = document.getElementById("file").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }

            $('#myModalBody').text("上传验收报告中，请稍后")
            $('#myModal').modal('show');
            console.log(files);

            if("undefined" != typeof(files) && files != null && files != ""){
                $.ajax({
                    url:'${pageContext.request.contextPath}/common_contract/saveInspectionReport',
                    type:'POST',
                    data:formData,
                    cache: false,
                    contentType: false, //不设置内容类型
                    processData: false, //不处理数据
                    success:function(data){
                        console.log(data);
                        var resultJson = JSON.parse(data)
                        $('#myModalBody').text(resultJson['msg']);
                        $('#myModal').on('hidden.bs.modal', function () {
                            // 执行一些动作...
                            document.location.reload();
                        })
                    },
                    error:function(){
                        $('#myModalBody').text("上传失败！");
                        $('#myModal').on('hidden.bs.modal', function () {
                            // 执行一些动作...
                            document.location.reload();
                        })
                    }
                })
            }else {
                $('#myModalBody').text("选择的文件无效！请重新选择");
                $('#myModal').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        }
    }
    $('#reset').click(function () {
        $('#uploadform').bootstrapValidator('resetForm')
    });
    $('#uploadbtn').click(function () {
        $('#reportStatus1').val('${inspectionReport.reportStatus}');
        $('#myModal2').modal('show')
    })

    $('#changform').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            file:{
                validators:{
                    notEmpty:{
                        message:'不能为空'
                    }
                }
            }
        }
    });
    function changFiles(){
        $('#changform').bootstrapValidator('validate');
        //var bootstrapValidator = $('#login_form').data('bootstrapValidator');
        //bootstrapValidator.on('success.form.bv', function (e) {
        //e.preventDefault();
        //提交逻辑
        if($('#changform').data("bootstrapValidator").isValid()){
            var formData = new FormData();
            var reportStatus = $('#reportStatus1').val();
            var registrationId = $('#registrationId1').val();
            var employeeId = $('#employeeId1').val();
            var version = $('#version').val();
            formData.append("version",version);
            formData.append("reportStatus",reportStatus);
            formData.append("employeeId",employeeId);
            formData.append("registrationId", registrationId);
            var files = document.getElementById("file1").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }

            $('#myModalBody2').text("上传验收报告中，请稍后")
            console.log(files);

            if("undefined" != typeof(files) && files != null && files != ""){
                $.ajax({
                    url:'${pageContext.request.contextPath}/common_contract/changeInspectionReport',
                    type:'POST',
                    data:formData,
                    cache: false,
                    contentType: false, //不设置内容类型
                    processData: false, //不处理数据
                    success:function(data){
                        console.log(data);
                        var resultJson = JSON.parse(data)
                        $('#myModalBody2').text(resultJson['msg']);
                        $('#myModal2').on('hidden.bs.modal', function () {
                            // 执行一些动作...
                            document.location.reload();
                        })
                    },
                    error:function(){
                        $('#myModalBody2').text("上传失败！");
                        $('#myModal2').on('hidden.bs.modal', function () {
                            // 执行一些动作...
                            document.location.reload();
                        })
                    }
                })
            }else {
                $('#myModalBody2').text("选择的文件无效！请重新选择");
                $('#myModal2').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        }
    }
    $('#delbtn').click(function () {
        var str = "<button type=\"button\" class=\"btn btn-danger\" onclick=\"enterdel('${inspectionReport.reportId}')\">确认删除</button>\n" +
            "                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭"
        $('#myModalBody').html(str);
        $('#myModal').modal('show')

    })
    function enterdel(reportId) {
        var json = {
            "reportId":reportId,
            "registrationId":${registrationId}
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_contract/delInspectionReport",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                console.log(data);
                if(data.result==1){
                    $('#myModalBody').text("删除成功！");
                }else if(data.result==2){
                    $('#myModalBody').text("删除失败！");
                }else {
                    $('#myModalBody').text(" 当前数据已被人删除！");
                }
                $('#myModal').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })

            },
            error : function(){
                $('#myModalBody').text('请重新刷新页面');
            }
        });
    }



</script>

</body>
</html>
