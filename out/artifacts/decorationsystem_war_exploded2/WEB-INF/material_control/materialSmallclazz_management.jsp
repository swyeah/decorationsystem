<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/25
  Time: 23:28
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
                    <li class="active">材料小分类管理</li>
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
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz" method="get">
                            <div class="col-sm-4">
                                <input name="searchContent" class="form-control" placeholder="编号名字模糊查询">
                            </div>

                            <div class="col-sm-2">
                                &nbsp;
                                <button class="btn btn-default btn-sm" type="submit"><i class="glyphicon glyphicon-search"></i> </button>
                            </div>
                        </form>
                    </div>

                    <div class="table-responsive">
                        <!-- Table -->
                        <table class="table table-hover">
                            <tr  class="text-center">
                                <th>材料小分类编号</th>
                                <th class="col-sm-3">材料小分类名字</th>
                                <th class="col-sm-3">材料大分类</th>
                                <th>操作<button class="btn btn-default btn-sm" id="addbtn">添加</button></th>
                            </tr>
                            <c:forEach items="${materialSmallclazzes}" var="c" varStatus="i" >
                                <tr>
                                    <td>${c.smallId}</td>
                                    <td>
                                        ${c.smallName}
                                    </td>
                                    <td>${c.materialClazz.bigName}</td>
                                    <td>
                                        <div  class="btn-group btn-group-xs" role="group">
                                            <button type="button" class="btn btn-default" onclick="openupdate('${c.smallId}')">更新</button>
                                            <button type="button" class="btn btn-danger" onclick="opendel('${c.smallId}')" >删除</button>
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

                                    <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz?pageNum=1&searchContent=${searchContent}">首页</a></li>

                                    <c:if test="${pageInfo.hasPreviousPage  }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz?pageNum=${pageInfo.pageNum-1}&searchContent=${searchContent}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                        <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz?pageNum=${page}&searchContent=${searchContent}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page==pageInfo.pageNum}">
                                            <li class="active"><a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz?pageNum=${page}&searchContent=${searchContent}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz?pageNum=${page}&searchContent=${searchContent}">${page}</a></li>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${pageInfo.hasNextPage }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz?pageNum=${pageInfo.pageNum+1 }&searchContent=${searchContent}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <li><a href="${pageContext.request.contextPath}/materialClazz_management/materialSmallclazz?pageNum=${pageInfo.pages}&searchContent=${searchContent}">末页</a></li>

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
                                添加材料小分类
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">


                        <div class="row" style="padding: 0px 20px 0px 20px;">
                        <form method="post" id="uploadform">
                            <div class="row">
                                <div class="col-sm-3">
                                    材料大分类：
                                </div>
                                <div class="col-sm-4 form-group">
                                    <select id="bigId1" name="bigId"class=" form-control">
                                        <option value="0">请选择材料大类型</option>
                                        <c:forEach items="${materialClazzes}" var="h">
                                            <option value="${h.bigId}">${h.bigName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    材料小分类名字：
                                </div>
                                <div class="col-sm-4 form-group">
                                    <input type="text" class="form-control" id="smallName1" name="smallName"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3 col-sm-offset-2">
                                    <button type="button" class="btn btn-primary" id="addbtna">添加</button>
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
                                更新材料小分类
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody3">


                            <div class="row" style="padding: 0px 20px 0px 20px;">
                                <form method="post" id="changform">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            材料大分类：
                                        </div>
                                        <div class="col-sm-4 form-group">
                                            <select id="bigId2" name="bigId"class=" form-control">
                                                <option value="0">请选择材料大类型</option>
                                                <c:forEach items="${materialClazzes}" var="h">
                                                    <option value="${h.bigId}">${h.bigName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            材料小分类名字：
                                        </div>
                                        <div class="col-sm-4 form-group">
                                            <input type="text" class="form-control" id="smallName2" name="smallName"/>
                                        </div>
                                    </div>
                                    <input class="hidden" id="smallId" name="smallId" value=""/>
                                    <div class="row">
                                        <div class="col-sm-3 col-sm-offset-2">
                                            <button type="button" class="btn btn-primary" id="updatebtn">更新</button>
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
            smallName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 10,
                        message: '长度必须在0-10之间！'
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
            }
        }
    });
    $('#addbtna').click(function(){
        $('#uploadform').bootstrapValidator('validate');
        if($('#uploadform').data("bootstrapValidator").isValid()){
          var json =$('#uploadform').serializeObject();
            $.post({
                url: "${pageContext.request.contextPath}/materialClazz_management/saveMaterialSmallclazz",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data:JSON.stringify(json),
                success : function (data) {
                    console.log(data);

                    $('#myModalBody2').text(data.msg);
                    $('#myModal2').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })

                },
                error : function(){
                    $('#myModalBody2').text('请重新刷新页面');
                    $('#myModal2').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }
            });
        }
    })
    $('#reset').click(function () {
        $('#uploadform').bootstrapValidator('resetForm')
    });

</script>
<script>
    function openupdate(smallId){
        var json = {
            "smallId":smallId
        }
        $.post({
            url: "${pageContext.request.contextPath}/materialClazz_management/getMaterialSmallclazz",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                console.log(data);
                $('#smallName2').val(data.materialSmallclazz.smallName)
                $('#bigId2').val(data.materialSmallclazz.materialClazz.bigId)
                $('#smallId').val(data.materialSmallclazz.smallId)
                $('#myModal3').modal('show')
                $('#myModal3').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })

            },
            error : function(){
                $('#myModalBody3').text('请重新刷新页面');
                $('#myModal3').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        });
    }

    $('#changform').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            smallName: {
                validators: {
                    notEmpty: {
                        message: '不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 10,
                        message: '长度必须在0-10之间！'
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
            }

        }
    });
    $('#updatebtn').click(function(){
        $('#changform').bootstrapValidator('validate');
        //var bootstrapValidator = $('#login_form').data('bootstrapValidator');
        //bootstrapValidator.on('success.form.bv', function (e) {
        //e.preventDefault();
        //提交逻辑
        if($('#changform').data("bootstrapValidator").isValid()){
            var json =$('#changform').serializeObject();
            $.post({
                url: "${pageContext.request.contextPath}/materialClazz_management/updateMaterialSmallclazz",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data:JSON.stringify(json),
                success : function (data) {
                    console.log(data);

                    $('#myModalBody3').text(data.msg);
                    $('#myModal3').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })

                },
                error : function(){
                    $('#myModalBody3').text('请重新刷新页面');
                    $('#myModal3').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }
            });
        }
    })
    function opendel(smallId) {
        var str = "<button type=\"button\" class=\"btn btn-danger\" onclick=\"enterdel('"+smallId+"')\">确认删除</button>\n" +
            "                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭"
        $('#myModalBody').html(str);
        $('#myModal').modal('show')

    }
    function enterdel(smallId) {
        var json = {
            "smallId":smallId
        }
        $.post({
            url: "${pageContext.request.contextPath}/materialClazz_management/delMaterialSmallclazz",
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

