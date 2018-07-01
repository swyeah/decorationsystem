<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2017/12/30
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <link href="<%=basePath%>css/loginstyle.css" rel="stylesheet">
</head>
<body style="background: url(<%=basePath%>images/bg002.jpg)">

<div class="container">
    <div class="col-sm-6 col-sm-offset-3" >
        <form <%--action="<%=basePath%>login/index"--%> method="post" class="form-horizontal" id="login_form" >
            <h3 class="form-title">装修设计公司管理系统</h3>
                      <i class="glyphicon glyphicon-user fa"></i>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Username"  name="userName" maxlength="20"/>
                    </div>
                    <i class="glyphicon glyphicon-lock fa" ></i>
                    <div class="form-group">
                        <input class="form-control" type="password" placeholder="Password" name="password" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="col-sm-3" style="padding-left:0;" id="user_input_verifyCode" name="user_input_verifyCode" placeholder="验证码" maxlength="4">

                        <span class="code_img col-sm-4">
                        <img src="${pageContext.request.contextPath }/login/getVerifyCode" id="verifyCodeImage" >
                    </span>
                        <span> <a id="changeVerifImageRegister"
                                  onclick="javascript:changeImage();">换一张</a>
                    </span>
                    </div>
                <div class="form-group">
                    <label>请选择用户类型：</label>
                    <select id="identifier" name="identifier"class="form-control">
                        <option value="0">普通用户</option>
                        <option value="1">员工</option>
                    </select>
                </div>
                    <div class="form-group pull-right">
                        <input type="button" class="btn btn-primary " onclick="javascript:register();" value="注册 "/>
                        <button class="btn btn-primary" id="sub">登录</button>
                </div>
        </form>
    </div>
</div>
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
<script type="text/javascript">
    function genTimestamp() {
        var time = new Date();
        return time.getTime();
    }
    function changeImage() {
        $('#verifyCodeImage').attr('src',
            '${pageContext.request.contextPath }/login/getVerifyCode?timestamp=' + genTimestamp());
    }

    function register(){
        window.location.href="${pageContext.request.contextPath }/login/register";

    }




</script>
<script type="text/javascript">
    $(document).ready(function() {
        // Generate a simple captcha
        function randomNumber(min, max) {
            return Math.floor(Math.random() * (max - min + 1) + min);
        };
        $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

        $('#login_form').bootstrapValidator({
//        live: 'disabled',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                userName: {
                    validators: {
                        notEmpty: {
                            message: '用户不能为空！'
                        },
                        stringLength: {//检测长度
                            min: 5,
                            max: 12,
                            message: '长度必须在5-12之间'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空！'
                        },
                        stringLength: {//检测长度
                            min: 6,
                            max: 12,
                            message: '长度必须在6-12之间'
                        }
                    }

                },
                identifier:{
                    validators:{
                        notEmpty:{
                            message:'不能为空'
                        }
                    }
                },
                user_input_verifyCode:{
                    validators:{
                        notEmpty:{
                            message:'验证码不能为空！'
                        },
                        stringLength: {//检测长度
                            min: 4,
                            max: 4,
                            message: '验证码输入错误！'
                        },
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: '${pageContext.request.contextPath}/login/checkcode',//验证地址
                            data:{verifyCode:function () {
                                return $("#user_input_verifyCode").val();
                            }},
                        message: '验证码错误',//提示消息
                        delay: 1000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                       }

                    }
                }
            }
        });

        // Validate the form manually
        $('#sub').click(function() {
            $('#login_form').bootstrapValidator('validate');

             if($('#login_form').data("bootstrapValidator").isValid()){
                 var formdata = JSON.stringify($("#login_form").serializeObject());
                 $.post({
                     url: "${pageContext.request.contextPath}/login/goIndex",
                     dataType : "json",//预期服务器返回的类型c
                     contentType: "application/json",
                     data: formdata,
                     aysnc:false,
                     success : function (data) {
                         console.log(data);
                         if(data.result == 'true'){
                             window.location.href="${pageContext.request.contextPath}/welcome/index";
                         }else if(data.result == 'employee'){
                             window.location.href="${pageContext.request.contextPath}/common/consumer_management";
                         }else {
                             $('#myModalBody').text('账号或密码错误，请重新输入');
                             $('#myModal').modal('show')
                         }

                     },
                     error : function(){
                         $('#myModalBody').text('请重新刷新页面');
                         $('#myModal').modal('show')
                     }
                 });
             }

        });
        });
</script>
</body>
</html>
