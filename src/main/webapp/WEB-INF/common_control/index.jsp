<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2017/12/30
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body style="padding-top:50px;">

<div class=".container-fluid ">
    <nav class="navbar navbar-inverse navbar-static-top"  style="padding: 0 0 0 0;margin: 0 0 0 0;">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" >装修设计公司管理系统</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <div class="hidden-xs" style="position: fixed;top: 50px; left: 0;width: 23%;height: 100%;background: #2c3b41;">
        <aside class="main-sidebar">
            <section  class="sidebar">
                <ul class="sidebar-menu">
                    <li class="header">主导航</li>
                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/welcome/consumer_intention" target="targetshow">
                            <i class="glyphicon glyphicon-pencil"></i>
                            <span>装修意向</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-th"></i> <span>窗口小部件</span>
                            <small class="label pull-right label-info">新的</small>
                        </a>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-pie-chart"></i>
                            <span>图表</span>
                            <i class="fa fa-angle-right pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> ChartJS</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Morris</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Flot</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Inline charts</a></li>
                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-laptop"></i>
                            <span>UI 元素</span>
                            <i class="fa fa-angle-right pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> 一般</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Icons图标</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 按钮</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 滑块</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 时间表</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 模态框</a></li>
                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-edit"></i> <span>表单</span>
                            <i class="fa fa-angle-right pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> 一般表单</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 高级表单</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 可编辑表单</a></li>
                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-table"></i> <span>表格</span>
                            <i class="fa fa-angle-right pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> 简单表格</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 时间表格</a></li>
                        </ul>
                    </li>

                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-share"></i> <span>多级</span>
                            <i class="fa fa-angle-right pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> 一级</a></li>
                            <li>
                                <a href="#"><i class="fa fa-circle-o"></i> 一级 <i class="fa fa-angle-right pull-right"></i></a>
                                <ul class="treeview-menu">
                                    <li><a href="#"><i class="fa fa-circle-o"></i> 二级</a></li>
                                    <li>
                                        <a href="#"><i class="fa fa-circle-o"></i> 二级 <i class="fa fa-angle-right pull-right"></i></a>
                                        <ul class="treeview-menu">
                                            <li><a href="#"><i class="fa fa-circle-o"></i> 三级</a></li>
                                            <li><a href="#"><i class="fa fa-circle-o"></i> 三级</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> 一级</a></li>
                        </ul>
                    </li>
                    <li class="header">标签</li>

                </ul>
            </section>
        </aside>
    </div>
    <div class="row"  style="margin-right: 0;">
        <div class="col-sm-3"></div>
        <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-0">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li class="active">装修意向</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">请填写装修意向</div>
                    <div class="panel-body">
                        <form class="form-horizontal">
                            <div class="col-sm-10">


                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label" for="province">施工地点</label>
                                        </div>
                                        <div class="col-sm-4 col-md-3">
                                            <select id="province" name="province"class="form-control">
                                                <option value="0">请选择省份</option>
                                                <c:forEach items="${provinces}" var="p">
                                                    <option value="${p.provinceId}">${p.provinceName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="col-sm-4 col-md-3">
                                            <select id="city" name="city" class="form-control">
                                                <option value="0">请选择城市</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-12" style="margin-top: 1em;">
                                            <input type="text" class="form-control" id="address" placeholder="详细地址">
                                        </div>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label  class="control-label" for="area">建筑面积</label>
                                        </div>
                                        <div class="col-sm-4 col-md-3">
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="area" name='area'>
                                                <span class="input-group-addon">㎡</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-3">
                                            <button type="submit" class="btn btn-primary">确认提交</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
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
                </div><!-- /.modal -->
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
                })
            </script>
        </div>
    </div>


</div>
<script type="text/javascript">
    $.sidebarMenu($('.sidebar-menu'))
</script>

</body>
</html>
