<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/6
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"%>
<style type="text/css">
    .navbar-default {
        background-color: #2564c5;
        border-color: #1751d8;
    }
    .navbar-default .navbar-brand {
        color: #ffffff;
    }
    .navbar-default .navbar-brand:hover,
    .navbar-default .navbar-brand:focus {
        color: #f9fbfc;
    }
    .navbar-default .navbar-text {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > a {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > a:hover,
    .navbar-default .navbar-nav > li > a:focus {
        color: #f9fbfc;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu {
        background-color: #2564c5;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu > li > a {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu > li > a:hover,
    .navbar-default .navbar-nav > li > .dropdown-menu > li > a:focus {
        color: #f9fbfc;
        background-color: #1751d8;
    }
    .navbar-default .navbar-nav > li > .dropdown-menu > li.divider {
        background-color: #1751d8;
    }
    .navbar-default .navbar-nav .open .dropdown-menu > .active > a,
    .navbar-default .navbar-nav .open .dropdown-menu > .active > a:hover,
    .navbar-default .navbar-nav .open .dropdown-menu > .active > a:focus {
        color: #f9fbfc;
        background-color: #1751d8;
    }
    .navbar-default .navbar-nav > .active > a,
    .navbar-default .navbar-nav > .active > a:hover,
    .navbar-default .navbar-nav > .active > a:focus {
        color: #f9fbfc;
        background-color: #1751d8;
    }
    .navbar-default .navbar-nav > .open > a,
    .navbar-default .navbar-nav > .open > a:hover,
    .navbar-default .navbar-nav > .open > a:focus {
        color: #f9fbfc;
        background-color: #1751d8;
    }
    .navbar-default .navbar-toggle {
        border-color: #1751d8;
    }
    .navbar-default .navbar-toggle:hover,
    .navbar-default .navbar-toggle:focus {
        background-color: #1751d8;
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
        color: #f9fbfc;
    }

    @media (max-width: 767px) {
        .navbar-default .navbar-nav .open .dropdown-menu > li > a {
            color: #ffffff;
        }
        .navbar-default .navbar-nav .open .dropdown-menu > li > a:hover,
        .navbar-default .navbar-nav .open .dropdown-menu > li > a:focus {
            color: #f9fbfc;
        }
        .navbar-default .navbar-nav .open .dropdown-menu > .active > a,
        .navbar-default .navbar-nav .open .dropdown-menu > .active > a:hover,
        .navbar-default .navbar-nav .open .dropdown-menu > .active > a:focus {
            color: #f9fbfc;
            background-color: #1751d8;
        }
    }
</style>
<%--顶部导航条--%>
<nav class="navbar navbar-default navbar-fixed-top ">
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

            <ul class="nav navbar-nav navbar-right visible-xs">
                <li >
                    <a href="${pageContext.request.contextPath}/common/consumer_register" >
                        <i class="glyphicon glyphicon-pencil"></i>
                        <span>登记新客户</span>
                    </a>
                </li>
                <li >
                    <a href="${pageContext.request.contextPath}/common/bind_username" >
                        <i class="glyphicon glyphicon-pencil"></i>
                        <span>绑定客户账号</span>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-list-alt"></i> <span>客户管理</span> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/common/consumer_management"></i> 登记客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_intent/consumer_management_intent">意向客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_deposit/consumer_management_deposit"></i> 协议客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_contract/common_management_contract"> 合同客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly"> 飞单客户</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-picture"></i>
                        <span>图纸管理</span><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/picture_management/house_management">户型图管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/picture_management/designerpic_management">设计图管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/picture_management/contract_management">合同管理</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-picture"></i>
                        <span>设计展示管理</span><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/designer_show/designer_up">设计图管理</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-wrench"></i>
                        <span>材料管理</span><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/material_management/material">材料管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/material_offer_management/materialOffer"> 供应商管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialClazz">材料大类型管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz">材料小类型管理</a></li>
                    </ul>
                </li>
                <li >
                    <a href="${pageContext.request.contextPath}/common/resetpassword">
                        <i class="glyphicon glyphicon-edit"></i> <span>修改密码</span>
                    </a>
                </li>
                <li >
                    <a onclick="logout()">
                        <i class="glyphicon glyphicon-user"></i> <span>退出登录</span>
                    </a>
                </li>
            </ul>
            <div class="nav navbar-nav navbar-right col-sm-2">
                <li><a href="#">${sessionScope.employee.job.department.departmentName}&nbsp;&nbsp;${sessionScope.employee.job.jobName}&nbsp;&nbsp;${sessionScope.employee.employeeName}</a></li>
                </div>

        </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->

</nav>
<%--侧边导航条--%>
<div class="hidden-xs" style="position: fixed;top: 50px;left: 0; width: 15%;height: 100%;background: #2c3b41;">
    <aside class="main-sidebar">
        <section  class="sidebar">
            <ul class="sidebar-menu">
                <li class="header">主导航</li>
                    <li class="treeview" >
                        <a href="${pageContext.request.contextPath}/common/consumer_register">
                            <i class="glyphicon glyphicon-pencil"></i>
                            <span>登记新客户</span>
                        </a>

                    </li>
                <li class="treeview">
                    <a href="${pageContext.request.contextPath}/common/bind_username">
                        <i class="glyphicon glyphicon-pencil"></i>
                        <span>绑定客户账号</span>
                    </a>

                </li>
                <li>
                    <a href="#" >
                        <i class="glyphicon glyphicon-list-alt"></i> <span>客户管理</span>
                    </a>
                    <ul class="treeview-menu"  >
                        <li ><a href="${pageContext.request.contextPath}/common/consumer_management"></i> 登记客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_intent/consumer_management_intent">意向客户</a></li>
                        <li ><a href="${pageContext.request.contextPath}/common_deposit/consumer_management_deposit"></i> 协议客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_contract/common_management_contract"> 合同客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_over/common_management_over"> 完工客户</a></li>
                        <li><a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly"> 飞单客户</a></li>
                    </ul>
                </li>
                <li class="treeview" >
                    <a href="#" >
                        <i class="glyphicon glyphicon-picture"></i>
                        <span>图纸管理</span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${pageContext.request.contextPath}/picture_management/house_management">户型图管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/picture_management/designerpic_management">设计图管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/picture_management/contract_management">合同管理</a></li>
                    </ul>
                </li>
                <li class="treeview" >
                    <a href="#" >
                        <i class="glyphicon glyphicon-picture"></i>
                        <span>设计展示管理</span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${pageContext.request.contextPath}/designer_show/designer_show_management">设计展示管理</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="glyphicon glyphicon-wrench"></i>
                        <span>材料管理</span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${pageContext.request.contextPath}/material_management/material">材料管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/material_offer_management/materialOffer"> 供应商管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialClazz">材料大类型管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz">材料小类型管理</a></li>
                    </ul>
                </li>
                <li class="header">用户导航</li>
                <li class="treeview" >
                    <a href="${pageContext.request.contextPath}/common/resetpassword">
                        <i class="glyphicon glyphicon-edit"></i> <span>修改密码</span>
                    </a>
                </li>
                <li class="treeview">
                    <a onclick="logout()">
                        <i class="glyphicon glyphicon-user"></i> <span>退出登录</span>
                    </a>
                </li>
            </ul>
        </section>
    </aside>
</div>
<script>

    function logout() {
        $.post({
            url: "${pageContext.request.contextPath}/common/logout",
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
    }
</script>

