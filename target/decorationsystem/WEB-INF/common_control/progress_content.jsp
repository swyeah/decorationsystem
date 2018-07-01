<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/4
  Time: 14:29
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
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>dist/js/bootstrapValidator.js"></script>
    <link href="<%=basePath%>css/indexstyle.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/nav.js"></script>
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/summernote.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/locales/summernote-zh-CN.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <!-- 初始化 自定义配置summernote js -->
    <script type="text/javascript">
        //加载编辑器及自定义配置
        var baseurl = "${pageContext.request.contextPath}";
        $(document).ready(function() {
            $('#summernote').summernote({
                height: 400,//初始化默认高度
                minHeight: null, //最小高度
                maxHeight: null, //最大高度
                focus: true,//是否定位
                lang:'zh-CN',//注意这里，若要设置语言，则需要引入该语言配置js
                placeholder:"请在这里写下您的内容",
                toolbar: [
                    ['color', ['color']],
                    ['fontsize', ['fontsize']],
                    ['para', ['paragraph']],
                    ['style', ['bold','underline', 'clear']],
                    ['insert', ['picture', 'link']],
                    ['table', ['table']],
                    ['view',['codeview','fullscreen']]
                ],//配置工具栏
                //查看更多配置(https://summernote.org/deep-dive/)
                //下面重写上传图片方法
                callbacks: {
                    // summernote提供的API：onImageUpload
                    onImageUpload:function (files) {
                        var formData = new FormData();
                        //formData.append('file',files[0]);
                        for(var i=0; i< files.length; i++){
                            formData.append("files",files[i]);   // 文件对象
                        }
                        $.ajax({
                            url : "${pageContext.request.contextPath}/common_contract/upload", //后台文件上传接口
                            type : 'POST',
                            data : formData,
                            processData : false,
                            contentType : false,
                            success : function(data) {
                                console.log(data)
                                var resultJson = JSON.parse(data)
                                console.log(resultJson)
                                for (var j =0;j<resultJson['urls'].length;j++){
                                    $('#summernote').summernote('insertImage',resultJson['urls'][j],function($image) {
                                        $image.addClass('img-responsive');
                                        $image.css('width','75%');

                                    });
                                }
                            },
                            error:function(){
                                $('#myModalBody').text("上传失败，请刷新页面")
                                $('#myModal').modal('show')
                            }
                        });
                    }
                }

            });
            $('#summernote1').summernote({
                height: 300,//初始化默认高度
                minHeight: null, //最小高度
                maxHeight: null, //最大高度
                focus: true,//是否定位
                lang:'zh-CN',//注意这里，若要设置语言，则需要引入该语言配置js
                placeholder:"请在这里写下您的内容",
                toolbar: [
                    ['color', ['color']],
                    ['fontsize', ['fontsize']],
                    ['para', ['paragraph']],
                    ['style', ['bold','underline', 'clear']],
                    ['table', ['table']],
                    ['view',['codeview','fullscreen']]
                ]//配置工具栏
                //查看更多配置(https://summernote.org/deep-dive/)
                //下面重写上传图片方法

            });
        });
    </script>
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
                    <li class="active">工程进度内容</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                工程进度内容
                            </a>
                        <li><a href="#uploadd" data-toggle="tab">添加工程进度内容</a></li>
                        </li>
                    </ul>
                    <div class="panel-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <div class="row">
                                    <div class="col-sm-2">工程进度日期</div>
                                    <div class="col-sm-3"><fmt:formatDate value="${projectTime.projectDay}" pattern="yyyy-MM-dd"/></div>
                                    <div class="col-sm-4">
                                        <button class="btn btn-primary btn-sm" id="updatebtn">修改</button>
                                        <button class="btn btn-danger btn-sm" id="delbtn">删除</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        工程进度内容
                                    </div>
                                </div>
                                <div class="row">
                                    <c:forEach items="${progressContents}" var="p">
                                        <c:if test="${progressContent.progressId==p.progressId}">
                                           <button onclick="window.location.href='${pageContext.request.contextPath}/common_contract/progressContent?projectId=${p.projectId}&progressId=${p.progressId}'" class="btn btn-success col-sm-1" style="margin-left: 10px; margin-top: 10px;">${p.progressTitle}</button>
                                        </c:if>
                                        <c:if test="${progressContent.progressId!=p.progressId}">
                                            <button onclick="window.location.href='${pageContext.request.contextPath}/common_contract/progressContent?projectId=${p.projectId}&progressId=${p.progressId}'" class="btn btn-default col-sm-1"  style="margin-left: 10px; margin-top: 10px;">${p.progressTitle}</button>
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
                            <div class="tab-pane fade" id="uploadd">
                                <form class="form-horizontal" id="showform">

                                    <div class="row">
                                        <div class="col-sm-1">
                                            标题:
                                        </div>
                                        <div class="col-sm-2 form-group">
                                            <input type="text" class="form-control" name="progressTitle" maxlength="6" placeholder="6个字小标题">
                                        </div>
                                    </div>
                                    <input hidden="hidden" name="progressContent" id="progressContent"/>
                                    <input hidden="hidden" name="projectId" id="projectId" value="${projectId}"/>
                                </form>
                                <div class="row">
                                    <div class="col-sm-2">内容:</div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-11">
                                        <div id="summernote" ></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-3 col-sm-2  col-sm-offset-4">
                                        <button id="sendform" class="btn btn-primary btn-lg" >提交</button>
                                    </div>
                                </div>
                            </div><%--upload--%>
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
                                请选择要删除的工程进度内容
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <div class="row">
                                <c:forEach items="${progressContents}" var="p">
                                        <button onclick="delpro('${p.progressId}')" class="btn btn-warning col-sm-2"  style="margin-left: 10px; margin-top: 10px;">${p.progressTitle}</button>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

            <%--弹出提示框--%>
            <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel3">
                                请选择要修改的工程进度内容
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody3">
                            <div class="row">
                                <c:forEach items="${progressContents}" var="p">
                                    <button onclick="updatepro('${p.progressId}')" class="btn btn-warning col-sm-2"  style="margin-left: 10px; margin-top: 10px;">${p.progressTitle}</button>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <%--弹出提示框--%>
            <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel4">
                                工程进度内容修改
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody4">
                            <form class="form-horizontal" id="updateform">

                                <div class="row">
                                    <div class="col-sm-2">
                                        标题:
                                    </div>
                                    <div class="col-sm-3 form-group">
                                        <input type="text" class="form-control" id="progressTitle1" name="progressTitle" maxlength="6" placeholder="6个字小标题">
                                    </div>
                                </div>
                                <input hidden="hidden" name="progressContent" id="progressContent1"/>
                                <input hidden="hidden" name="projectId" id="projectId1" value="${projectId}"/>
                                <input hidden="hidden" name="version" id="version" />
                                <input hidden="hidden" name="progressId" id="progressId1" />
                            </form>
                            <div class="row">
                                <div class="col-sm-2">内容:</div>
                            </div>
                            <div class="row">
                                <div class="col-sm-11">
                                    <div id="summernote1" ></div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="sendupdateform">发送</button>
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
        $.sidebarMenu($('.sidebar-menu'));
        $('#sendform').click(function () {
            var txt = $('#summernote').summernote('code');
            $('#progressContent').val(txt);
            var formdata = $('#showform').serializeObject();
            $.post({
                url: "${pageContext.request.contextPath}/common_contract/addprogress",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: JSON.stringify(formdata),
                success : function (data) {
                    console.log(data);
                        $('#myModalBody').html(data.msg)
                        $('#myModal').modal('show')
                    $('#myModal').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })

                },
                error : function(){
                    $('#myModalBody').text('发送失败,请刷新页面');
                    $('#myModal').modal('show')
                    $('#myModal').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }
            });

        })

        $('#delbtn').click(function () {
           $('#myModal2').modal('show')
        })

        $('#updatebtn').click(function () {
            $('#myModal3').modal('show')
        })

        function delpro(progressId) {
            var json = {
                "progressId":progressId
            }
            $.post({
                url: "${pageContext.request.contextPath}/common_contract/delprogress",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: JSON.stringify(json),
                success : function (data) {
                    console.log(data);
                    $('#myModalBody2').html(data.msg)
                    $('#myModal2').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })

                },
                error : function(){
                    $('#myModalBody2').text('发送失败,请刷新页面');
                    $('#myModal2').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }
            });

        }

        function updatepro(progressId) {
            var json = {
                "progressId":progressId
            }
            $.post({
                url: "${pageContext.request.contextPath}/common_contract/getprogress",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: JSON.stringify(json),
                success : function (data) {
                    console.log(data);
                    $('#myModal3').modal('hide')
                   $('#progressTitle1').val(data.progressContent.progressTitle)
                    $('#summernote1').summernote('code', data.progressContent.progressContent);
                    $('#version').val(data.progressContent.version)
                    $('#progressId1').val(data.progressContent.progressId)
                    $('#myModal4').modal('show')
                },
                error : function(){
                    $('#myModalBody4').text('发送失败,请刷新页面');
                    $('#myModal3').modal('hide')
                    $('#myModal4').modal('show')
                }
            });
            $('#myModal4').on('hidden.bs.modal', function () {
                // 执行一些动作...
                document.location.reload();
            })
        }
        $('#sendupdateform').click(function () {
            var txt = $('#summernote1').summernote('code');
            $('#progressContent1').val(txt);
            var formdata = $('#updateform').serializeObject();
            $.post({
                url: "${pageContext.request.contextPath}/common_contract/updateprogress",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: JSON.stringify(formdata),
                success : function (data) {
                    console.log(data);
                    $('#myModalBody4').html(data.msg)
                    $('#sendupdateform').hide()
                    $('#myModal4').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })

                },
                error : function(){
                    $('#myModalBody4').text('发送失败,请刷新页面');
                    $('#myModal4').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }
            });

        })

    </script>

</body>
</html>
