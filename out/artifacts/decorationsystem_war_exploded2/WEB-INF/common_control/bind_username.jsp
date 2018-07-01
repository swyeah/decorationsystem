<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/9/9
  Time: 10:18
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
        .row{margin-bottom: 10px;}
        .red{
            font-size:120%;
            color: red;
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
        <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-0" style="padding-bottom: 50px;">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li class="active">绑定账号</li>
                </ol>
            </div>
            <div class="row" style="font-size: 18px;">
                <div class="panel panel-primary">

                    <div class="panel-body">
                       <form id="login_form">
                           <div class="row">
                               <div class="col-sm-2">订单登记编号：</div>
                               <div class="col-sm-4 form-group">
                                   <input class="form-control" type="text" name="registrationId" maxlength="12"/>
                               </div>
                           </div>
                           <div class="row">
                               <div class="col-sm-2">用户账号：</div>
                               <div class="col-sm-4 form-group">
                                   <input class="form-control" type="text" name="userName" maxlength="12"/>
                               </div>
                               <input hidden="hidden" name="status" id="status" value="0">
                           </div>
                           <div class="row">
                               <div class="form-group col-sm-4 col-sm-offset-4">
                                   <button class="btn btn-primary" id="sub" onclick="changeRegistration()">提交</button>
                                   <input type="reset" class="btn btn-default " id="reset"  value="重置 "/>
                               </div>
                           </div>
                       </form>
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
<script>
    $.sidebarMenu($('.sidebar-menu'));
    $('#login_form').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            registrationId: {
                validators: {
                    notEmpty: {
                        message: '登记编号不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 6,
                        max: 10,
                        message: '登记编号长度必须在6-10之间！'
                    },
                    remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                        url: '${pageContext.request.contextPath}/common/checkRegistrationId',//验证地址
                        data:{registrationId:function () {
                            return $("#registrationId").val();
                        }},
                        message: '该登记编号不存在，无法绑定！',//提示消息
                        delay: 1000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }
                }

            },
            userName: {
                validators: {
                    notEmpty: {
                        message: '账号不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 6,
                        max: 12,
                        message: '账号长度必须在6-12之间！'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '账号只能由字母、数字、点和下划线组成！'
                    },
                    remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                        url: '${pageContext.request.contextPath}/common/checkUserName',//验证地址
                        data:{userName:function () {
                            return $("#userName").val();
                        }},
                        message: '该账号不存在，无法绑定！',//提示消息
                        delay: 1000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }
                }
            }
        }
    });
    $('#reset').click(function () {
        $('#login_form').bootstrapValidator('resetForm')
    });
    function changeRegistration() {
        $('#login_form').bootstrapValidator('validate');
        if($('#login_form').data("bootstrapValidator").isValid()){

            var formdata = JSON.stringify($("#login_form").serializeObject());
            $.post({
                url: "${pageContext.request.contextPath}/common/changeRegistrationId",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: formdata,
                aysnc:true,
                success : function (data) {
                    console.log(data);
                    if (data.resultstatus==0){
                        $('#myModalBody').text(data.msg);
                    }else{
                        $('#status').val(1)
                        $('#myModalBody').html(data.msg);
                    }

                    $('#myModal').modal('show')
                    $('#myModal').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })

                },
                error : function(){
                    $('#myModalBody').text('请重新刷新页面！');
                    $('#myModal').modal('show')
                }
            });
        }
    };
</script>

</div>

</body>
</html>
