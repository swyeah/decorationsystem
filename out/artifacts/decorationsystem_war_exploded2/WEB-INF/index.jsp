<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/1
  Time: 0:19
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


</head>
<body>
<div class="container-fluid" style="padding-left: 0;padding-right: 0;">
    <jsp:include page="./indexHeader.jsp" flush="true"></jsp:include>


    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <div class="row" style="margin: 15px 0 0 0;">
                <div class="col-sm-6 pull-right" >
                    <form method="get" id="formset" action="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}">
                        <div class="col-xs-8 col-sm-10 col-md-11 "><input type="text" class="form-control" id="sname" name="sname" placeholder="搜索文章标题"> </div>
                        <div class="col-xs-4 col-sm-2 col-md-1"><button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button> </div>
                    </form>
                </div>
            </div>

            <script>
               $(document).ready(function () {
                   var sname = ${searchContent.sname}
                   $('#sname').val(sname)
               })
            </script>

            <div class="row">
                <hr>
                <ul class="list-group">
                    <li class="list-group-item">
                        <span>风格</span>&nbsp;
                        <span>|</span>
                        <div class="col-xs-1 pull-right">
                            <button class="btn btn-primary btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/welcome/index?pageNum=1&sname=${searchContent.sname}&areaId=${searchContent.areaId}&houseId=${searchContent.houseId}'">清除</button>
                        </div>
                        <c:forEach items="${decorationStyles}" var="s">
                            <c:if test="${s.styleId==searchContent.styleId}">
                                <span><a href="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${s.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}" class="red" >${s.styleName}</a></span>&nbsp;
                            </c:if>
                            <c:if test="${s.styleId!=searchContent.styleId}">
                                <span><a href="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${s.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}" >${s.styleName}</a></span>&nbsp;
                            </c:if>
                        </c:forEach>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    </li>
                    <li class="list-group-item">
                        <span>户型</span>&nbsp;
                        <span>|</span>
                        <c:forEach items="${houseTypes}" var="s" >
                            <c:if test="${s.houseId==searchContent.houseId}">
                                <span><a href="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${s.houseId}&areaId=${searchContent.areaId}" class="red" >${s.houseName}</a></span>&nbsp;
                            </c:if>
                            <c:if test="${s.houseId!=searchContent.houseId}">
                                <span><a href="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${s.houseId}&areaId=${searchContent.areaId}" >${s.houseName}&nbsp;</a></span>&nbsp;
                            </c:if>

                        </c:forEach>
                        <c:if test="${houseTypes.size()<10}">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                        <div class="col-xs-1 pull-right">
                            <button class="btn btn-primary btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&sname=${searchContent.sname}&areaId=${searchContent.areaId}'">清除</button>
                        </div>
                    </li>

                    <li class="list-group-item">
                        <span>面积</span>&nbsp;
                        <span>|</span>
                        <c:forEach items="${areaTypes}" var="s">
                            <c:if test="${s.areaId==searchContent.areaId}">
                                <span><a href="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${s.areaId}" class="red" >${s.areaName}</a></span>&nbsp;
                            </c:if>
                            <c:if test="${s.areaId!=searchContent.areaId}">
                                <span><a href="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${s.areaId}" >${s.areaName}</a></span>&nbsp;
                            </c:if>
                        </c:forEach>
                        <div class="col-xs-1 pull-right">
                            <button class="btn btn-primary btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}'">清除</button>
                        </div>
                    </li>

                </ul>

            </div>

            <div class="row ">
                <c:forEach items="${designerShows}" var="d" begin="0" end="2">
                    <div class="col-sm-6 col-md-4">
                        <a href="${pageContext.request.contextPath}/welcome/designershow?showId=${d.showId}">
                        <div class="thumbnail">
                            <img src="${d.showTitlepic}"     alt="图片缺失">
                            <div class="caption">
                                <h4>
                                    <c:if test="${d.showTitle.length()>10}">
                                        ${d.showTitle.substring(0,10)}...
                                    </c:if>
                                    <c:if test="${d.showTitle.length()<=10}">
                                        ${d.showTitle}
                                    </c:if>
                                </h4>

                                <small>上传时间：<fmt:formatDate value="${d.uploadTime}" pattern="yyyy-MM-dd"/></small>
                                <p><a href="#" class="btn btn-default btn-sm"  role="button"><i class="glyphicon glyphicon-thumbs-up"></i>${d.praiseCount} </a> <a href="#" class="btn btn-default btn-sm" id="collectbtn" role="button"><i class="glyphicon glyphicon-star-empty"></i>${d.collectCount}</a></p>
                            </div>
                        </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
            <div class="row ">
                <c:forEach items="${designerShows}" var="d" begin="3" end="5">
                    <div class="col-sm-6 col-md-4">
                        <a href="${pageContext.request.contextPath}/welcome/designershow?showId=${d.showId}">
                            <div class="thumbnail">
                                <img src="${d.showTitlepic}"     alt="图片缺失">
                                <div class="caption">
                                    <h4>
                                        <c:if test="${d.showTitle.length()>10}">
                                            ${d.showTitle.substring(0,10)}...
                                        </c:if>
                                        <c:if test="${d.showTitle.length()<=10}">
                                            ${d.showTitle}
                                        </c:if>
                                    </h4>

                                    <small>上传时间：<fmt:formatDate value="${d.uploadTime}" pattern="yyyy-MM-dd"/></small>
                                    <p><a href="#" class="btn btn-default btn-sm"  role="button"><i class="glyphicon glyphicon-thumbs-up"></i>${d.praiseCount} </a> <a href="#" class="btn btn-default btn-sm" id="collectbtn" role="button"><i class="glyphicon glyphicon-star-empty"></i>${d.collectCount}</a></p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
            <div class="row ">
                <c:forEach items="${designerShows}" var="d" begin="6" end="8">
                    <div class="col-sm-6 col-md-4">
                        <a href="${pageContext.request.contextPath}/welcome/designershow?showId=${d.showId}">
                            <div class="thumbnail">
                                <img src="${d.showTitlepic}"     alt="图片缺失">
                                <div class="caption">
                                    <h4>
                                        <c:if test="${d.showTitle.length()>10}">
                                            ${d.showTitle.substring(0,10)}...
                                        </c:if>
                                        <c:if test="${d.showTitle.length()<=10}">
                                            ${d.showTitle}
                                        </c:if>
                                    </h4>

                                    <small>上传时间：<fmt:formatDate value="${d.uploadTime}" pattern="yyyy-MM-dd"/></small>
                                    <p><a href="#" class="btn btn-default btn-sm"  role="button"><i class="glyphicon glyphicon-thumbs-up"></i>${d.praiseCount} </a> <a href="#" class="btn btn-default btn-sm" id="collectbtn" role="button"><i class="glyphicon glyphicon-star-empty"></i>${d.collectCount}</a></p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
            <!-- 分页 -->
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <nav aria-lable="Page navigation">
                        <ul class="pagination  pagination-lg">

                            <li><a href="${pageContext.request.contextPath}/welcome/index?pageNum=1&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}">首页</a></li>

                            <c:if test="${pageInfo.hasPreviousPage  }">
                                <li>
                                    <a href="${pageContext.request.contextPath}/welcome/index?pageNum=${pageInfo.pageNum-1}&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach items="${pageInfo.navigatepageNums  }" var="page">
                                <c:if test="${page!=pageInfo.pageNum && page>pageInfo.pageNum-3 &&page<pageInfo.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/welcome/index?pageNum=${page}&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}">${page}</a></li>
                                </c:if>
                                <c:if test="${page==pageInfo.pageNum}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/welcome/index?pageNum=${page}&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}">${page}</a></li>
                                </c:if>
                                <c:if test="${page!=pageInfo.pageNum && page<pageInfo.pageNum+3 &&page>pageInfo.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/welcome/index?pageNum=${page}&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}">${page}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${pageInfo.hasNextPage }">
                                <li>
                                    <a href="${pageContext.request.contextPath}/welcome/index?pageNum=${pageInfo.pageNum+1 }&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <li><a href="${pageContext.request.contextPath}/welcome/index?pageNum=${pageInfo.pages}&styleId=${searchContent.styleId}&sname=${searchContent.sname}&houseId=${searchContent.houseId}&areaId=${searchContent.areaId}">末页</a></li>

                        </ul>
                    </nav>
                </div>
            </div><%--分页--%>
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
