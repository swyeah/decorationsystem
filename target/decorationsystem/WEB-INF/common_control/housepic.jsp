<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/20
  Time: 22:57
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
                    <li class="active">户型图</li>
                </ol>
            </div>
            <div class="row" style="font-size: 18px;">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                户型图
                            </a>
                        </li>
                        <c:forEach items="${sessionScope.employee.job.myAuths}" var="m">
                            <c:if test="${m.authId==57}">
                        <li><a href="#uploadd" data-toggle="tab">上传户型图</a></li>
                            </c:if>
                        </c:forEach>
                        <li><a href="${pageContext.request.contextPath}/common_deposit/designerpic?registrationId=${registrationId}&employeeId=${employeeId}">设计图</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_deposit/contract_approval?registrationId=${registrationId}&employeeId=${employeeId}">合同</a></li>
                    </ul>
                    <div class="panel-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <c:if test="${requestScope.resultStatus == true}">
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-4">户型图编号：${housepic.housepicId}</div>
                                        <div class="col-sm-4">户型图名字：${housepic.housepicName}</div>
                                        <c:forEach items="${sessionScope.employee.job.myAuths}" var="m">
                                        <c:if test="${m.authId==57}">
                                        <div class="col-sm-4">
                                            <button class="btn btn-primary" type="button" id="uploadbtn">更新</button>
                                            <button class="btn btn-danger" type="button" id="delbtn">删除</button>
                                        </div>
                                        </c:if>
                                        </c:forEach>

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
                            <div class="tab-pane fade" id="uploadd">
                                <form class="form-horizontal" id="uploadform" enctype="multipart/form-data" method="post">

                                    <div class="row">
                                        <div class="col-xs-4 col-sm-2">户型图名字：</div>
                                        <div class="col-xs-4 col-sm-2">
                                            <div class="form-group">
                                                <input id="housepicName" name="housepicName" class="form-control" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row"  style="margin-top: 10px;">
                                        <div class="col-xs-4 col-sm-2">户型类型：</div>
                                        <div class="col-xs-4 col-sm-3 form-group">
                                            <select class="form-control" id="houseId" name="houseId">
                                                <c:forEach items="${houseTypes}" var="h">
                                                    <option value="${h.houseId}">${h.houseName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="col-xs-4 col-sm-2 col-sm-offset-1">装修面积：</div>
                                        <div class="col-xs-8 col-sm-3 form-group">
                                            <select class="form-control" id="areaId" name="areaId">
                                                <c:forEach items="${areaTypes}" var="a">
                                                    <option value="${a.areaId}">${a.areaName}平米</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                        <div class="row"  style="margin-top: 10px;">
                                            <div class="col-xs-4 col-sm-2">户型图片：</div>
                                            <div class="col-xs-4 col-sm-3 form-group">
                                                <input id="file" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                            </div>
                                        </div>
                                        <div class="row" style="margin-top:10px;">
                                            <div class="col-xs-4 col-sm-2">户型图描述：</div>
                                            <div class="col-xs-8 col-sm-3 form-group"><textarea class="form-control" cols="5" id="housepicDescrible" name="housepicDescrible"></textarea> </div>
                                        </div>
                                    <input type="hidden" name="registrationId" id="registrationId" value='${registrationId}'/>
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
                                户型图更新
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <form class="form-horizontal" id="changform" enctype="multipart/form-data" method="post">

                                <div class="row">
                                    <div class="col-sm-8">户型图编号：${housepic.housepicId}</div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">户型图名字：</div>
                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <input id="housepicName1" name="housepicName" class="form-control" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row"  style="margin-top: 10px;">
                                    <div class="col-sm-3">户型类型：</div>
                                    <div class="col-sm-3 form-group">
                                        <select class="form-control" id="houseId1" name="houseId">
                                            <c:forEach items="${houseTypes}" var="h">
                                                <option value="${h.houseId}">${h.houseName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-sm-3">装修面积：</div>
                                    <div class=" col-sm-4 form-group">
                                        <select class="form-control" id="areaId1" name="areaId">
                                            <c:forEach items="${areaTypes}" var="a">
                                                <option value="${a.areaId}">${a.areaName}平米</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-8">
                                        户型图片：${housepicFiles.size()}张图片
                                    </div>
                                </div>
                                <div class="row"  style="margin-top: 10px;">
                                    <div class="col-xs-4 col-sm-3">户型图片：</div>
                                    <div class="col-xs-4 col-sm-4 form-group">
                                        <input id="file1" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:10px;">
                                    <div class="col-sm-3">户型图描述：</div>
                                    <div class="col-sm-6 form-group"><textarea class="form-control" cols="5" id="housepicDescrible1" name="housepicDescrible"></textarea> </div>
                                </div>
                                <input type="hidden" name="housepicId" id="housepicId" value="${housepic.housepicId}">
                                <input type="hidden" name="registrationId" id="registrationId1" value="${housepic.registrationId}">
                                <input type="hidden" name="version" id="version" value="${housepic.version}">
                                <div class="row">
                                    <div class="col-sm-5 col-sm-offset-3">
                                        <button type="button" onclick="changFiles()" class="btn btn-primary">更新</button>
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
            housepicName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    }
                }
            },
            file:{
                validators:{
                    notEmpty:{
                        message:'不能为空'
                    }
                }
            },
            housepicDescrible:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
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
        var housepicName = $('#housepicName').val();
        var housepicDescrible = $('#housepicDescrible').val();
        var registrationId = $('#registrationId').val();
        var houseId = $('#houseId').val();
        var areaId = $('#areaId').val();
        formData.append("housepicName",housepicName);
        formData.append("housepicDescrible",housepicDescrible);
        formData.append("houseId", houseId);
        formData.append("areaId", areaId);
        formData.append("registrationId", registrationId);
            var files = document.getElementById("file").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }


            console.log(files);

        if("undefined" != typeof(files) && files != null && files != ""){
            $.ajax({
                url:'${pageContext.request.contextPath}/common_deposit/saveHousepic',
                type:'POST',
                data:formData,
                cache: false,
                contentType: false, //不设置内容类型
                processData: false, //不处理数据
                success:function(data){
                    console.log(data);
                    var resultJson = JSON.parse(data)
                    $('#myModalBody').text(resultJson['msg']);
                    $('#myModal').modal('show');
                    $('#myModal').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                },
                error:function(){
                    $('#myModalBody').text("上传失败！");
                    $('#myModal').modal('show')
                }
            })
        }else {
            $('#myModalBody').text("选择的文件无效！请重新选择");
            $('#myModal').modal('show')
        }
        }
    }
    $('#reset').click(function () {
        $('#uploadform').bootstrapValidator('resetForm')
    });

    $('#uploadbtn').click(function () {
        $('#housepicName1').val('${housepic.housepicName}');
        $('#housepicDescrible1').val('${housepic.housepicDescrible}');
        $('#houseId1').val('${housepic.houseType.houseId}');
        $('#areaId1').val('${housepic.areaType.areaId}');
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
            housepicName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    }
                }
            },
            file:{
                validators:{
                    notEmpty:{
                        message:'不能为空'
                    }
                }
            },
            housepicDescrible:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
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
            var housepicName = $('#housepicName1').val();
            var housepicDescrible = $('#housepicDescrible1').val();
            var housepicId = $('#housepicId').val();
            var houseId = $('#houseId1').val();
            var areaId = $('#areaId1').val();
            var registrationId =$('#registrationId1').val();
            var version = $('#version').val();
            formData.append("registrationId", registrationId);
            formData.append("housepicName",housepicName);
            formData.append("housepicDescrible",housepicDescrible);
            formData.append("houseId", houseId);
            formData.append("areaId", areaId);
            formData.append("housepicId", housepicId);
            formData.append("version", version);
            var files = document.getElementById("file1").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }

            console.log(formData);

            if("undefined" != typeof(files) && files != null && files != ""){
                $.ajax({
                    url:'${pageContext.request.contextPath}/common_deposit/changeHousepic',
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
                    }
                })
            }else {
                $('#myModalBody2').text("选择的文件无效！请重新选择");
            }
        }
    }
    $('#delbtn').click(function () {
        var str = "<button type=\"button\" class=\"btn btn-danger\" onclick=\"enterdel('${housepic.housepicId}')\">确认删除</button>\n" +
            "                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭"
        $('#myModalBody').html(str);
        $('#myModal').modal('show')

    })
    function enterdel(housepicId) {
        var json = {
            "housepicId":housepicId,
            "registrationId":${registrationId}
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_deposit/delHousepic",
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
