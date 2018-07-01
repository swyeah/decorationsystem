<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/16
  Time: 17:12
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
        <div class="col-xs-10 col-xs-offset-1 col-sm-9 col-sm-offset-0">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li class="active">客户管理</li>
                    <li class="active">预约时间</li>
                </ol>
            </div>

            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">客户资料
                    </div>
                    <div class="panel-body"  style="padding-left: 50px; padding-right: 50px;">
                        <div class="row">
                            <div class="col-xs-4">客户姓名：${registration.registrationName}</div>
                            <div class="col-xs-4">电话号码：${registration.registrationPhone}</div>
                            <div class="col-xs-4">地址:${registration.city.province.provinceName}${registration.city.cityName}${registration.registrationAddress}</div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4">面积：${registration.registrationArea}</div>
                            <div class="col-xs-4">市场人员：${registration.employee.employeeName}</div>
                            <div class="col-xs-4">设计师：${registration.designer.employeeName}</div>
                        </div>
                    </div>
                </div>
            </div>

                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-4 col-sm-2">预约信息</div>
                                <div class="col-xs-2 col-sm-1 col-xs-offset-3 col-sm-offset-8" ><button id="addapp" class="btn btn-default btn-sm">添加预约</button> </div>
                            </div>
                        </div>
                        <div class="panel-body"  style="padding-left: 50px; padding-right: 50px;">
                            <c:forEach items="${appointments}" var="a" varStatus="s">
                               <div class="row" id="appointments">
                                   <div class="row">
                                       <div class="col-sm-5">预约时间：${a.appointmentTime }</div>
                                           <div class="col-sm-1"><button id="updateapp" class="btn btn-sm btn-primary"  onclick="updateApp('${a.appointmentId}')">修改</button></div>
                                           <div class="col-sm-1"><button id="delapp" class="btn btn-sm btn-danager"  onclick="delApp('${a.appointmentId}','${a.registrationId}')">删除</button></div>
                                   </div>
                                   <div class="row">
                                       <div class="col-sm-5">预约地点:${a.appointmentAddress}</div>
                                   </div>
                               </div>
                            </c:forEach>
                            <!-- 分页 -->
                            <div class="row">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <nav aria-lable="Page navigation">
                                        <ul class="pagination">

                                            <li><a href="${pageContext.request.contextPath}/common/tracking_info?pageNum=1&registrationId=${registration.registrationId}&employeeId=${employee.employeeId}">首页</a></li>

                                            <c:if test="${pageInfo.hasPreviousPage  }">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/common/tracking_info?pageNum=${pageInfo.pageNum-1}&registrationId=${registration.registrationId}&employeeId=${employee.employeeId}" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                                <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                                    <li><a href="${pageContext.request.contextPath}/common/tracking_info?pageNum=${page}&registrationId=${registration.registrationId}&employeeId=${employee.employeeId}">${page}</a></li>
                                                </c:if>
                                                <c:if test="${page==pageInfo.pageNum}">
                                                    <li class="active"><a href="${pageContext.request.contextPath}/common/tracking_info?pageNum=${page}&registrationId=${registration.registrationId}&employeeId=${employee.employeeId}">${page}</a></li>
                                                </c:if>
                                                <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                                    <li><a href="${pageContext.request.contextPath}/common/tracking_info?pageNum=${page}&registrationId=${registration.registrationId}&employeeId=${employee.employeeId}">${page}</a></li>
                                                </c:if>
                                            </c:forEach>

                                            <c:if test="${pageInfo.hasNextPage }">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/common/tracking_info?pageNum=${pageInfo.pageNum+1 }&registrationId=${registration.registrationId}&employeeId=${employee.employeeId}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <li><a href="${pageContext.request.contextPath}/common/tracking_info?pageNum=${pageInfo.pages}&registrationId=${registration.registrationId}&employeeId=${employee.employeeId}">末页</a></li>

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

                </div>
            </div>

        <%--弹出提示框--%>
        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel2">
                            添加预约信息
                        </h4>
                    </div>
                    <div class="modal-body" id="myModalBody2">
                        <form class="form-horizontal" id="appointment">
                            <div class="row">

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>预约时间<span class="red">*</span></label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="form-control input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                            <input class="form-control" name="appointmentTime" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <input type="hidden" id="dtp_input2" value="" /><br/>

                                    <script type="text/javascript">
                                        $(".form_date").datetimepicker({
                                            language: 'zh-CN',
                                            autoclose: true,  //当选择一个日期之后是否立即关闭此日期时间选择器。
                                            format: 'yyyy-mm-dd hh:ii',
                                            minView: 0,
                                            minuteStep:1,
                                            startDate:'2018-01-01 00:00'
                                        });
                                    </script>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    预约地址:
                                </div>
                                <div class="col-sm-5">
                                    <textarea name="appointmentAddress" cols="5" class="form-control"></textarea>
                                </div>
                            </div>
                                <input name="registrationId" type="hidden" value="${registration.registrationId}"/>
                                <input name="designerId"  type="hidden" value="${registration.designer.employeeId}"/>
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-4">
                                        <button type="button" class="btn btn-primary" onclick="setstatus('1')" id="sub">提交</button>
                                        <button type="button" class="btn btn-default" id="que">取消</button>
                                    </div>
                                </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

        <%--弹出提示框--%>
        <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel3">
                        </h4>
                    </div>
                    <div class="modal-body" id="myModalBody3">
                        <form class="form-horizontal" id="appointmentupdate">
                            <div class="row">

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label>预约时间<span class="red">*</span></label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="form-control input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                            <input class="form-control" id="appTime" name="appointmentTime" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <input type="hidden" id="dtp_input" value="" /><br/>

                                    <script type="text/javascript">
                                        $(".form_date").datetimepicker({
                                            language: 'zh-CN',
                                            autoclose: true,  //当选择一个日期之后是否立即关闭此日期时间选择器。
                                            format: 'yyyy-mm-dd hh:ii',
                                            minView: 0,
                                            minuteStep:1,
                                            startDate:'2018-01-01 00:00'
                                        });
                                    </script>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    预约地址:
                                </div>
                                <div class="col-sm-5">
                                    <textarea name="appointmentAddress" id="appAddress" cols="5" class="form-control"></textarea>
                                </div>
                            </div>
                            <input name="registrationId" type="hidden" value="${registration.registrationId}"/>
                            <input name="designerId"  type="hidden" value="${registration.designer.employeeId}"/>
                            <input id="appointmentId" name="appointmentId" type="hidden" value=""/>
                            <div class="row">
                                <div class="col-sm-4 col-sm-offset-4">
                                    <button type="button" class="btn btn-primary" onclick="setstatus('0')">提交</button>
                                    <button type="button" class="btn btn-default" id="que2">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
