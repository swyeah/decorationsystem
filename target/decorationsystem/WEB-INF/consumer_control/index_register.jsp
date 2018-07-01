<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/3/1
  Time: 20：06
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
        <div class="col-sm-8 col-sm-offset-2" style="margin-top: 20px;">
            <div class="row">
                <button class="btn btn-default" onclick="window.location.href='${pageContext.request.contextPath}/welcome/index'">返回首页</button>
            </div>
            <form  method="post" class="form-horizontal" id="registration_form">
                <div class="row"><h3>登记表格</h3></div>
                <hr>
                <div class="row">
                    <div class="col-sm-2">登记姓名<span class="red">*</span></div>
                    <div class="col-sm-2 form-group">
                        <input type="text" class="form-control" id="registrationName" name="registrationName">
                    </div>
                    <div class="col-sm-2">登记电话<span class="red">*</span></div>
                    <div class="col-sm-2 form-group">
                        <input type="text" class="form-control" name="registrationPhone">
                    </div>
                    <div class="col-sm-1">面积<span class="red">*</span></div>
                    <div class="col-sm-2 form-group input-group">
                        <input type="text" class="form-control" name="registrationArea">
                        <div class="input-group-addon">平方米</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 form-group">
                        <div class="col-sm-2">登记区域<span class="red">*</span></div>
                        <div class="col-sm-3">
                            <select id="province" name="provinceId"class="form-control">
                                <option value="0">请选择省份</option>
                                <c:forEach items="${provinces}" var="p">
                                    <option value="${p.provinceId}">${p.provinceName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3 form-group" >
                            <select id="city" name="cityId" class="form-control">
                                <option value="0">请选择城市</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">具体地址<span class="red">*</span></div>
                    <div class="col-sm-8 form-group">
                        <input type="text" class="form-control" name="registrationAddress">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">设计师</div>
                    <div class="col-sm-5 form-group">
                        <div class="input-group">
                            <input type="text" id="designer" name="designerId" class="form-control col-sm-6" placeholder="请输入设计师编号" aria-describedby="basic-addon2">
                            <span class="input-group-addon" id="basic-addon2"><button type="button" id="setdesigner">指定设计师</button></span>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="registrationSource" value="电销"/>
                <input type="hidden" name="userName" value="${sessionScope.consumer.userName}">
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <button class="btn btn-primary" id="sub">提交</button>
                        <input type="reset" class="btn btn-default " id="reset"  value="重置 "/>
                    </div>
                </div>

            </form>

        </div>
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

    /*指定设计师按钮响应方法*/
    $('#setdesigner').bind('click',function () {
        url = "${pageContext.request.contextPath}/welcome/getDesigner"
        show_message(url,"{pageNum:'1',search:''}","");
        $('#myModal').modal('show');
    });

    function saveDesigner(employeeId,deptId) {
        if(deptId == "1001"){
            $('#designer').val(employeeId);
        }else{
            $('#marketer').val(employeeId);
        }
        $('#myModal').modal('hide');

    }

    /*搜索按钮响应方法*/
    function search_data(){
        var search = $('#search').val();
        var json = {
            "search":search,
            "pageNum":"1"
        }
        show_message(url,JSON.stringify(json),search);

    }

    function changshow(pageNum){
        var search = $('#search').val();
        var json = {
            "search":search,
            "pageNum":pageNum
        }
        show_message(url,JSON.stringify(json),search);

    }


    function show_message(url,resultdata,searchdata) {
        $.post({
            url: url,
            dataType : "json",//预期服务器返回的类型c
            contentType: "application/json",
            data:resultdata,
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

    $('#registration_form').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            registrationName: {
                validators: {
                    notEmpty: {
                        message: '登记姓名不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 2,
                        max: 5,
                        message: '长度必须在2-5之间！'
                    },
                }
            },
            registrationPhone: {
                validators:{
                    notEmpty: {
                        message: '手机号码不能为空！'
                    },
                    stringLength: {//检测长度
                        min: 11,
                        max: 11,
                        message: '手机证号码必须为11位数字！'
                    },
                    digits: {
                        message: '手机号码只能包含数字。'
                    }
                }
            },
            registrationArea:{
                validators: {
                    notEmpty: {
                        message: '面积不能为空！'
                    },
                    digits: {
                        message: '面积只能是数字。'
                    }
                }

            },
            provinceId:{
                validators: {
                    greaterThan: {
                        value: 1,
                        message:'请选择省份！'
                    }
                }
            },
            cityId:{
                validators: {
                    greaterThan: {
                        value: 1,
                        message:'请选择城市！'
                    }
                }
            },
            registrationAddress:{
                validators:{
                    notEmpty:{
                        message:'地址不能为空！'
                    },
                    stringLength: {//检测长度
                        max: 40,
                        message: '地址长度不能超过100！'
                    }
                }
            },
            employeeId:{
                validators: {
                    digits: {
                        message: '只能是数字。'
                    }
                }

            },
            designerId:{
                validators: {
                    digits: {
                        message: '只能是数字。'
                    }
                }

            },
            userName: {
                validators: {
                    stringLength: {//检测长度
                        min: 6,
                        max: 12,
                        message: '账号长度必须在6-12之间！'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '账号只能由字母、数字、点和下划线组成！'
                    }
                }
            }
        }
    });
    $('#reset').click(function () {
        $('#registration_form').bootstrapValidator('resetForm')

    });
    $('#sub').click(function() {
        $('#registration_form').bootstrapValidator('validate');
        if($('#registration_form').data("bootstrapValidator").isValid()){

            var formdata = JSON.stringify($("#registration_form").serializeObject());
            $.post({
                url: "${pageContext.request.contextPath}/welcome/doregistration",
                dataType : "json",//预期服务器返回的类型c
                contentType: "application/json",
                data: formdata,
                success : function (data) {
                    console.log(data);
                    if(data.result == 'true'){
                        $('#myModalBody').text('登记成功');
                        $('#myModal').modal('show')
                    }else {
                        $('#myModalBody').text('登记失败,请重新登记');
                        $('#myModal').modal('show')
                    }

                },
                error : function(){
                    $('#myModalBody').text('登记失败,请刷新页面');
                    $('#myModal').modal('show')
                }
            });
        }
    });


</script>


</body>
</html>

