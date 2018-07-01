<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/6
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <li class="active">飞单客户</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">飞单客户
                    <div class="pull-right">总共${pageInfo.pages }页，总共${pageInfo.total }条记录</div>
                    </div>
                    <div class="panel-body">
                        飞单客户为走丢了的客户
                    </div>

                    <div class="row" style="margin-left: 10px;">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/common_fly/consumer_management_fly" method="get">
                            <div class="col-sm-2">
                                start
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                    <input class="form-control"  name="startTime" id="startTime" size="16" type="text" value="" readonly>
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
                            <div class="col-sm-2">
                                end
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" name="endTime" id="endTime" size="16" type="text" value="" readonly>
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
                            <div class="col-sm-2">
                                姓名
                                <input name="sname" id="sname" class="form-control" placeholder="姓名模糊查询">
                            </div>
                            <div class="col-sm-2">
                                省份
                                <select id="province" name="provinceId"class="form-control">
                                    <option value="0">请选择省份</option>
                                    <c:forEach items="${provinces}" var="p">
                                        <option value="${p.provinceId}">${p.provinceName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                城市
                                <select id="city" name="cityId" class="form-control">
                                    <option value="0">请选择城市</option>
                                </select>
                            </div>
                            <script type="text/javascript">
                                $(document).ready(function(){
                                $('#province').change(function () {
                                    $.post({
                                        url: "${pageContext.request.contextPath}/welcome/getcity",
                                        dataType : "json",//预期服务器返回的类型c
                                        contentType: "application/json",
                                        data: JSON.stringify({provinceId: $('#province').val()}),
                                        success : function (data) {
                                            console.log(data);
                                            $("#city option").remove();
                                            $("#city").append("<option value='0'>请选择城市</option>");
                                            if (data != 0) {
                                                for ( var i = 0; i < data.length; i++) {
                                                    var cityId = data[i].cityId;
                                                    var cityName = data[i].cityName;
                                                    $("#city").append(
                                                        "<option value="+cityId+">"
                                                        + cityName + "</option>");
                                                }
                                            }

                                        },
                                        error : function(){
                                            $('#myModalBody').text('请重新刷新页面');
                                            $('#myModal').modal('show')
                                        }
                                    });
                                });
                                })
                            </script>
                            <input type="hidden" name="employeeId" value="${employeeId}">
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
                                <th>登记编号</th>
                                <th>登记时间</th>
                                <th>区域</th>
                                <th>面积</th>
                                <th>市场人员</th>
                                <th>设计师</th>
                                <th style="width:70px;">业务状态</th>
                                <th style="width:50px;">跟踪次数</th>
                                <th style="width:70px;">未跟踪天数</th>
                                <th style="width:50px;">警告次数</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${registrationTables}" var="r" varStatus="i" >
                                <tr>
                                    <td>${r.registrationId}</td>
                                    <td>${r.registrationTime}</td>
                                    <td>${r.city.province.provinceName}${r.city.cityName}</td>
                                    <td>${r.registrationArea}</td>
                                    <td>${r.employee.employeeName}</td>
                                    <td>${r.designer.employeeName}</td>
                                    <td>${r.businessStatus}</td>
                                    <td>${r.trackingTimes}</td>
                                    <td>${r.days}</td>
                                    <td>${r.warningTimes}</td>
                                    <td>
                                        <div  class="btn-group btn-group-xs" role="group">
                                            <button type="button" onclick="setCancelTable('${r.registrationId}')" class="btn btn-default">飞单详情</button>
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

                                    <li><a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly?pageNum=1&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}&employeeId=${searchContent.employeeId}">首页</a></li>

                                    <c:if test="${pageInfo.hasPreviousPage  }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly?pageNum=${pageInfo.pageNum-1}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}&employeeId=${searchContent.employeeId}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                        <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly?pageNum=${page}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}&employeeId=${searchContent.employeeId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page==pageInfo.pageNum}">
                                            <li class="active"><a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly?pageNum=${page}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}&employeeId=${searchContent.employeeId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly?pageNum=${page}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}&employeeId=${searchContent.employeeId}">${page}</a></li>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${pageInfo.hasNextPage }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly?pageNum=${pageInfo.pageNum+1 }&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}&employeeId=${searchContent.employeeId}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <li><a href="${pageContext.request.contextPath}/common_fly/consumer_management_fly?pageNum=${pageInfo.pages}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}&employeeId=${searchContent.employeeId}">末页</a></li>

                                </ul>
                            </nav>
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
                                飞单内容
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <form class='form-horizontal' id='cancel_form'>
                                <div class='row'>
                                    <div class='col-sm-4'>操作人员：${sessionScope.employee.employeeName}</div>
                                    <div class='col-sm-4'>记录编号：<span id="registration"></span></div>
                                </div>
                                    <div class='row' style='margin-top:10px;'>
                                        <div class='col-sm-3'>飞单原因：</div>
                                        <div class='col-sm-6'>
                                            <textarea id="cancelContent" name='cancelContent' cols='5' class='form-control'></textarea>
                                        </div>
                                        <input type='hidden'id="registrationId1" name='registrationId'/>
                                    </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button id='set_true'  type='button' id="set_true" class='btn btn-primary'>批准</button>
                            <button type='button' id='set_false' class='btn btn-danger'>拒绝</button>
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
    function setCancelTable(registrationId){
        var json = {
            "registrationId":registrationId
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_fly/getCancelTable",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                console.log(data);
                if (data.cancelTable!=null){
                    $('#registration').text(data.cancelTable.registrationId)
                    $('#registrationId1').val(data.cancelTable.registrationId)
                    $('#cancelContent').val(data.cancelTable.cancelContent)
                    $('#myModal2').modal('show')
                }

                $('#myModal2').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error : function(){
                $('#myModalBody2').text('请重新刷新页面');
                $('#myModal2').modal('show')

            }
        });
    }
    $('#set_true').click(function () {
        var json = {
            "registrationId":$('#registrationId1').val()
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_fly/delCancelTable",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                console.log(data);
                $('#set_true').hide()
                $('#set_false').hide()
                $('#myModalBody2').text(data.msg);
                $('#myModal2').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error : function(){
                $('#myModalBody2').text('请重新刷新页面');
                $('#myModal2').modal('show')

            }
        });
    })

    $('#set_false').click(function () {
        var json = {
            "registrationId":$('#registrationId1').val()
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_fly/resetCancelTable",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                console.log(data);
                $('#set_true').hide()
                $('#set_false').hide()
                $('#myModalBody2').text(data.msg);
                $('#myModal2').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error : function(){
                $('#myModalBody2').text('请重新刷新页面');
                $('#myModal2').modal('show')

            }
        });
    })
</script>

</body>
</html>
