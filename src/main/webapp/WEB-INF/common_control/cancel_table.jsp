<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/14
  Time: 15:28
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
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>dist/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <link href="<%=basePath%>css/indexstyle.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/nav.js"></script>
    <script type="text/javascript">
        function consShow(userName) {
            $.post({
                url: "${pageContext.request.contextPath}/common/getConsumer",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: JSON.stringify({userName:userName}),
                success : function (data) {
                    console.log(data);


                },
                error : function(){
                    $('#myModalBody').text('请重新刷新页面');
                    $('#myModal').modal('show')
                }
            });
        }
    </script>
</head>
<body>

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
                    <li class="active">登记客户</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">登记客户
                    <div class="pull-right">总共${pageInfo.pages }页，总共${pageInfo.total }条记录</div>
                    </div>
                    <div class="panel-body">
                        登记客户为市场人员未指定设计师的客户，即该客户会被市场人员跟踪，市场人员指定设计师后该客户变为为意向客户
                    </div>
                    <div class="table-responsive">
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
                                    <td>${r.registrationName}</td>
                                    <td>${r.registrationPhone}</td>
                                    <td>${r.city.province.provinceName}${r.city.cityName}</td>
                                    <td>${r.registrationArea}</td>
                                    <td>${r.registrationSource}</td>
                                    <td>${r.employee.employeeName}</td>
                                    <td>${r.designer.employeeName}</td>
                                    <td>${r.businessStatus}</td>
                                    <td>${r.trackingTimes}</td>
                                    <td>${r.days}</td>
                                    <td>${r.warningTimes}</td>
                                    <td>
                                        <div  class="btn-group btn-group-xs" role="group">
                                            <button type="button"  onclick="setdesigner('${r.registrationId}','${r.version}')" id="set_designer" class="btn btn-default">指定设计师</button>
                                            <button type="button"   class="btn btn-info" id="tracking" onclick="tracking('${r.registrationId}')">跟踪信息</button>
                                            <button type="button" id="set_quee" class="btn btn-danger">飞单申请</button>
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

                                    <li><a href="${pageContext.request.contextPath}/common/consumer_management?pageNum=1">首页</a></li>

                                    <c:if test="${pageInfo.hasPreviousPage  }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/common/consumer_management?pageNum=${pageInfo.pageNum-1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                        <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/common/consumer_management?pageNum=${page}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page==pageInfo.pageNum}">
                                            <li class="active"><a href="${pageContext.request.contextPath}/common/consumer_management?pageNum=${page}">${page}</a></li>
                                        </c:if>
                                        <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                            <li><a href="${pageContext.request.contextPath}/common/consumer_management?pageNum=${page}">${page}</a></li>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${pageInfo.hasNextPage }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/common/consumer_management?pageNum=${pageInfo.pageNum+1 }" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <li><a href="${pageContext.request.contextPath}/common/consumer_management?pageNum=${pageInfo.pages}">末页</a></li>

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

        </div>
    </div>


