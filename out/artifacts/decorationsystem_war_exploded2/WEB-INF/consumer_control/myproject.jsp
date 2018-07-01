<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/2
  Time: 0:17
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
        <span class="col-sm-8 col-sm-offset-2">
            <div class="row" style="margin-top: 10px;">
                <button class="btn btn-default" onclick="window.location.href='${pageContext.request.contextPath}/welcome/index'">返回首页</button>
            </div>
            <div class="row"><h3>我的项目</h3></div>
            <hr>
            <c:if test="${resultStatus==1}">
            <c:forEach items="${registrationTables}" var="r">
                <ul class="list-group">
                    <li class="list-group-item">
                        登记编号：${r.registrationId}
                        <div class="col-sm-10">
                            <button class="btn btn-default btn-sm " onclick="window.location.href='${pageContext.request.contextPath}/welcome/housepic?registrationId=${r.registrationId}'">图纸管理</button>
                            <button class="btn btn-default btn-sm " onclick="window.location.href='${pageContext.request.contextPath}/welcome/project_time?registrationId=${r.registrationId}'">工程进度</button>
                            <button class="btn btn-default btn-sm " onclick="window.location.href='${pageContext.request.contextPath}/welcome/inspection_report?registrationId=${r.registrationId}'">工程验收</button>
                            <button class="btn btn-default btn-sm " onclick="window.location.href='${pageContext.request.contextPath}/webSocket/consumer_communication?toid=${r.designer.employeeId}'">联系设计师</button>

                        </div>
                    </li>
                    <li class="list-group-item">
                        登记时间：${r.registrationTime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登记名字：${r.registrationName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登记电话：${r.registrationPhone}
                    </li>
                    <li class="list-group-item">
                        登记地区：${r.city.province.provinceName}${r.city.cityName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市场人员：${r.employee.employeeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设计师：${r.designer.employeeName}
                    </li>
                    <li class="list-group-item">
                        详细地址：${r.registrationAddress}
                    </li>
                    <li class="list-group-item">
                    <span class="red">当前状态：
                    <c:if test="${r.status ==1}">等待指定设计师</c:if>
                     <c:if test="${r.status ==2}">定金协议，量房</c:if>
                     <c:if test="${r.status ==3}">等待合同上传</c:if>
                     <c:if test="${r.status ==4}">工程进行中</c:if>
                     <c:if test="${r.status ==5}">项目完工</c:if>
                     <c:if test="${r.status ==6}">登记失效</c:if>
                </span>
                        &nbsp;
                    </li>

                </ul>
            </c:forEach>

        </div>
        <!-- 分页 -->
        <div class="row">
            <div class="col-sm-4 col-sm-offset-4">
                <nav aria-lable="Page navigation">
                    <ul class="pagination  ">

                        <li><a href="${pageContext.request.contextPath}/welcome/myproject?pageNum=1">首页</a></li>

                        <c:if test="${pageInfo.hasPreviousPage  }">
                            <li>
                                <a href="${pageContext.request.contextPath}/welcome/myproject?pageNum=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                        <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/welcome/myproject?pageNum=${page}">${page}</a></li>
                        </c:if>
                        <c:if test="${page==pageInfo.pageNum}">
                            <li class="active"><a href="${pageContext.request.contextPath}/welcome/myproject?pageNum=${page}">${page}</a></li>
                        </c:if>
                        <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                        <li><a href="${pageContext.request.contextPath}/welcome/myproject?pageNum=${page}">${page}</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage }">
                            <li>
                               <a href="${pageContext.request.contextPath}/welcome/myproject?pageNum=${pageInfo.pageNum+1 }" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        </c:if>

                        <li><a href="${pageContext.request.contextPath}/welcome/myproject?pageNum=${pageInfo.pages}">末页</a></li>

                    </ul>
                </nav>
            </div>
        </div><%--分页--%>
            </c:if>
        <c:if test="${resultStatus==0}">
            <h3>无项目记录</h3>
        </c:if>
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
    function good(showId){
        var json = {
            "showId":showId,
            "praiseMan":${sessionScope.consumer.consId}
        }

        $.post({
            url: "${pageContext.request.contextPath}/welcome/setgood",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(json),
            success : function (data) {
                console.log(data);
                if (data.msg =="1"){
                    alert('成功点赞！')

                }else {
                    alert(data.msg)
                }

            },
            error : function(){
                $('#myModalBody').text('登记失败,请刷新页面');
                $('#myModal').modal('show')
            }
        });

    }
</script>


</body>
</html>
