<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/4
  Time: 0:22
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
                    <li>客户管理</li>
                    <li class="active">合同客户</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">合同客户
                        <div class="pull-right">总共${pageInfo.pages }页，总共${pageInfo.total }条记录</div>
                    </div>
                    <div class="panel-body">
                       合同客户为签订合同的客户，这里可以查看工程进度，工程验收，设置保修期限
                    </div>

                    <div class="row" style="margin-left: 10px; margin-bottom: 10px;">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/common_contract/common_management_contract" method="get">
                            <div class="col-sm-2">
                                start
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                                    <input class="form-control"  name="startTime" size="16" type="text" value="" readonly>
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
                            <div class="col-sm-2">
                                end
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" name="endTime" size="16" type="text" value="" readonly>
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
                                <input name="sname" class="form-control" placeholder="姓名模糊查询">
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
                            <div class="col-sm-2">
                                &nbsp;
                                <button class="btn btn-default btn-lg" type="submit"><i class="glyphicon glyphicon-search"></i> </button>
                            </div>
                        </form>
                    </div>

                    <div class="table-responsive" >
                        <!-- Table -->
                        <table class="table table-hover">
                            <tr  class="text-center">
                                <th>登记编号</th>
                                <th>登记时间</th>
                                <th>姓名</th>
                                <th>手机号码</th>
                                <th>区域</th>
                                <th>面积</th>
                                <th>来源</th>
                                <th>市场人员</th>
                                <th>设计师</th>
                                <th>合同开始时间</th>
                                <th>合同结束时间</th>
                                <th>工程状态</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${registrationTables}" var="r" varStatus="i" >
                                <tr>
                                    <td>${r.registrationId}</td>
                                    <td>${r.registrationTime}</td>
                                    <td>${r.registrationName}</td>
                                    <td>${r.registrationPhone}</td>
                                    <td>${r.city.province.provinceName}${r.city.cityName}</td>
                                    <td>${r.registrationArea}</td>
                                    <td>${r.registrationSource}</td>
                                    <td>${r.employee.employeeName}</td>
                                    <td>${r.designer.employeeName}</td>
                                    <td><fmt:formatDate value="${r.startTime}" pattern="yyyy-MM-dd"/></td>
                                    <td><fmt:formatDate value="${r.endTime}" pattern="yyyy-MM-dd"/></td>
                                    <td>
                                        <c:if test="${r.projectStatus==0}"><span class="red">未知状态</span> </c:if>
                                        <c:if test="${r.projectStatus==1}"><span class="blue">未开工</span> </c:if>
                                        <c:if test="${r.projectStatus==2}"><span class="blue">施工中</span> </c:if>
                                        <c:if test="${r.projectStatus==3}"><span class="red">施工逾期</span> </c:if>
                                        <c:if test="${r.projectStatus==4}"><span class="red">验收未通过</span> </c:if>
                                        <c:if test="${r.projectStatus==5}"><span class="green">验收通过</span> </c:if>


                                    </td>
                                    <td>
                                        <div  class="btn-group btn-group-xs" role="group">
                                            <button type="button"  onclick="window.location.href='${pageContext.request.contextPath}/common_contract/project_time?registrationId=${r.registrationId}'"  class="btn btn-default">工程进度</button>
                                            <button type="button"  onclick="window.location.href='${pageContext.request.contextPath}/common_contract/inspection_report?registrationId=${r.registrationId}'"  class="btn btn-default">工程验收</button>
                                            <button type="button" id="guarantee" onclick="guarantee('${r.registrationId}')"  class="btn btn-primary">保修期限</button>
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

                                    <li><a href="${pageContext.request.contextPath}/common_contract/common_management_contract?pageNum=1&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}">首页</a></li>

                                    <c:if test="${pageInfo.hasPreviousPage  }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/common_contract/common_management_contract?pageNum=${pageInfo.pageNum-1}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                        <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/common_contract/common_management_contract?pageNum=${page}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page==pageInfo.pageNum}">
                                            <li class="active"><a href="${pageContext.request.contextPath}/common_contract/common_management_contract?pageNum=${page}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/common_contract/common_management_contract?pageNum=${page}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}">${page}</a></li>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${pageInfo.hasNextPage }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/common_contract/common_management_contract?pageNum=${pageInfo.pageNum+1 }&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <li><a href="${pageContext.request.contextPath}/common_contract/common_management_contract?pageNum=${pageInfo.pages}&startTime=${searchContent.startTime}&endTime=${searchContent.endTime}&sname=${searchContent.sname}&provinceId=${searchContent.provinceId}&cityId=${searchContent.cityId}">末页</a></li>

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
                            <h4 class="modal-title" id="myModalLabe2">
                                保修期限
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <form id="guarateeform">
                                <div class="row">
                                    <div class="col-sm-3">
                                        保修开始时间：
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                                            <input class="form-control"  name="startTime" size="16" type="text" value="" readonly>
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
                                </div>
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-sm-3">
                                            保修年限：
                                        </div>
                                        <div class="col-sm-3 ">
                                        <div class="form-group input-group">
                                            <input class="form-control" name="year" id="year" maxlength="2">
                                            <span class="input-group-addon">年</span>
                                        </div>
                                        </div>
                                        <div class="col-sm-3">
                                        <div class=" form-group input-group">
                                            <input class="form-control" name="month" id="month" maxlength="2">
                                            <span class="input-group-addon">月</span>
                                        </div>
                                        </div>
                                        <input hidden="hidden" name="registrationId" id="registrationId1" />
                                    </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="sub">提交</button>
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
    function guarantee(registrationId) {
        $('#registrationId1').val(registrationId)
        $('#myModal2').modal('show')

    }
    $('#guarateeform').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            startTime:{
                validators:{
                    notEmpty:{
                        message:'不能为空'
                    }
                }
            },
            year:{
                validators:{
                    notEmpty:{
                        message:'不能为空'
                    },
                    digits: {
                        message: '只能是数字。'
                    },
                    greaterThan:{
                        value:0,
                        message:'年要大于0'
                    },
                    lessThan:{
                        value:5,
                        message:'年要小于5'
                    }
                }
            },
            month:{
                validators:{
                    notEmpty:{
                        message:'不能为空'
                    },
                    digits: {
                        message: '只能是数字。'
                    },
                    greaterThan:{
                        value:0,
                        message:'月要大于0'
                    },
                    lessThan:{
                        value:12,
                        message:'月要小于12'
                    }
                }
            }
        }

    });
    $('#sub').click(function() {
        $('#guarateeform').bootstrapValidator('validate');
        if($('#guarateeform').data("bootstrapValidator").isValid()){

            var formdata = JSON.stringify($("#guarateeform").serializeObject());
            $.post({
                url: "${pageContext.request.contextPath}/common_contract/saveGuarantee",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: formdata,
                success : function (data) {
                    console.log(data);
                    $('#myModalBody2').html(data.msg);
                    $('#sub').hide()
                    $('#myModal2').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                },
                error : function(){
                    $('#myModalBody2').text('提交失败！');
                    $('#myModal2').on('hidden.bs.modal', function () {
                        // 执行一些动作...
                        document.location.reload();
                    })
                }
            });
        }
    });
</script>

</body>
</html>

