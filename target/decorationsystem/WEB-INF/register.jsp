<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2017/1/3
  Time: 1:13
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
    <link href="<%=basePath%>css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>dist/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <link href="<%=basePath%>css/loginstyle.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
</head>
<body style="background: url(<%=basePath%>images/bg002.jpg);padding-top: 3%;">

<div class="container">
    <div class="col-sm-6 col-sm-offset-3" >
        <div class="row">
            <ul class="nav nav-pills">
                <li role="presentation" class="active"><a href="<%=basePath%>login/login">返回登录</a></li>
            </ul>
        </div>
        <form method="post" class="form-horizontal" id="login_form" >
            <h3 class="form-title text-center">用户注册</h3>
            <p><span class="red">*</span>为必须填写</p>
            <div class="form-group">
                <label>用户名<span class="red">*</span> </label>
                <input class="form-control" type="text"   name="name" maxlength="12"/>
            </div>

            <div class="form-group">
                <label>账号<span class="red">*</span></label>
                <input class="form-control" type="text" placeholder="账号只能由字母、数字、点和下划线组成，长度必须在6-12之间"  name="userName" maxlength="12"/>
            </div>
            <div class="form-group">
                <label>密码<span class="red">*</span></label>
                <input class="form-control" type="password" placeholder="密码长度必须在6-12之间"  name="password" maxlength="12"/>
            </div>
            <div class="form-group">
                <label>确认密码<span class="red">*</span></label>
                <input class="form-control" type="password" placeholder="请确认密码"  name="checkpassword" maxlength="12"/>
            </div>
            <div class="form-group">
                <label for="birth">出生日期<span class="red">*</span></label>
                <div class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" id="birth" name="birth" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
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
                        startDate: "1949-10-01"
                    });
                </script>
            </div>
            <div class="form-group">
                <label>身份证号码<span class="red">*</span></label>
                <input class="form-control" type="text" placeholder="仅限18位数字"  name="idCard" maxlength="18"/>
            </div>
            <div class="form-group">
                <label>住址<span class="red">*</span></label>
                <input class="form-control" type="text" placeholder="长度为40"  name="address" maxlength="40"/>
            </div>
            <div class="form-group">
                <label>邮箱</label>
                <input class="form-control" type="text" placeholder="例如xxx.xxx@xx.com"  name="email"/>
            </div>
            <div class="form-group">
                <label>手机<span class="red">*</span></label>
                <input class="form-control" type="text" placeholder="仅限11位数字"  name="phone" maxlength="11"/>
            </div>

            <div class="form-group text-center">
                    <input type="reset" class="btn btn-primary " id="reset"  value="重置 "/>
                    <button class="btn btn-primary" id="sub">注册</button>
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
                name: {
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空！'
                        },
                        stringLength: {//检测长度
                            min: 1,
                            max: 12,
                            message: '用户名长度必须在1-12之间！'
                        },

                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: '${pageContext.request.contextPath}/login/register/checkName',//验证地址
                            data:{name:function () {
                                return $("#name").val();
                            }},
                            message: '该用户名已存在',//提示消息
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
                            url: '${pageContext.request.contextPath}/login/register/checkUserName',//验证地址
                            data:{userName:function () {
                                return $("#userName").val();
                            }},
                            message: '该账号已存在',//提示消息
                            delay: 1000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'//请求方式
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
                            message: '密码长度必须在6-12之间！'
                        }
                    }

                },
                checkpassword:{
                    validators:{
                        identical: {
                            field: 'password',
                            message: '密码不一致！'
                        },
                        notEmpty:{
                            message:'请确认密码！'
                        }
                    }

                },
                birth:{
                    validators: {
                        notEmpty: {
                            message: '出生日期不能为空！'
                        }
                    }

                },
                idCard:{
                    validators: {
                        notEmpty: {
                            message: '身份证号码不能为空！'
                        },
                        stringLength: {//检测长度
                            min: 18,
                            max: 18,
                            message: '身份证号码必须为18位数字！'
                        },
                        digits: {
                            message: '身份证号码只能包含数字。'
                        }
                    }

                },
                address:{
                    validators:{
                        notEmpty:{
                            message:'住址不能为空！'
                        },
                        stringLength: {//检测长度
                            max: 40,
                            message: '住址长度不能超过40！'
                        }
                    }
                },
                email:{
                    validators:{
                        emailAddress: {
                            message: '邮箱格式错误！'
                        }
                    }
                },
                phone:{
                    validators:{
                        notEmpty: {
                            message: '手机号码不能为空！'
                        },
                        stringLength: {//检测长度
                            min: 11,
                            max: 11,
                            message: '手机证号码必须为11位数字！'
                        },
                        digits: {
                            message: '手机号码只能包含数字。'
                        }
                    }
                }
            }
        });
        $('#reset').click(function () {
            $('#login_form').bootstrapValidator('resetForm')
        });
        $('#sub').click(function() {
            $('#login_form').bootstrapValidator('validate');
            if($('#login_form').data("bootstrapValidator").isValid()){

                var formdata = JSON.stringify($("#login_form").serializeObject());
                var formjson = JSON.parse(formdata)
                delete formjson['checkpassword'];
                var formdata2 = JSON.stringify(formjson);
                $.post({
                    url: "${pageContext.request.contextPath}/login/doregister",
                    dataType : "json",//预期服务器返回的类型c
                    contentType: "application/json",
                    data: formdata2,
                    aysnc:true,
                    success : function (data) {
                        console.log(data);
                        if(data.result == 'true'){
                            //window.location.href="${pageContext.request.contextPath}/login/login";

                            $('#myModalBody').html("<a href='${pageContext.request.contextPath}/login/login'>注册成功，请点击前往登录</a>");
                            $('#myModal').modal('show')
                        }else {
                            $('#myModalBody').text('注册失败,请重新注册'+message);
                            $('#myModal').modal('show')
                        }

                    },
                    error : function(){
                        $('#myModalBody').text('已存在该账号或者用户名,请重新注册');
                        $('#myModal').modal('show')
                    }
                });
            }
        });

    });
</script>
</body>
</html>
