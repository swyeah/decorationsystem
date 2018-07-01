<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/20
  Time: 9:20
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
                    <li class="active">合同</li>
                </ol>
            </div>
            <div class="row" style="font-size: 18px;">
                <div class="panel panel-primary" >
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li><a href="${pageContext.request.contextPath}/common_deposit/housepic?registrationId=${registrationId}&employeeId=${employeeId}">户型图</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_deposit/designerpic?registrationId=${registrationId}&employeeId=${employeeId}">设计图</a></li>
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                合同
                            </a>
                        </li>
                        <c:forEach items="${sessionScope.employee.job.myAuths}" var="m">
                            <c:if test="${m.authId==59}">
                        <li><a href="#uploadd" data-toggle="tab">上传合同</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <div class="panel-body" >
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <c:if test="${requestScope.resultStatus == true}">
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-4">合同编号：${contract.contractId}</div>
                                        <c:forEach items="${sessionScope.employee.job.myAuths}" var="m">
                                        <c:if test="${m.authId==59}">
                                        <div class="col-sm-4">
                                            <button class="btn btn-primary" type="button" id="uploadbtn">更新</button>
                                            <button class="btn btn-danger" type="button" id="delbtn">删除</button>
                                        </div>
                                        </c:if>
                                        </c:forEach>
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
                                    <p>无合同，请上传合同</p>
                                </c:if>
                            </div>
                            <div class="tab-pane fade" id="uploadd">
                                <form class="form-horizontal" id="uploadform" enctype="multipart/form-data" method="post">

                                    <div class="row">
                                        <div class="col-xs-4 col-sm-2">合同名字：</div>
                                        <div class="col-xs-4 col-sm-2">
                                            <div class="form-group">
                                                <input id="contractName" name="contractName" class="form-control" type="text"/>
                                            </div>
                                        </div>
                                        <div class="col-xs-4 col-sm-2">合同金额：</div>
                                        <div class="col-xs-4 col-sm-2">
                                            <div class="input-group form-group">
                                                <input id="contractMoney" name="contractMoney" class="form-control" type="text"/>
                                                <span class="input-group-addon">RMB</span>
                                            </div>
                                        </div>
                                        <div class="col-xs-4 col-sm-2">装修模式：</div>
                                        <div class="col-xs-4 col-sm-2">
                                            <div class="form-group">
                                                <select id="modeId" name="modeId" class="form-control">
                                                    <c:forEach items="${decorationModes}" var="d">
                                                        <option value="${d.modeId}">${d.modeName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row"  style="margin-top: 10px;">
                                        <div class="col-xs-4 col-sm-2">开工日期：</div>
                                        <div class="col-sm-2">
                                            <div class="input-group form-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                                <input class="form-control" id="startTime"  name="startTime" size="16" type="text" value="" readonly>
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
                                        <div class="col-xs-4 col-sm-2">竣工日期：</div>
                                        <div class="col-sm-2">
                                            <div class="input-group form-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                                <input class="form-control" id="endTime" name="endTime" size="16" type="text" value="" readonly>
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
                                    </div>
                                    <div class="row"  style="margin-top: 10px;">
                                        <div class="col-xs-4 col-sm-2">合同图片：</div>
                                        <div class="col-xs-4 col-sm-3 form-group">
                                            <input id="file" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top:10px;">
                                        <div class="col-xs-4 col-sm-2">合同描述：</div>
                                        <div class="col-xs-8 col-sm-3 form-group"><textarea class="form-control" cols="5" id="contractDesribe" name="contractDesribe"></textarea> </div>
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
                                合同更新
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <form class="form-horizontal" id="changform" enctype="multipart/form-data" method="post">

                                <div class="row">
                                    <div class="col-sm-3">合同名字：</div>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <input id="contractName1" name="contractName" class="form-control" type="text"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">合同金额：</div>
                                    <div class="col-sm-3">
                                        <div class="input-group form-group">
                                            <input id="contractMoney1" name="contractMoney" class="form-control" type="text"/>
                                            <span class="input-group-addon">RMB</span>
                                        </div>
                                    </div>

                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-3">装修模式：</div>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <select id="modeId1" name="modeId" class="form-control">
                                                <c:forEach items="${decorationModes}" var="d">
                                                    <option value="${d.modeId}">${d.modeName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row"  style="margin-top: 10px;">
                                    <div class="col-sm-2">开工日期：</div>
                                    <div class="col-sm-4">
                                        <div class="input-group form-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                                            <input class="form-control" id="startTime1"  name="startTime" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>

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
                                    <div class="col-sm-2">竣工日期：</div>
                                    <div class="col-sm-4">
                                        <div class="input-group form-group date form_date" data-date="" data-date-format="yyyy-mm-dd" >
                                            <input class="form-control" id="endTime1" name="endTime" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
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
                                </div>
                                <div class="row"  style="margin-top: 10px;">
                                    <div class=" col-sm-3">合同图片：</div>
                                    <div class="col-sm-3 form-group">
                                        <input id="file1" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:10px;">
                                    <div class="col-sm-3">合同描述：</div>
                                    <div class="col-sm-6 form-group"><textarea class="form-control" cols="5" id="contractDesribe1" name="contractDesribe"></textarea> </div>
                                </div>
                                <input type="hidden" name="registrationId" id="registrationId1" value='${registrationId}'/>
                                <input type="hidden" name="version" id="version" value='${contract.version}'/>
                                <input type="hidden" name="contractId" id="contractId" value='${contract.contractId}'/>
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
            contractName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 100,
                        message: '长度必须在1-100之间！'
                    }
                }
            },
            startTime: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    }
                }
            },
            endTime: {
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
            contractMoney: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    regexp: {
                        regexp: /^\d+(\.\d{1,2})?$/,
                        message: '金额只能是数字，带两位小数'
                    }
                }

            },
            contractDesribe:{
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
            var contractName = $('#contractName').val();
            var startTime = $('#startTime').val();
            var registrationId = $('#registrationId').val();
            var endTime = $('#endTime').val();
            var contractMoney = $('#contractMoney').val();
            var contractDesribe = $('#contractDesribe').val();
            var modeId = $('#modeId').val();
            formData.append("contractName",contractName);
            formData.append("startTime",startTime);
            formData.append("registrationId", registrationId);
            formData.append("endTime", endTime);
            formData.append("contractMoney", contractMoney);
            formData.append("contractDesribe", contractDesribe);
            formData.append("modeId", modeId);
            var files = document.getElementById("file").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }

           $('#myModalBody').text("上传合同中，请稍后")
            $('#myModal').modal('show');
            console.log(files);

            if("undefined" != typeof(files) && files != null && files != ""){
                $.ajax({
                    url:'${pageContext.request.contextPath}/common_deposit/saveContract',
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
        $('#contractName1').val('${contract.contractName}');
        $('#startTime1').val('${contract.startTime}');
        $('#endTime1').val('${contract.endTime}');
        $('#contractMoney1').val('${contract.contractMoney}');
        $('#contractDesribe1').val('${contract.contractDescribe}');
        $('#modeId1').val('${contract.decorationMode.modeId}');
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
            contractName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 100,
                        message: '长度必须在1-100之间！'
                    }
                }
            },
            startTime: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    }
                }
            },
            endTime: {
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
            contractMoney: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    regexp: {
                        regexp: /^\d+(\.\d{1,2})?$/,
                        message: '金额只能是数字，带两位小数'
                    }
                }

            },
            contractDesribe:{
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
            var contractName = $('#contractName1').val();
            var startTime = $('#startTime1').val();
            var registrationId = $('#registrationId1').val();
            var endTime = $('#endTime1').val();
            var contractMoney = $('#contractMoney1').val();
            var contractDesribe = $('#contractDesribe1').val();
            var modeId = $('#modeId1').val();
            var version = $('#version').val();
            var contractId = $('#contractId').val();
            formData.append("contractId",contractId);
            formData.append("version",version);
            formData.append("contractName",contractName);
            formData.append("startTime",startTime);
            formData.append("registrationId", registrationId);
            formData.append("endTime", endTime);
            formData.append("contractMoney", contractMoney);
            formData.append("contractDesribe", contractDesribe);
            formData.append("modeId", modeId);
            var files = document.getElementById("file1").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }

            $('#myModalBody2').text("上传合同中，请稍后")
            console.log(files);

            if("undefined" != typeof(files) && files != null && files != ""){
                $.ajax({
                    url:'${pageContext.request.contextPath}/common_deposit/changeContract',
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
        var str = "<button type=\"button\" class=\"btn btn-danger\" onclick=\"enterdel('${contract.contractId}')\">确认删除</button>\n" +
            "                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭"
        $('#myModalBody').html(str);
        $('#myModal').modal('show')

    })
    function enterdel(contractId) {
        var json = {
            "contractId":contractId,
            "registrationId":${registrationId}
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_deposit/delContract",
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


    $('#uploadbtna').click(function () {
        $('#contractName1').val('${contract.contractName}');
        $('#startTime1').val('${contract.startTime}');
        $('#endTime1').val('${contract.endTime}');
        $('#contractMoney1').val('${contract.contractMoney}');
        $('#contractDesribe1').val('${contract.contractDescribe}');
        $('#modeId1').val('${contract.decorationMode.modeId}');
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
            contractName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 100,
                        message: '长度必须在1-100之间！'
                    }
                }
            },
            startTime: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    }
                }
            },
            endTime: {
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
            contractMoney: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    regexp: {
                        regexp: /^\d+(\.\d{1,2})?$/,
                        message: '金额只能是数字，带两位小数'
                    }
                }

            },
            contractDesribe:{
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
    function newsFiles(){
        $('#changform').bootstrapValidator('validate');
        //var bootstrapValidator = $('#login_form').data('bootstrapValidator');
        //bootstrapValidator.on('success.form.bv', function (e) {
        //e.preventDefault();
        //提交逻辑
        if($('#changform').data("bootstrapValidator").isValid()){
            var formData = new FormData();
            var contractName = $('#contractName1').val();
            var startTime = $('#startTime1').val();
            var registrationId = $('#registrationId1').val();
            var endTime = $('#endTime1').val();
            var contractMoney = $('#contractMoney1').val();
            var contractDesribe = $('#contractDesribe1').val();
            var modeId = $('#modeId1').val();
            var version = $('#version').val();
            var contractId = $('#contractId').val();
            formData.append("contractId",contractId);
            formData.append("version",version);
            formData.append("contractName",contractName);
            formData.append("startTime",startTime);
            formData.append("registrationId", registrationId);
            formData.append("endTime", endTime);
            formData.append("contractMoney", contractMoney);
            formData.append("contractDesribe", contractDesribe);
            formData.append("modeId", modeId);
            var files = document.getElementById("file1").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }

            $('#myModalBody2').text("上传合同中，请稍后")
            console.log(files);

            if("undefined" != typeof(files) && files != null && files != ""){
                $.ajax({
                    url:'${pageContext.request.contextPath}/common_deposit/newsContract',
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
    $('#setstatusbtn').click(function () {
        var json = {
            contractId:'${contract.contractId}',
            version:'${contract.version}'
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_deposit/setcontractStatus",
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
            url: "${pageContext.request.contextPath}/common_deposit/setcontractStatusFalse",
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
