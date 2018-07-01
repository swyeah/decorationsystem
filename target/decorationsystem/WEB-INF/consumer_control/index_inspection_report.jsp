<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/6
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
        <div class="col-sm-8 col-sm-offset-2">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#home" data-toggle="tab">
                        工程验收报告
                    </a>
                </li>
            </ul>
            <div class="panel-body" >
                <div id="myTabContent" class="tab-content" style="font-size: 18px;">
                    <div class="tab-pane fade in active" id="home">
                        <c:if test="${requestScope.resultStatus == 1}">
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-4">工程验收报告编号：${inspectionReport.reportId}</div>
                                <div class="col-sm-4">客户登记编号：${inspectionReport.registrationId}</div>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-5">
                                    验收状态：
                                    <c:if test="${requestScope.inspectionReport.reportStatus==1}">
                                        <span class="green">通过</span>
                                    </c:if>
                                    <c:if test="${requestScope.inspectionReport.reportStatus==0}">
                                        <span class="red">未通过</span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-8">上传人员：${inspectionReport.employee.job.department.departmentName}&nbsp;${inspectionReport.employee.job.jobName}&nbsp;${inspectionReport.employee.employeeName}</div>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-8">上传时间：<fmt:formatDate value="${inspectionReport.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <div class="col-sm-2">合同图片：</div>
                                <div class="col-sm-8">
                                    <c:forEach items="${inspectionReportPics}" var="c">
                                        ${c.fileName}
                                        <img src="${c.fileUrl}" class="img-responsive" alt="Responsive image">
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.resultStatus==0}">
                            <p>无验收报告</p>
                        </c:if>
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
