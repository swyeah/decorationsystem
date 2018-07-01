<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/1
  Time: 17:38
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
        <div class="col-sm-8 col-sm-offset-2" style="background: rgba(245,245,245,0.8)">
            <div class="row" style="text-align: center;">
                <h3>${designerShow.showTitle}</h3>
            </div>
            <div class="row">
                <div class="col-sm-5"><small>上传时间：<fmt:formatDate value="${designerShow.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/></small></div>
            </div>
            <hr style="margin: 10px 0 10px 0;">
            <div class="row" style="margin-bottom: 10px;">
                <small>点赞次数：${designerShow.praiseCount}&nbsp;&nbsp;&nbsp;收藏次数：${designerShow.collectCount}&nbsp;&nbsp;&nbsp;设计师：${employee.employeeName}</small>
            </div>
            <div class="row" style="padding-left: 10px;">
                ${designerShow.showContent}
            </div>
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3">
                    <button class="btn btn-default" onclick="good('${designerShow.showId}')" id="goodbtn">点赞<i class="glyphicon glyphicon-thumbs-up"></i></button>
                    <button class="btn btn-default" id="collectbtn" onclick="collect('${designerShow.showId}')">收藏<i class="glyphicon glyphicon-star-empty"></i></button>
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
                        $('#goodbtn').text('点赞成功')

                    }else {
                        $('#myModalBody').text('已经点过赞了！');
                        $('#myModal').modal('show')
                    }

                },
                error : function(){
                    $('#myModalBody').text('点赞失败,请刷新页面');
                    $('#myModal').modal('show')
                }
            });

        }
        function collect(showId){
            var json = {
                "showId":showId,
                "collectman":${sessionScope.consumer.consId}
            }

            $.post({
                url: "${pageContext.request.contextPath}/welcome/setcollect",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: JSON.stringify(json),
                success : function (data) {
                    console.log(data);
                    if (data.msg =="1"){
                        $('#collectbtn').text('收藏成功')

                    }else {
                        $('#myModalBody').text('已经收藏了！');
                        $('#myModal').modal('show')
                    }

                },
                error : function(){
                    $('#myModalBody').text('收藏失败,请刷新页面');
                    $('#myModal').modal('show')
                }
            });

        }
    </script>


</body>
</html>
