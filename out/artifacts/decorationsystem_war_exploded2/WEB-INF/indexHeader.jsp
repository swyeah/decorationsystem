<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/9
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
    .navbar-default {
        background-color: #2394ee;
        border-color: #d199f7;
    }
    .navbar-default .navbar-brand {
        color: #ffffff;
    }
    .navbar-default .navbar-brand:hover,
    .navbar-default .navbar-brand:focus {
        color: #fffcfc;
    }
    .navbar-default .navbar-text {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > a {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > a:hover,
    .navbar-default .navbar-nav > li > a:focus {
        color: #fffcfc;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu {
        background-color: #2394ee;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu > li > a {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu > li > a:hover,
    .navbar-default .navbar-nav > li > .dropdown-menu > li > a:focus {
        color: #fffcfc;
        background-color: #d199f7;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu > li.divider {
        background-color: #d199f7;
    }
    .navbar-default .navbar-nav .open .dropdown-menu > .active > a,
    .navbar-default .navbar-nav .open .dropdown-menu > .active > a:hover,
    .navbar-default .navbar-nav .open .dropdown-menu > .active > a:focus {
        color: #fffcfc;
        background-color: #d199f7;
    }
    .navbar-default .navbar-nav > .active > a,
    .navbar-default .navbar-nav > .active > a:hover,
    .navbar-default .navbar-nav > .active > a:focus {
        color: #fffcfc;
        background-color: #d199f7;
    }
    .navbar-default .navbar-nav > .open > a,
    .navbar-default .navbar-nav > .open > a:hover,
    .navbar-default .navbar-nav > .open > a:focus {
        color: #fffcfc;
        background-color: #d199f7;
    }
    .navbar-default .navbar-toggle {
        border-color: #d199f7;
    }
    .navbar-default .navbar-toggle:hover,
    .navbar-default .navbar-toggle:focus {
        background-color: #d199f7;
    }
    .navbar-default .navbar-toggle .icon-bar {
        background-color: #ffffff;
    }
    .navbar-default .navbar-collapse,
    .navbar-default .navbar-form {
        border-color: #ffffff;
    }
    .navbar-default .navbar-link {
        color: #ffffff;
    }
    .navbar-default .navbar-link:hover {
        color: #fffcfc;
    }

    @media (max-width: 767px) {
        .navbar-default .navbar-nav .open .dropdown-menu > li > a {
            color: #ffffff;
        }
        .navbar-default .navbar-nav .open .dropdown-menu > li > a:hover,
        .navbar-default .navbar-nav .open .dropdown-menu > li > a:focus {
            color: #fffcfc;
        }
        .navbar-default .navbar-nav .open .dropdown-menu > .active > a,
        .navbar-default .navbar-nav .open .dropdown-menu > .active > a:hover,
        .navbar-default .navbar-nav .open .dropdown-menu > .active > a:focus {
            color: #fffcfc;
            background-color: #d199f7;
        }
    }
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
    .row{
        margin: 0 0 0 0;
    }
</style>
<%--顶部导航条--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" >装修设计公司管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <c:if test="${sessionScope.consumer==null}">
                <div class="col-sm-2 pull-right">
                    <span class="write">欢迎，请先登陆！</span>
                    <button type="button" class="btn btn-default navbar-btn" onclick="window.location.href='${pageContext.request.contextPath}/login/login'">登陆</button>
                </div>
            </c:if>
            <c:if test="${sessionScope.consumer!=null}">
                <div class="col-sm-5 pull-right">
                    <ul class="nav navbar-nav navbar-right">

                        <li><a href="${pageContext.request.contextPath}/welcome/index_register" style="background: rgba(12,54,35,0.1)">一切从这里开始<i class="glyphicon glyphicon-pencil"></i> </a></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/welcome/myproject">我的项目<i class="glyphicon glyphicon-cog"></i></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.consumer.name} <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a id="logout">退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div><!-- /.container-fluid -->
</nav>
<script>
    $('#logout').click(function () {
        $.post({
            url: "${pageContext.request.contextPath}/welcome/logout",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: null,
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