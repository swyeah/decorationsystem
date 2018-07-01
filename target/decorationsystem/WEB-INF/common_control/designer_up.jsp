<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/27
  Time: 21:05
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
    <link href="<%=basePath%>css/indexstyle.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/nav.js"></script>
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/summernote.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/locales/summernote-zh-CN.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <!-- 初始化 自定义配置summernote js -->
    <script type="text/javascript">
        //加载编辑器及自定义配置
        var baseurl = "${pageContext.request.contextPath}";
        $(document).ready(function() {
            $('#summernote').summernote({
                height: 400,//初始化默认高度
                minHeight: null, //最小高度
                maxHeight: null, //最大高度
                focus: true,//是否定位
                lang:'zh-CN',//注意这里，若要设置语言，则需要引入该语言配置js
                placeholder:"请在这里写下您的内容",
                toolbar: [
                    ['color', ['color']],
                    ['fontsize', ['fontsize']],
                    ['para', ['paragraph']],
                    ['style', ['bold','underline', 'clear']],
                    ['insert', ['picture', 'link']],
                    ['table', ['table']],
                    ['view',['codeview','fullscreen']]
                ],//配置工具栏
                //查看更多配置(https://summernote.org/deep-dive/)
                //下面重写上传图片方法
                callbacks: {
                    // summernote提供的API：onImageUpload
                    onImageUpload:function (files) {
                        var formData = new FormData();
                        //formData.append('file',files[0]);
                        for(var i=0; i< files.length; i++){
                            formData.append("files",files[i]);   // 文件对象
                        }
                        $.ajax({
                            url : "${pageContext.request.contextPath}/designer_show/upload", //后台文件上传接口
                            type : 'POST',
                            data : formData,
                            processData : false,
                            contentType : false,
                            success : function(data) {
                                console.log(data)
                                var resultJson = JSON.parse(data)
                                console.log(resultJson)
                                for (var j =0;j<resultJson['urls'].length;j++){
                                    $('#summernote').summernote('insertImage',resultJson['urls'][j],function($image) {
                                        $image.addClass('img-responsive');
                                        $image.css('width','75%');

                                    });
                                }
                            },
                            error:function(){
                                $('#myModalBody').text("上传失败，请刷新页面")
                                $('#myModal').modal('show')
                            }
                        });
                    }
                }

        });
        });



    </script>
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
        <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-0" style="padding-bottom: 50px;">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li>设计展示</li>
                    <li class="active">上传设计展示</li>
                </ol>
            </div>
            <form class="form-horizontal" id="showform">

            <div class="row">
                <div class="col-sm-1">
                    标题:
                </div>
                <div class="col-sm-8 form-group">
                    <input type="text" class="form-control" maxlength="12" name="showTitle">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-1">
                    设计风格：
                </div>
                <div class="col-sm-2 form-group">
                    <select class="form-control" name="styleId" >
                        <c:forEach items="${decorationStyles}" var="s">
                             <option value="${s.styleId}">${s.styleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-sm-1">
                    房子类型：
                </div>
                <div class="col-sm-2 form-group">
                    <select class="form-control" name="houseId" >
                        <c:forEach items="${houseTypes}" var="s">
                            <option value="${s.houseId}">${s.houseName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-sm-1">
                    面积类型：
                </div>
                <div class="col-sm-2 form-group">
                    <select class="form-control" name="areaId" >
                        <c:forEach items="${areaTypes}" var="s">
                            <option value="${s.areaId}">${s.areaName}平方</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input hidden="hidden" name="showContent" id="showContent"/>
            <input type="hidden" name="employeeId" value="${sessionScope.employee.employeeId}"/>
            </form>
            <div class="row">
                <div class="col-sm-2">内容:</div>
            </div>
            <div class="row">
                <div class="col-sm-11">
                <div id="summernote" ></div></div>
            </div>
            <div class="row">
                <div class="col-xs-3 col-sm-2  col-sm-offset-4">
                    <button id="sendform" class="btn btn-primary btn-lg" >提交</button>
                </div>
            </div>
<script>
    $('#sendform').click(function () {
        var txt = $('#summernote').summernote('code');
         $('#showContent').val(txt);
         var formdata = $('#showform').serializeObject();
        $.post({
            url: "${pageContext.request.contextPath}/designer_show/addDesignerShow",
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data: JSON.stringify(formdata),
            success : function (data) {
                console.log(data);
                if (data.msg=='1'){
                    $('#showId').val(data.showId);
                   $('#myModal2').modal('show')
                }else if(data.msg = '0'){
                    $('#myModalBody').text("提交失败，请重新提交！")
                    $('#myModal').modal('show')
                }else{
                    $('#myModalBody').text(data.msg)
                    $('#myModal').modal('show')
                }

            },
            error : function(){
                $('#myModalBody').text('发送失败,请刷新页面');
                $('#myModal').modal('show')
            }
        });

    })


</script>
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

            <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel2">
                                提示
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <div class="row"  style="margin-top: 10px;">
                                <div class="col-sm-3">请上传文章标题图片：</div>
                                <div class="col-sm-5 form-group"><input id="file1" type="file" accept="image/jpeg,image/png" class="form-control" name="file"/></div>
                                <input type="hidden" name="showId" id="showId" value=""/>
                            </div>
                            <div class="row">
                                <div class="col-sm-2 col-sm-offset-4">
                                    <button class="btn btn-primary" id="setTitlepic">上传</button>
                                </div>
                            </div>
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
<script>
    $.sidebarMenu($('.sidebar-menu'));

    $('#setTitlepic').click(function () {
    var formData = new FormData();
    var showId =  $('#showId').val();
    var file  =  $('#file1').get(0).files[0];
    formData.append("showId",showId)
    formData.append("file",file)
    if("undefined" != typeof(file) && file != null && file != ""){
        $.ajax({
            url:'${pageContext.request.contextPath}/designer_show/uploadTitlepic',
            type:'POST',
            data:formData,
            cache: false,
            contentType: false, //不设置内容类型
            processData: false, //不处理数据
            success:function(data){
                console.log(data);
                var resultJson = JSON.parse(data)
                $('#myModalBody2').text(resultJson['msg']);
                $('#myModal2').on('hidden.bs.modal', function () {
                    // 执行一些动作...
                    document.location.reload();
                })
            },
            error:function(){
                $('#myModalBody2').text("上传失败！");
            }
        })
    }else {
        $('#myModalBody2').text("选择的文件无效！请重新选择");
    }
})
</script>

</body>
</html>
