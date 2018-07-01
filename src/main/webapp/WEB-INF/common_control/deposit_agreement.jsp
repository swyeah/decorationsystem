<%--
  Created by IntelliJ IDEA.
  User: swye
  Date: 2018/2/18
  Time: 12:09
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
    <script type="text/javascript" src="<%=basePath%>js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.myFunc.js"></script>
    <link href="<%=basePath%>css/indexstyle.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/nav.js"></script>

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
                    <li class="active">定金协议</li>
                </ol>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                定金协议
                            </a>
                        </li>
                        <li><a href="#uploadd" data-toggle="tab">上传定金协议</a></li>
                    </ul>
                    <div class="panel-body" style="font-size: 18px;">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <c:if test="${requestScope.resultStatus == true}">
                                    <div class="row">
                                        <div class="col-sm-3">协议编号：${deposit.depositId}</div>
                                        <div class="col-sm-3">协议签订日期：<fmt:formatDate value="${deposit.depositTime}" pattern="yyyy-MM-dd"/></div>
                                        <div class="col-sm-3">协议金额：${deposit.depositMoney}RMB</div>
                                        <div class="col-sm-3">
                                            <button id="updatebtn" class="btn btn-primary">修改</button>
                                            <button id="delbtn" class="btn btn-danger">删除</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">协议描述：${deposit.depositDescribe}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">上传时间：<fmt:formatDate value="${deposit.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">协议图片：</div>
                                        <div class="col-sm-8">
                                            <img src="${deposit.depositUrl}" class="img-responsive" alt="Responsive image">
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${requestScope.resultStatus==false}">
                                    <p>无协议，请上传协议</p>
                                </c:if>
                            </div>
                            <div class="tab-pane fade" id="uploadd">
                                <form class="form-horizontal" id="uploadform" enctype="multipart/form-data" method="post">
                                    <div class="row">

                                        <div class="col-sm-2">
                                            协定签订时间
                                        </div>
                                        <div class="col-sm-3 form-group">
                                            <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                                <input class="form-control" id="depositTime" name="depositTime" size="16" type="text" value="" readonly>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <input type="hidden" id="dtp_input" value="" /><br/>

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
                                    <div class="row" style="margin-top: 10px;">
                                        <div class="col-xs-4 col-sm-2">协议金额：</div>
                                        <div class="col-xs-4 col-sm-2">
                                            <div class="input-group form-group">
                                                <input id="depositMoney" name="depositMoney" class="form-control" type="text"/>
                                                <span class="input-group-addon">RMB</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row"  style="margin-top: 10px;">
                                        <div class="col-xs-4 col-sm-2">协议文件照片：</div>
                                        <div class="col-xs-4 col-sm-3 form-group"><input id="file" type="file" accept="image/jpeg,image/png" class="form-control" name="file"/></div>
                                    </div>
                                    <div class="row" style="margin-top:10px;">
                                        <div class="col-xs-4 col-sm-2">协议文件描述：</div>
                                        <div class="col-xs-8 col-sm-3 form-group"><textarea class="form-control" cols="5" id="depositDescribe" name="depositDescribe"></textarea> </div>
                                    </div>
                                    <input type="hidden" name="registrationId" id="registrationId" value='${registrationId}'/>
                                    <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}"/>
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-3 col-sm-offset-2">
                                            <button type="button" onclick="uploadFiles()" class="btn btn-primary">上传</button>
                                            <button type="reset" id="reset" class="btn btn-default">重置</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

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
                            <h4 class="modal-title" id="myModalLabel2">
                                更新协议
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody2">
                            <form class="form-horizontal" id="updeposit">
                                <div class="row">
                                    <div class="col-sm-4">协定编号:${deposit.depositId}</div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4">
                                        协定签订时间
                                    </div>
                                    <div class="col-sm-8 form-group">
                                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                            <input class="form-control" id="depositTime1" name="depositTime" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <input type="hidden" id="dtp_input1" value="" /><br/>

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
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-3">协议金额：</div>
                                    <div class="col-sm-4">
                                        <div class="input-group form-group">
                                            <input id="depositMoney1" name="depositMoney" class="form-control" type="text"/>
                                            <span class="input-group-addon">RMB</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-sm-8">
                                        协议文件名:${deposit.filename}
                                    </div>
                                </div>
                                <div class="row"  style="margin-top: 10px;">
                                    <div class="col-sm-3">协议文件照片：</div>
                                    <div class="col-sm-5 form-group"><input id="file1" type="file" accept="image/jpeg,image/png" class="form-control" name="file"/></div>
                                </div>
                                <div class="row" style="margin-top:10px;">
                                    <div class="col-sm-3">协议文件描述：</div>
                                    <div class="col-sm-8 form-group"><textarea class="form-control" cols="5" id="depositDescribe1" name="depositDescribe"></textarea> </div>
                                </div>
                                <input type="hidden" name="depositId" id="depositId" value='${deposit.depositId}'/>
                                <input type="hidden" name="registrationId" id="registrationId1" value='${registrationId}'/>
                                <input type="hidden" name="employeeId" id="employeeId1" value="${employeeId}"/>
                                <input type="hidden" name="uploadTime" id="uploadTime" value="<fmt:formatDate value="${deposit.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-2">
                                        <button type="button" onclick="uploadFiles1()" class="btn btn-primary">更新</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

            <%--弹出提示框--%>
            <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel3">
                                提示
                            </h4>
                        </div>
                        <div class="modal-body" id="myModalBody3">
                           <button type="button" class="btn btn-danger" onclick="enterdel('${deposit.registrationId}')">确认删除</button>
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
          $('#uploadform').bootstrapValidator({
//        live: 'disabled',
              message: 'This value is not valid',
              feedbackIcons: {
                  valid: 'glyphicon glyphicon-ok',
                  invalid: 'glyphicon glyphicon-remove',
                  validating: 'glyphicon glyphicon-refresh'
              },
              fields: {
                  depositTime: {
                      validators: {
                          notEmpty: {
                              message: '不能为空！'
                          }
                      }
                  },
                  depositMoney: {
                      validators: {
                          notEmpty: {
                              message: '不能为空！'
                          },
                          regexp: {
                              regexp: /^\d+(\.\d{1,2})?$/,
                              message: '金额只能是数字，带两位小数'
                          }
                      }

                  },
                  file:{
                      validators:{
                          notEmpty:{
                              message:'不能为空'
                          }
                      }
                  }
              }
          });
          function uploadFiles(){
              $('#uploadform').bootstrapValidator('validate');
              //var bootstrapValidator = $('#login_form').data('bootstrapValidator');
              //bootstrapValidator.on('success.form.bv', function (e) {
              //e.preventDefault();
              //提交逻辑
              if($('#uploadform').data("bootstrapValidator").isValid()){
                  var formData = new FormData();
                  var uploadFile = $('#file').get(0).files[0];
                  // 	var uploadFile = new FormData($("#file")[0]);
                  var depositMoney = $('#depositMoney').val();
                  var depositDescribe = $('#depositDescribe').val();
                  var registrationId = $('#registrationId').val();
                  var depositTime = $('#depositTime').val();
                  var employeeId = $('#employeeId').val();
                  formData.append("employeeId",employeeId);
                  formData.append("depositTime",depositTime);
                  formData.append("file",uploadFile);
                  formData.append("depositMoney", depositMoney);
                  formData.append("depositDescribe", depositDescribe);
                  formData.append("registrationId", registrationId);
                  console.log(uploadFile);

                  if("undefined" != typeof(file) && file != null && file != ""){
                      $.ajax({
                          url:'${pageContext.request.contextPath}/common_intent/uploadDeposit',
                          type:'POST',
                          data:formData,
                          cache: false,
                          contentType: false, //不设置内容类型
                          processData: false, //不处理数据
                          success:function(data){
                              console.log(data);
                              var resultJson = JSON.parse(data)
                              $('#myModalBody').text(resultJson['msg']);
                              $('#myModal').modal('show');
                              $('#myModal').on('hidden.bs.modal', function () {
                                  // 执行一些动作...
                                  document.location.reload();
                              })
                          },
                          error:function(){
                              $('#myModalBody').text("上传失败！");
                              $('#myModal').modal('show')
                          }
                      })
                  }else {
                      $('#myModalBody').text("选择的文件无效！请重新选择");
                      $('#myModal').modal('show')
                  }
              }
          };
          $('#reset').click(function () {
              $('#uploadform').bootstrapValidator('resetForm')
          });

          $('#updatebtn').click(function () {
              $('#depositMoney1').val('${deposit.depositMoney}');
              $('#depositDescribe1').val('${deposit.depositDescribe}');
              $('#depositTime1').val('${deposit.depositTime}');
              $('#myModal2').modal('show')
          });
          $('#updeposit').bootstrapValidator({
//        live: 'disabled',
              message: 'This value is not valid',
              feedbackIcons: {
                  valid: 'glyphicon glyphicon-ok',
                  invalid: 'glyphicon glyphicon-remove',
                  validating: 'glyphicon glyphicon-refresh'
              },
              fields: {
                  depositTime: {
                      validators: {
                          notEmpty: {
                              message: '不能为空！'
                          }
                      }
                  },
                  depositMoney: {
                      validators: {
                          notEmpty: {
                              message: '不能为空！'
                          },
                          regexp: {
                              regexp: /^\d+(\.\d{1,2})?$/,
                              message: '金额只能是数字，带两位小数'
                          }
                      }

                  },
                  file:{
                      validators:{
                          notEmpty:{
                              message:'不能为空'
                          }
                      }
                  }
              }
          });

          function uploadFiles1(){
              $('#updeposit').bootstrapValidator('validate');
              //var bootstrapValidator = $('#login_form').data('bootstrapValidator');
              //bootstrapValidator.on('success.form.bv', function (e) {
              //e.preventDefault();
              //提交逻辑
              if($('#updeposit').data("bootstrapValidator").isValid()){
                  var formData = new FormData();
                  var uploadFile = $('#file1').get(0).files[0];
                  // 	var uploadFile = new FormData($("#file")[0]);
                  var depositMoney = $('#depositMoney1').val();
                  var depositDescribe = $('#depositDescribe1').val();
                  var registrationId = $('#registrationId1').val();
                  var depositTime = $('#depositTime1').val();
                  var employeeId = $('#employeeId1').val();
                  var depositId = $('#depositId').val();
                  var uploadTime = $('#uploadTime').val();
                  formData.append("uploadTime",uploadTime);
                  formData.append("depositId",depositId);
                  formData.append("employeeId",employeeId);
                  formData.append("depositTime",depositTime);
                  formData.append("file",uploadFile);
                  formData.append("depositMoney", depositMoney);
                  formData.append("depositDescribe", depositDescribe);
                  formData.append("registrationId", registrationId);
                  console.log(uploadFile);

                  if("undefined" != typeof(file) && file != null && file != ""){
                      $.ajax({
                          url:'${pageContext.request.contextPath}/common_intent/changeDeposit',
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
              }
          };
          function enterdel(registrationId) {
              var json = {
                  "registrationId":registrationId
              }
              $.post({
                  url: "${pageContext.request.contextPath}/common_intent/delDeposit",
                  dataType : "json",//预期服务器返回的类型c
                  contentType: "application/json",
                  data:JSON.stringify(json),
                  success : function (data) {
                      console.log(data);
                      $('#myModalBody3').text(data.result);
                      $('#myModal3').on('hidden.bs.modal', function () {
                          // 执行一些动作...
                          document.location.reload();
                      })

                  },
                  error : function(){
                      $('#myModalBody3').text('请重新刷新页面');
                  }
              });
          };

          $('#delbtn').click(function () {
              $('#myModal3').modal('show')
          });

</script>

</body>
</html>
