<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/11
  Time: 22:06
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
        #trackinginfo{
            border: 1px solid #9acfea;
            border-radius: 10px 10px 10px 10px;
            padding-left: 10px;
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
        <div class="col-xs-10 col-xs-offset-1 col-sm-9 col-sm-offset-0">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li class="active">客户管理</li>
                    <li class="active">跟踪信息</li>
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
                            <div class="col-xs-4">区域:${registration.city.province.provinceName}${registration.city.cityName}</div>
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
                                <div class="col-xs-4 col-sm-2">跟踪信息</div>
                                <div class="col-xs-2 col-sm-1 col-xs-offset-3 col-sm-offset-8" ><button id="addInfo" class="btn btn-default btn-sm">添加跟踪信息</button> </div>
                            </div>
                        </div>
                        <div class="panel-body"  style="padding-left: 50px; padding-right: 50px;">
                            <c:forEach items="${trackingInfos}" var="t" varStatus="s">
                               <div class="row" id="trackinginfo">
                                   <div class="row">
                                       <div class="col-sm-3">跟踪人员：${t.employee.employeeName}</div>
                                       <div class="col-sm-5">跟踪日期：<fmt:formatDate value="${t.infoTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </div>
                                       <div class="col-sm-3">跟踪方式：${t.infoType}</div>
                                       <c:if test="${s.count==1 && pageInfo.pageNum<=1}">
                                           <div class="col-sm-1"><button id="updateinfo" class="btn btn-sm btn-default"  onclick="updateInfo('${t.infoId}')">修改</button></div>
                                       </c:if>
                                   </div>
                                   <div class="row">
                                       <div class="col-sm-3">业务状态:${t.businessStatus}</div>
                                       <div class="col-sm-3">是否警告：<c:if test="${t.warning==true}">是</c:if><c:if test="${t.warning==false}">否</c:if> </div>
                                   </div>
                                   <div class="row">
                                       <div class="col-sm-3">跟踪内容：</div>
                                       <div class="col-sm-9">${t.infoContent}</div>
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


        </div>
        <script type="text/javascript">
            $.sidebarMenu($('.sidebar-menu'));
            $('#que').click(function(){
                $('#myModal').modal('hide');
            });
            $('#addInfo').bind('click',function () {
                var str = "<form class=\"form-horizontal\" id=\"info\">\n" +
                    "                                <div class='row'>\n" +
                    "                                    <div class='col-sm-4'>跟踪人员：${employee.employeeName}</div>\n" +
                    "                                    <div class='col-sm-3'>跟踪方式：</div>\n" +
                    "                                    <div class=\"col-sm-4\">\n" +
                    "                                        <select name=\"infoType\" class=\"form-control\">\n" +
                    "                                            <option value=\"电话\">电话</option>\n" +
                    "                                            <option value=\"短信\">短信</option>\n" +
                    "                                        </select>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                                <div class='row'>" +
                    "                                    <div class='col-sm-2'>业务状态:</div>"+
                    "                                    <div class='col-sm-3'>" +
                    "                                         <select name='businessStatus' class='form-control'>" +
                    "                                            <option value='一般'>一般</option>"+
                    "                                            <option value='满意'>满意</option>"+
                    "                                    </select></div>" +
                    "                                    <div class='col-sm-2'>是否警告:</div>"+
                    "                                    <div class='col-sm-3'>" +
                    "                                         <select name='warning' class='form-control'>" +
                    "                                            <option value='false'>否</option>"+
                    "                                            <option value='true'>是</option>"+
                    "                                    </select></div>" +
                    "                                </div>"+
                    "                                <div class=\"row\">\n" +
                    "                                    <div class=\"col-sm-3\">跟踪内容：</div>\n" +
                    "                                    <div class=\"col-sm-9\"><textarea name=\"infoContent\" class=\"form-control\" cols=\"5\"></textarea>\n" +
                    "                                    <input type=\"hidden\" name=\"employeeId\" value=\"${employee.employeeId}\"/>\n" +
                    "                                    <input type=\"hidden\" name=\"registrationId\" value=\"${registration.registrationId}\"/>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                                <div class=\"row\">\n" +
                    "                                    <div class=\"col-sm-4 col-sm-offset-4\">\n" +
                    "                                        <button id=\"sub\" onclick=\"setstatus('1')\" class=\"btn btn-primary\">提交</button>\n" +
                    "                                        <button id=\"que\" class=\"btn btn-default\">取消</button>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </form>"
                $('#myModalBody').html(str);
                $('#myModal').modal('show');
            });
            function updateInfo(infoId) {
                var json = {
                    "infoId":infoId
                }
                $.post({
                    url: "${pageContext.request.contextPath}/common/getTrackingInfo",
                    dataType : "json",//预期服务器返回的类型c
                    contentType: "application/json",
                    data: JSON.stringify(json),
                    success : function (data) {
                        console.log(data);
                        if(data !=null){
                            var str = "<form class=\"form-horizontal\" id=\"info\">\n" +
                                "                                <div class='row'>\n" +
                                "                                    <div class='col-sm-4'>跟踪人员："+data.employee.employeeName+"</div>\n" +
                                "                                    <div class='col-sm-3'>跟踪方式：</div>\n" +
                                "                                    <div class=\"col-sm-4\">\n"+
                                "                                   <select name='infoType' class='form-control'>"
                            var select ;
                            if(data.infoType=="电话"){
                                select = "<option value='电话' selected='selected'>电话</option><option value='短信'>短信</option>"
                            }else{
                                select = "<option value='电话'>电话</option><option  selected='selected' value='短信'>短信</option>"
                            }
                            str=str+select+     "                                   </select></div>\n" +
                                "                                </div>\n" +
                                "                                <div class='row'>" +
                                "                                <div class='col-sm-2'>业务状态</div>"+
                                "                                    <div class='col-sm-3'>" +
                                "                                      <select name='businessStatus' class='form-control'>"
                            if(data.businessStatus=="一般"){
                                select = "<option value='一般' selected='selected'>一般</option><option value='满意'>满意</option>"
                            }else{
                                select = "<option  selected='selected' value='满意'>满意</option><option value='一般'>一般</option>"
                            }
                            str = str+select+
                                "                                       </select></div>" +
                                "                                <div class='col-sm-2'>是否警告</div>"+
                                "                                    <div class='col-sm-3'>" +
                                "                                         <select name='warning' class='form-control'>"
                            if(data.warning==true){
                                select = "<option value='true' selected='selected'>是</option><option value='false'>否</option>"
                            }else{
                                select = "<option value='true'>是</option><option  selected='selected' value='false'>否</option>"
                            }
                            str=str+select+
                                "                                    </select></div>" +
                                "                                </div>"+
                                "                                <div class=\"row\">\n" +
                                "                                    <div class=\"col-sm-3\">跟踪内容：</div>\n" +
                                "                                    <div class=\"col-sm-9\"><textarea name=\"infoContent\" class=\"form-control\" cols=\"5\">"+data.infoContent+"</textarea>\n" +
                                "                                    <input type=\"hidden\" name=\"employeeId\" value=\""+data.employee.employeeId+"\"/>\n" +
                                "                                    <input type=\"hidden\" name=\"registrationId\" value=\""+data.registrationId+"\"/>\n" +
                                "                                    <input type='hidden' name='infoId' value='"+data.infoId+"'/>"+
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                                <div class=\"row\">\n" +
                                "                                    <div class=\"col-sm-4 col-sm-offset-4\">\n" +
                                "                                        <button id=\"sub\" onclick=\"setstatus('2')\" class=\"btn btn-primary\">提交</button>\n" +
                                "                                        <button id=\"que\" class=\"btn btn-default\">取消</button>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </form>"
                            $('#myModalBody').html(str);
                            $('#myModal').modal('show');
                            $('#myModal').on('hidden.bs.modal', function () {
                                // 执行一些动作...
                                document.location.reload();
                            })
                        }else {
                            $('#myModalBody').text('操作失败');
                            $('#myModal').modal('show')

                        }

                    },
                    error : function(){
                        $('#myModalBody').text('操作失败,请刷新页面');
                        $('#myModal').modal('show')
                    }
                });
            }

            $('#info').bootstrapValidator({
//        live: 'disabled',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    infoContent: {
                        validators: {
                            notEmpty: {
                                message: '跟踪内容不能为空！'
                            },
                            stringLength: {//检测长度
                                min: 1,
                                max: 50,
                                message: '字数必须在1-50之间'
                            }
                        }
                    }
                }
            });

            function setstatus(stat) {
                $('#info').bootstrapValidator('validate');
                if($('#info').data("bootstrapValidator").isValid()){
                    if(stat==1){
                        url="${pageContext.request.contextPath}/common/addTrackingInfo";
                    }else{
                        url ="${pageContext.request.contextPath}/common/updateTrackingInfo"
                    }
                    var formdata = JSON.stringify($("#info").serializeObject());
                    $.post({
                        url: url,
                        dataType : "json",//预期服务器返回的类型c
                        contentType: "application/json",
                        data: formdata,
                        success : function (data) {
                            console.log(data);
                            if(data.result == 'true'){
                                $('#myModalBody').text('操作成功');
                                $('#myModal').modal('show')
                                $('#myModal').on('hidden.bs.modal', function () {
                                    // 执行一些动作...
                                    document.location.reload();
                                })
                            }else {
                                $('#myModalBody').text('操作失败'+data.msg);
                                $('#myModal').modal('show')
                            }

                        },
                        error : function(){
                            $('#myModalBody').text('操作失败,请刷新页面');
                            $('#myModal').modal('show')
                        }
                    });
                }
            }


        </script>

</body>
</html>

