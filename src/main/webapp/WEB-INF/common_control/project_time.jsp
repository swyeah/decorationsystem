<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/4
  Time: 0:57
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

<div class=".container-fluid ">
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
                    <li class="active">工程进度</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                工程进度
                            </a>
                        </li>
                    </ul>
                    <div class="panel-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                            </div>
                        </div>

                        <div class="row">
                            <form>
                                <div class="col-sm-1">开始时间：</div>
                                <div class="col-sm-2">
                                    <div class="input-group date form_date " data-date="" data-date-format="yyyy-mm-dd">
                                        <input class="form-control"  name="startTime" size="16" type="text" value="" readonly>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
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
                                <div class="col-sm-1">结束时间：</div>
                                <div class="col-sm-2">
                                    <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" >
                                        <input class="form-control" name="endTime" size="16" type="text" value="" readonly>
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
                                <input type="hidden" value="${map.registrationId}" name="registrationId"/>
                                <div class="col-sm-2">
                                    &nbsp;
                                    <button class="btn btn-default btn-lg" type="submit"><i class="glyphicon glyphicon-search"></i> </button>
                                </div>
                            </form>
                        </div>

                        <hr>
                        <div class="row">
                            <div class="col-sm-2">工程日期</div>
                            <div class="col-sm-4">
                                <button class="btn btn-primary btn-sm" id="addbtn">添加</button>
                                <button class="btn btn-danger btn-sm" id="delbtn">删除</button>
                            </div>
                        </div>

                        <div class="row">
                            <c:forEach items="${projectTimes}" var="p">
                                <div class="col-sm-1" style="margin-left: 10px;margin-top: 10px;"><button onclick="window.location.href='${pageContext.request.contextPath}/common_contract/progressContent?projectId=${p.projectId}'" class="btn btn-default"><fmt:formatDate value="${p.projectDay}" pattern="yyyy-MM-dd"/></button> </div>
                            </c:forEach>
                        </div>
                        <!-- 分页 -->
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <nav aria-lable="Page navigation">
                                    <ul class="pagination">

                                        <li><a href="${pageContext.request.contextPath}/common_contract/project_time?pageNum=1&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">首页</a></li>

                                        <c:if test="${pageInfo.hasPreviousPage  }">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/common_contract/project_time?pageNum=${pageInfo.pageNum-1}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                            <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                                <li><a href="${pageContext.request.contextPath}/common_contract/project_time?pageNum=${page}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">${page}</a></li>
                                            </c:if>
                                            <c:if test="${page==pageInfo.pageNum}">
                                                <li class="active"><a href="${pageContext.request.contextPath}/common_contract/project_time?pageNum=${page}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">${page}</a></li>
                                            </c:if>
                                            <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                                <li><a href="${pageContext.request.contextPath}/common_contract/project_time?pageNum=${page}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">${page}</a></li>
                                            </c:if>
                                        </c:forEach>

                                        <c:if test="${pageInfo.hasNextPage }">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/common_contract/project_time?pageNum=${pageInfo.pageNum+1 }&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <li><a href="${pageContext.request.contextPath}/common_contract/project_time?pageNum=${pageInfo.pages}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">末页</a></li>

                                    </ul>
                                </nav>
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
                                添加工程日期
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <div class="row"><form>
                                <div class="col-sm-3">添加工程日期：</div>
                                <div class="col-sm-4">
                                    <div class="input-group date form_date " data-date="" data-date-format="yyyy-mm-dd">
                                        <input class="form-control" id="projectDay" name="projectDay" size="16" type="text" value="" readonly>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </form>
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
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="addbtna">添加</button>
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
                                删除工程日期
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody3">
                            <div class="row"><form>
                                <div class="col-sm-3">删除工程日期：</div>
                                <div class="col-sm-4">
                                    <div class="input-group date form_date " data-date="" data-date-format="yyyy-mm-dd">
                                        <input class="form-control" id="projectDay2" name="projectDay" size="16" type="text" value="" readonly>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </form>
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
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="delbtna">删除</button>
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
    $('#addbtn').click(function () {
        $('#myModal2').modal('show');
    })

    $('#addbtna').click(function () {
        var json = {
            "projectDay":$('#projectDay').val(),
            "registrationId":${map.registrationId}
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_contract/addprojectTime",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(json),
            aysnc:false,
            success : function (data) {
                console.log(data);
                $('#addbtna').hide();
                $('#myModalBody2').text(data.msg);
                $('#myModal2').modal('show');
                $('#myModal2').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error : function(){
                $('#addbtna').hide();
                $('#myModalBody2').text(data.msg);
                $('#myModal2').modal('show')
                $('#myModal2').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        });
    })
    $('#delbtn').click(function () {
        $('#myModal3').modal('show');
    })

    $('#delbtna').click(function () {
        var json = {
            "projectDay":$('#projectDay2').val(),
            "registrationId":${map.registrationId}
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_contract/delprojectTime",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(json),
            aysnc:false,
            success : function (data) {
                console.log(data);
                $('#delbtna').hide();
                $('#myModalBody3').text(data.msg);
                $('#myModal3').modal('show');
                $('#myModal3').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error : function(){
                $('#addbtna').hide();
                $('#myModalBody3').text(data.msg);
                $('#myModal3').modal('show')
                $('#myModal3').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            }
        });
    })

</script>

</body>
</html>