</div>
<script type="text/javascript">
    $.sidebarMenu($('.sidebar-menu'));
    function tracking(registrationId) {
        window.location.href="${pageContext.request.contextPath}/common/tracking_info?registrationId="+registrationId+"${r.registrationId}&employeeId=${sessionScope.employee.employeeId }";
    }
    /*指定设计师按钮响应方法*/
    function setdesigner(registration,versionNum) {
        registrationId = registration;
        version = versionNum;
        var json = {
            "pageNum":'1',
            "search":'',
            "version":version
        }
        show_message(json,"");
        $('#myModal').modal('show');
    };
    function saveDesigner(employeeId,deptId) {
       var json = {
           "registrationId":registrationId,
           "employeeId":employeeId,
           "version":version
       }
        $.post({
            url: "${pageContext.request.contextPath}/common/management_setdesigner",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(json),
            success : function (data) {
                console.log(data);
                $('#myModalBody').text(data.result);
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
    /*搜索按钮响应方法*/
    function search_data(){
        var search = $('#search').val();
        var json = {
            "search":search,
            "pageNum":"1",
            "version":version
        };
        show_message(json,search);

    }
    function changshow(pageNum){
        var search = $('#search').val();
        var json = {
            "search":search,
            "pageNum":pageNum,
            "version":version
        }
        show_message(json,search);

    }


    function show_message(resultdata,searchdata) {
        $.post({
            url: "${pageContext.request.contextPath}/common/getDesigner",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:JSON.stringify(resultdata),
            success : function (data) {
                console.log(data);
                var str2 = "<div class='row'>\n" +
                    "                <form class=\"form-horizontal\" id='search_form' >\n" +
                    "                    <div class=\"input-group col-sm-6\" style=\"margin-bottom: 10px;\">\n" +
                    "                        <input type=\"text\" id='search' name=\"search\" class=\"form-control col-sm-4\" placeholder=\"请输入要查找的内容\" aria-describedby=\"basic-addon2\">\n" +
                    "                        <span class=\"input-group-addon\" id=\"basic-addon2\"><button id='searchBtn' type=\"button\" onclick='search_data()'><i class=\"glyphicon glyphicon-search\"></i></button></span>\n" +
                    "                    </div>\n" +
                    "\n" +
                    "                </form>\n" +
                    "            </div>";
                var str=str2+" <div class=\"row\">\n" +
                    "                <div class=\"table-responsive\">\n" +
                    "                    <table class=\"table table-bordered\">\n" +
                    "                        <tr>\n" +
                    "                            <td>员工编号</td>\n" +
                    "                            <td>设计师姓名</td>\n" +
                    "                            <td>部门</td>\n" +
                    "                            <td>职务</td>\n" +
                    "                            <td>操作</td>\n" +
                    "                        </tr>";

                if (data != 0) {
                    for ( var i = 0; i < data[1].length; i++) {
                        var employeeId = data[1][i].employeeId;
                        var employeeName = data[1][i].employeeName;
                        var dept = data[1][i].job.department.departmentName;
                        var deptId = data[1][i].job.department.departmentId;
                        var job = data[1][i].job.jobName;
                        var lastpage = parseInt(data[0].pageNum)-1;
                        var nextpage = parseInt(data[0].pageNum)+1;
                        var str1 = " <tr>\n" +
                            "                                <td>"+employeeId+"</td>\n" +
                            "                                <td>"+employeeName+"</td>\n" +
                            "                                <td>"+dept+"</td>\n" +
                            "                                <td>"+job+"</td>\n" +
                            "                                <td>\n" +
                            "                                    <button class=\"button\"  onclick=\"saveDesigner('"+employeeId+"','"+deptId+"')\">\n" +"指定"+
                            "                                </td>\n" +
                            "                            </tr>"
                        str = str+str1;
                    }
                    str = str+"</table><div>";
                    str = str + "<div class='row' style='margin-right: 0px;'> <div class='col-xs-6 col-xs-offset-3'> <nav aria-label=\"Page navigation\" >\n" +
                        "  <ul class=\"pagination\">\n" ;

                    if(data[0].hasPreviousPage) {
                        str = str + "    <li>\n" +
                            "      <a onclick="+"changshow('"+lastpage+"')  aria-label=\"Previous\">\n" +
                            "        <span aria-hidden=\"true\">上一页</span>\n" +
                            "      </a>\n" +
                            "    </li>\n";
                    }
                    if(data[0].hasNextPage) {
                        str = str+    "    <li>\n" +
                            "      <a onclick="+"changshow('"+nextpage+"') aria-label=\"Next\">\n" +
                            "        <span aria-hidden=\"true\">下一页</span>\n" +
                            "      </a>\n" +
                            "    </li>\n";
                    }
                    str = str +    "  </ul>\n" +
                        "</nav></div></div>"
                }
                $('#myModalBody').html(str);
                $('#search').val(searchdata);


            },
            error : function(){
                $('#myModalBody').text('请重新刷新页面');
                $('#myModal').modal('show')
            }
        });
    }
</script>

</body>
</html>