</div>

<script type="text/javascript">
    $.sidebarMenu($('.sidebar-menu'));
    $('#que').click(function(){
        $('#myModal2').modal('hide');
    });
    $('#addapp').bind('click',function () {

        $('#myModal2').modal('show');
    });

    function setstatus(stat) {
        var formdata = "";
            if(stat==1){
                url="${pageContext.request.contextPath}/common_intent/addAppointment";
                formdata = JSON.stringify($("#appointment").serializeObject())
            }else{
                url ="${pageContext.request.contextPath}/common_intent/changeAppointment"
                formdata = JSON.stringify($("#appointmentupdate").serializeObject())
            }

            $.post({
                url: url,
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: formdata,
                success : function (data) {
                    console.log(data);
                    if(data.result == 'true'){
                        $('#myModal2').modal('hide');
                        $('#myModal3').modal('hide');
                        $('#myModalBody').text('操作成功');
                        $('#myModal').modal('show')
                        $('#myModal').on('hidden.bs.modal', function () {
                            // 执行一些动作...
                            document.location.reload();
                        })
                    }else {
                        $('#myModal2').modal('hide');
                        $('#myModalBody').text('操作失败,'+data.msg);
                        $('#myModal').modal('show')
                    }

                },
                error : function(){
                    $('#myModal2').modal('hide');
                    $('#myModalBody').text('操作失败,请刷新页面');
                    $('#myModal').modal('show')
                }
            });
        }

    function updateApp(appId) {
        var json={
            "appointmentId":appId
        }
        $.post({
            url: "${pageContext.request.contextPath}/common_intent/updateAppointment",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(json),
            success : function (data) {
                console.log(data);
                    $('#appTime').val(data.appointment.appointmentTime)
                    $('#appAddress').val(data.appointment.appointmentAddress)
                    $('#appointmentId').val(data.appointment.appointmentId)
                    $('#myModal3').modal('show')
                    $('#myModal3').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    });
            },
            error : function(){
                $('#myModalBody3').text('操作失败,请刷新页面');
                $('#myModal3').modal('show')
            }
        });
    }

        function delApp(appId,registrationId){
            var json={
                "appointmentId":appId,
                "registrationId":registrationId
            }
            $.post({
                url: "${pageContext.request.contextPath}/common_intent/deleteAppointment",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: JSON.stringify(json),
                success : function (data) {
                    console.log(data);
                    $('#myModalBody').text(data.result);
                    $('#myModal').modal('show')
                    $('#myModal').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    });
                },
                error : function(){
                    $('#myModalBody').text('操作失败,请刷新页面');
                    $('#myModal').modal('show')
                }
        })
    }


</script>

</body>
</html>

