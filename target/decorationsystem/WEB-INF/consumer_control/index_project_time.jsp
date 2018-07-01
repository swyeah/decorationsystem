<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/1
  Time: 21:43
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

</head>
<body>
<div class="container-fluid" style="padding-left: 0;padding-right: 0;">
    <%--顶部导航条--%>
        <jsp:include page="../indexHeader.jsp" flush="true"></jsp:include>


        <div class="row">
        <div class="col-sm-8 col-sm-offset-2" style="padding-top: 20px;">

            <div class="row">
                <form>
                    <div class="col-sm-2">开始时间：</div>
                    <div class="col-sm-3">
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
                    <div class="col-sm-2">结束时间：</div>
                    <div class="col-sm-3">
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
            </div>

            <div class="row">
                <c:forEach items="${projectTimes}" var="p">
                    <div class="col-sm-2" style="margin-left: 10px;margin-top: 10px;"><button onclick="window.location.href='${pageContext.request.contextPath}/welcome/progressContent?projectId=${p.projectId}'" class="btn btn-default"><fmt:formatDate value="${p.projectDay}" pattern="yyyy-MM-dd"/></button> </div>
                </c:forEach>
            </div>
            <!-- 分页 -->
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <nav aria-lable="Page navigation">
                        <ul class="pagination">

                            <li><a href="${pageContext.request.contextPath}/welcome/project_time?pageNum=1&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">首页</a></li>

                            <c:if test="${pageInfo.hasPreviousPage  }">
                                <li>
                                    <a href="${pageContext.request.contextPath}/welcome/project_time?pageNum=${pageInfo.pageNum-1}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/welcome/project_time?pageNum=${page}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">${page}</a></li>
                                </c:if>
                                <c:if test="${page==pageInfo.pageNum}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/welcome/project_time?pageNum=${page}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">${page}</a></li>
                                </c:if>
                                <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/welcome/project_time?pageNum=${page}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">${page}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${pageInfo.hasNextPage }">
                                <li>
                                    <a href="${pageContext.request.contextPath}/welcome/project_time?pageNum=${pageInfo.pageNum+1 }&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <li><a href="${pageContext.request.contextPath}/welcome/project_time?pageNum=${pageInfo.pages}&startTime=${map.startTime}&endTime=${map.endTime}&registrationId=${map.registrationId}">末页</a></li>

                        </ul>
                    </nav>
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
                </div>
        </div><!-- /.modal -->

    </div>

</div>
<script type="text/javascript">
</script>


</body>
</html>
