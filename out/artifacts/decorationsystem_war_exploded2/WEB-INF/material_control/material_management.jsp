<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/24
  Time: 23:16
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
                    <li class="active">材料管理</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading" style="padding-bottom: 0px;">
                        <div class="row">
                            <div class="pull-right">总共${pageInfo.pages }页，总共${pageInfo.total }条记录</div>
                        </div>
                    </div>
                    <div class="panel-body">
                    </div>

                    <div class="row" style="margin-left: 10px; margin-bottom: 10px; ">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/material_management/material" method="get">
                            <div class="col-sm-2">
                                材料名字
                                <input name="materialName" class="form-control" placeholder="模糊查询">
                            </div>
                            <div class="col-sm-2">
                                材料大类型
                                <select id="bigId" name="bigId"class=" form-control">
                                    <option value="0">请选择材料大类型</option>
                                    <c:forEach items="${materialClazzes}" var="h">
                                        <option value="${h.bigId}">${h.bigName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                材料小类型
                                <select id="smallId" name="smallId"class="form-control">
                                    <option value="0">请选择材料小类型</option>
                                </select>

                            </div>
                            <div class="col-sm-2">
                                供应商
                                <select id="modeId" name="modeId"class="form-control">
                                    <option value="0">请选择供应商</option>
                                    <c:forEach items="${materialOffers}" var="h">
                                        <option value="${h.offerId}">${h.offerName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm-2">
                                &nbsp;
                                <button class="btn btn-default btn-lg" type="submit"><i class="glyphicon glyphicon-search"></i> </button>
                            </div>
                        </form>
                    </div>

                    <div class="table-responsive">
                        <!-- Table -->
                        <table class="table table-hover">
                            <tr  class="text-center">
                                <th>材料编号</th>
                                <th>材料名字</th>
                                <th>材料单价</th>
                                <th>材料品牌</th>
                                <th>材料大类型</th>
                                <th>材料小类型</th>
                                <th>材料供应商</th>
                                 <th>操作<c:forEach items="${sessionScope.employee.job.myAuths}" var="m">
                                  <c:if test="${m.authId==65}"><button class="btn btn-default btn-sm" id="addbtn">添加</button></c:if>
                                  </c:forEach></th>

                            </tr>
                            <c:forEach items="${materials}" var="c" varStatus="i" >
                                <tr>
                                    <td>${c.materialId}</td>
                                    <td>
                                        <c:if test="${c.materialName.length()>10}">
                                            ${c.materialName.substring(0,10)}...
                                        </c:if>
                                        <c:if test="${c.materialName.length()<=10}">
                                            ${c.materialName}
                                        </c:if>
                                    </td>
                                    <td>${c.materialPrice}</td>
                                    <td>${c.materialBrand}</td>
                                    <td>${c.materialSmallclazz.materialClazz.bigName}</td>
                                    <td>${c.materialSmallclazz.smallName}</td>
                                    <td>${c.materialOffer.offerName}</td>
                                    <td>
                                        <div  class="btn-group btn-group-xs" role="group">
                                            <button type="button"   onclick="window.location.href='${pageContext.request.contextPath}/material_management/material_content?materialId=${c.materialId}'" class="btn btn-primary">详情</button>
                                            <c:forEach items="${sessionScope.employee.job.myAuths}" var="m">
                                            <c:if test="${m.authId==65}">
                                            <button type="button" class="btn btn-default" onclick="openupdate('${c.materialId}')">更新</button>
                                            <button type="button" class="btn btn-danger" onclick="opendel('${c.materialId}')" >删除</button>
                                            </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>

                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!-- 分页 -->
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <nav aria-lable="Page navigation">
                                <ul class="pagination">

                                    <li><a href="${pageContext.request.contextPath}/material_management/material?pageNum=1&materialName=${searchContent.materialName}&offerId=${searchContent.offerId}&bigId=${searchContent.bigId}&smallId=${searchContent.smallId}">首页</a></li>

                                    <c:if test="${pageInfo.hasPreviousPage  }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/material_management/material?pageNum=${pageInfo.pageNum-1}&materialName=${searchContent.materialName}&offerId=${searchContent.offerId}&bigId=${searchContent.bigId}&smallId=${searchContent.smallId}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                        <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/material_management/material?pageNum=${page}&materialName=${searchContent.materialName}&offerId=${searchContent.offerId}&bigId=${searchContent.bigId}&smallId=${searchContent.smallId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page==pageInfo.pageNum}">
                                            <li class="active"><a href="${pageContext.request.contextPath}/material_management/material?pageNum=${page}&materialName=${searchContent.materialName}&offerId=${searchContent.offerId}&bigId=${searchContent.bigId}&smallId=${searchContent.smallId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/material_management/material?pageNum=${page}&materialName=${searchContent.materialName}&offerId=${searchContent.offerId}&bigId=${searchContent.bigId}&smallId=${searchContent.smallId}">${page}</a></li>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${pageInfo.hasNextPage }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/material_management/material?pageNum=${pageInfo.pageNum+1 }&materialName=${searchContent.materialName}&offerId=${searchContent.offerId}&bigId=${searchContent.bigId}&smallId=${searchContent.smallId}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <li><a href="${pageContext.request.contextPath}/material_management/material?pageNum=${pageInfo.pages}&materialName=${searchContent.materialName}&offerId=${searchContent.offerId}&bigId=${searchContent.bigId}&smallId=${searchContent.smallId}">末页</a></li>

                                </ul>
                            </nav>
                        </div>
                    </div><%--分页--%>
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
                                提示
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">


                        <div class="row" style="padding: 0px 20px 0px 20px;">
                        <form method="post" id="uploadform">
                            <div class="row">
                                <div class="col-sm-2">
                                    材料名字：
                                </div>
                                <div class="form-group col-sm-3">
                                    <input class="form-control" id="materialName" name="materialName"/>
                                </div>
                                <div class="col-sm-2">
                                    材料单价：
                                </div>
                                <div class="form-group col-sm-4">
                                    <div class="input-group">
                                        <input id="materialPrice" name="materialPrice" class="form-control" type="text"/>
                                        <span class="input-group-addon">RMB</span>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    材料品牌：
                                </div>
                                <div class="form-group col-sm-4">
                                    <input class="form-control" id="materialBrand" name="materialBrand"/>
                                </div>
                                <div class="col-sm-2">
                                    材料规格：
                                </div>
                                <div class="form-group col-sm-4">
                                    <input class="form-control" id="materialCompany" name="materialCompany"/>
                                </div>


                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    材料单位：
                                </div>
                                <div class="form-group col-sm-4">
                                    <input class="form-control" id="materialStandard" name="materialStandard"/>
                                </div>
                            </div>
                            <div class="row">

                                <div class="col-sm-2">
                                    材料大类型：
                                </div>
                                <div class="col-sm-4 form-group">
                                    <select id="bigId1" name="bigId"class=" form-control">
                                        <option value="0">请选择材料大类型</option>
                                        <c:forEach items="${materialClazzes}" var="h">
                                            <option value="${h.bigId}">${h.bigName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    材料小类型：
                                </div>
                                <div class="col-sm-4 form-group">
                                    <select id="smallId1" name="smallId"class="form-control">
                                        <option value="0">请选择材料小类型</option>
                                    </select>

                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    材料供应商：
                                </div>
                                <div class="form-group col-sm-4">
                                    <select id="offerId1" name="offerId"class=" form-control">
                                        <option value="0">请选择材料供应商</option>
                                        <c:forEach items="${materialOffers}" var="h">
                                            <option value="${h.offerId}">${h.offerName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    材料描述
                                </div>
                                <div class="col-sm-4 form-group">
                                    <textarea cols="5" class="form-control" id="materialDescribe" name="materialDescribe"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-2">材料图片</div>
                                <div class="col-sm-5 form-group">
                                    <input id="file" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 col-sm-offset-4">
                                    <button type="button" class="btn btn-primary" onclick="uploadFiles()" id="addbtna">添加</button>
                                    <button type="reset" class="btn btn-default" id="reset">重置</button>
                                </div>
                            </div>
                        </form>
                        </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

            <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel3">
                                提示
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody3">


                            <div class="row" style="padding: 0px 20px 0px 20px;">
                                <form method="post" id="changform">
                                    <div class="row">
                                        <div class="col-sm-5 hidden" id="materialIdshow"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            材料名字：
                                        </div>
                                        <div class="form-group col-sm-3">
                                            <input class="form-control" id="materialName1" name="materialName"/>
                                        </div>
                                        <div class="col-sm-2">
                                            材料单价：
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <div class="input-group">
                                                <input id="materialPrice1" name="materialPrice" class="form-control" type="text"/>
                                                <span class="input-group-addon">RMB</span>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            材料品牌：
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <input class="form-control" id="materialBrand1" name="materialBrand"/>
                                        </div>
                                        <div class="col-sm-2">
                                            材料规格：
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <input class="form-control" id="materialCompany1" name="materialCompany"/>
                                        </div>


                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            材料单位：
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <input class="form-control" id="materialStandard1" name="materialStandard"/>
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-sm-2">
                                            材料大类型：
                                        </div>
                                        <div class="col-sm-4 form-group">
                                            <select id="bigId2" name="bigId"class=" form-control">
                                                <option value="0">请选择材料大类型</option>
                                                <c:forEach items="${materialClazzes}" var="h">
                                                    <option value="${h.bigId}">${h.bigName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-2">
                                            材料小类型：
                                        </div>
                                        <div class="col-sm-4 form-group">
                                            <select id="smallId2" name="smallId"class="form-control">
                                                <option value="0">请选择材料小类型</option>
                                            </select>

                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            材料供应商：
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <select id="offerId2" name="offerId"class=" form-control">
                                                <option value="0">请选择材料供应商</option>
                                                <c:forEach items="${materialOffers}" var="h">
                                                    <option value="${h.offerId}">${h.offerName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            材料描述
                                        </div>
                                        <div class="col-sm-4 form-group">
                                            <textarea cols="5" class="form-control" id="materialDescribe1" name="materialDescribe"></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">材料图片</div>
                                        <div class="col-sm-5 form-group">
                                            <input id="file1" type="file" multiple="true" accept="image/jpeg,image/png" class="form-control" name="file"/>
                                        </div>
                                    </div>
                                    <input id="materialId" name="materialId" class="hidden" value=""/>
                                    <input id="version" name="version" class="hidden" value=""/>
                                    <div class="row">
                                        <div class="col-sm-4 col-sm-offset-4" id="up">
                                            <button type="button" class="btn btn-primary" onclick="changFiles()" >添加</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
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

</div>
<script type="text/javascript">
    $.sidebarMenu($('.sidebar-menu'))
    $('#bigId').change(function () {
        $.post({
            url: "${pageContext.request.contextPath}/material_management/getMaterialSmallclazz",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify({bigId: $('#bigId').val()}),
            success : function (data) {
                console.log(data);
                $("#smallId option").remove();
                $("#smallId").append("<option value='0'>请选择材料小类型</option>");
                if (data != 0) {
                    for ( var i = 0; i < data.length; i++) {
                        var smallId = data[i].smallId;
                        var smallName = data[i].smallName;
                        $("#smallId").append(
                            "<option value="+smallId+">"
                            + smallName + "</option>");
                    }
                }

            },
            error : function(){
                $('#myModalBody').text('请重新刷新页面');
                $('#myModal').modal('show')
            }
        });
    })
    $('#bigId1').change(function () {
        $.post({
            url: "${pageContext.request.contextPath}/material_management/getMaterialSmallclazz",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify({bigId: $('#bigId1').val()}),
            success : function (data) {
                console.log(data);
                $("#smallId1 option").remove();
                $("#smallId1").append("<option value='0'>请选择材料小类型</option>");
                if (data != 0) {
                    for ( var i = 0; i < data.length; i++) {
                        var smallId = data[i].smallId;
                        var smallName = data[i].smallName;
                        $("#smallId1").append(
                            "<option value="+smallId+">"
                            + smallName + "</option>");
                    }
                }

            },
            error : function(){
                $('#myModalBody').text('请重新刷新页面');
                $('#myModal').modal('show')
            }
        });
    })
    $('#bigId2').change(function () {
        $.post({
            url: "${pageContext.request.contextPath}/material_management/getMaterialSmallclazz",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify({bigId: $('#bigId2').val()}),
            success : function (data) {
                console.log(data);
                $("#smallId2 option").remove();
                $("#smallId2").append("<option value='0'>请选择材料小类型</option>");
                if (data != 0) {
                    for ( var i = 0; i < data.length; i++) {
                        var smallId = data[i].smallId;
                        var smallName = data[i].smallName;
                        $("#smallId2").append(
                            "<option value="+smallId+">"
                            + smallName + "</option>");
                    }
                }

            },
            error : function(){
                $('#myModalBody').text('请重新刷新页面');
                $('#myModal').modal('show')
            }
        });
    })
    function openupdate(materialId) {
        var json = {
            "materialId":materialId
        }
        $.post({
            url: "${pageContext.request.contextPath}/material_management/getMaterial",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                if (data !=null){
                    $('#materialIdshow').text("材料编号："+data.material.materialId)
                    $('#materialName1').val(data.material.materialName);
                    $('#materialPrice1').val(data.material.materialPrice);
                    $('#version').val(data.material.version);
                    $('#materialId').val(data.material.materialId);
                    $('#materialCompany1').val(data.material.materialCompany);
                    $('#materialBrand1').val(data.material.materialBrand);
                    $('#materialStandard1').val(data.material.materialStandard);
                    $('#offerId2').val(data.material.materialOffer.offerId);
                    $('#bigId2').val(data.material.materialSmallclazz.materialClazz.bigId)
                    $('#smallId2').append(data.appstr);
                    $('#smallId2').val(data.material.materialSmallclazz.smallId)
                   $('#materialDescribe1').val(data.material.materialDescribe);
                    $('#myModal3').modal('show')
                    $('#myModal3').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }else {
                    $('#myModalBody3').text('请重新刷新页面');
                    $('#myModal3').modal('show')
                    $('#myModal3').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }

            },
            error : function(){
                $('#myModalBody3').text('请重新刷新页面');
                $('#myModal3').modal('show')
                $('#myModal3').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        });
    }


    $('#addbtn').click(function () {
        $('#myModal2').modal('show');
    })


    $('#uploadform').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            materialName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },

            materialPrice: {
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
            materialDescribe:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            materialBrand:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            materialCompany:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            materialStandard:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            offerId:{
                validators:{
                    greaterThan: {
                        value: 1,
                        message:'请选择供应商'
                    }
                }
            },
            bigId:{
                validators:{
                    greaterThan: {
                        value: 1,
                        message:'请选择材料大类'
                    }
                }
            },
            smallId:{
                validators:{
                    greaterThan: {
                        value: 1,
                        message:'请选择材料小类'
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
            var materialName = $('#materialName').val();
            var materialPrice = $('#materialPrice').val();
            var materialCompany = $('#materialCompany').val();
            var materialBrand = $('#materialBrand').val();
            var materialStandard = $('#materialStandard').val();
            var offerId = $('#offerId1').val();
            var smallId = $('#smallId1').val();
            var materialDescribe = $('#materialDescribe').val();
            formData.append("materialDescribe",materialDescribe);
            formData.append("smallId",smallId);
            formData.append("offerId", offerId);
            formData.append("materialStandard", materialStandard);
            formData.append("materialBrand", materialBrand);
            formData.append("materialCompany", materialCompany);
            formData.append("materialPrice", materialPrice);
            formData.append("materialName", materialName);
            var files = document.getElementById("file").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }
            $('#myModalBody2').text("上传设计图中，请稍等");

            console.log(formData);

                $.ajax({
                    url:'${pageContext.request.contextPath}/material_management/saveMaterial',
                    type:'POST',
                    data:formData,
                    cache: false,
                    contentType: false, //不设置内容类型
                    processData: false, //不处理数据
                    success:function(data){
                        console.log(data);
                        var resultJson = JSON.parse(data)
                        /*$('#myModalBody').text(resultJson['msg']);*/
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
            }
    }
    $('#reset').click(function () {
        $('#uploadform').bootstrapValidator('resetForm')
    });

</script>
<script>


    $('#changform').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            materialName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },

            materialPrice: {
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
            materialDescribe:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            materialBrand:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            materialCompany:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            materialStandard:{
                validators:{
                    stringLength: {//检测长度
                        min: 0,
                        max: 100,
                        message: '长度必须在0-100之间！'
                    }
                }
            },
            offerId:{
                validators:{
                    greaterThan: {
                        value: 1,
                        message:'请选择供应商'
                    }
                }
            },
            bigId:{
                validators:{
                    greaterThan: {
                        value: 1,
                        message:'请选择材料大类'
                    }
                }
            },
            smallId:{
                validators:{
                    greaterThan: {
                        value: 1,
                        message:'请选择材料小类'
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
            var materialName = $('#materialName1').val();
            var materialPrice = $('#materialPrice1').val();
            var materialCompany = $('#materialCompany1').val();
            var materialBrand = $('#materialBrand1').val();
            var materialStandard = $('#materialStandard1').val();
            var offerId = $('#offerId2').val();
            var smallId = $('#smallId2').val();
            var materialDescribe = $('#materialDescribe1').val();
            var version = $('#version').val();
            var materialId = $('#materialId').val();
            formData.append("version",version);
            formData.append("materialId",materialId);
            formData.append("materialDescribe",materialDescribe);
            formData.append("smallId",smallId);
            formData.append("offerId", offerId);
            formData.append("materialStandard", materialStandard);
            formData.append("materialBrand", materialBrand);
            formData.append("materialCompany", materialCompany);
            formData.append("materialPrice", materialPrice);
            formData.append("materialName", materialName);
            var files = document.getElementById("file1").files;
            for(var i=0; i< files.length; i++){
                formData.append("files",files[i]);   // 文件对象
            }
            $('#myModalBody3').text("上传设计图中，请稍等");

            console.log(formData);

            $.ajax({
                url:'${pageContext.request.contextPath}/material_management/updateMaterial',
                type:'POST',
                data:formData,
                cache: false,
                contentType: false, //不设置内容类型
                processData: false, //不处理数据
                success:function(data){
                    console.log(data);
                    var resultJson = JSON.parse(data)
                    /*$('#myModalBody').text(resultJson['msg']);*/
                    $('#myModalBody3').text(resultJson['msg']);
                    $('#myModal3').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                },
                error:function(){
                    $('#myModalBody3').text("上传失败！");
                }
            })
        }
    }
    function opendel(materialId) {
        var str = "<button type=\"button\" class=\"btn btn-danger\" onclick=\"enterdel('"+materialId+"')\">确认删除</button>\n" +
            "                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭"
        $('#myModalBody').html(str);
        $('#myModal').modal('show')

    }
    function enterdel(materialId) {
        var json = {
            "materialId":materialId
        }
        $.post({
            url: "${pageContext.request.contextPath}/material_management/delMaterial",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                console.log(data);

                $('#myModalBody').text(data.msg);
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

