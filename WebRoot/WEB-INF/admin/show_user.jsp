<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>The_User</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<link href="static/css/custom.css" rel="stylesheet">

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function share(id){
		$("#share_id").val(id);
		$("#shareModal").modal('show');
	}
	function upload(){
		$("#uploadModal").modal('show');
	}
	function dele(){
		var ids = $('input[name="ids"]');
		var haveDeleFile = false;
		ids.each(function(){
			if($(this).prop('checked')==true){
				haveDeleFile = true;
			}
		});
		if(haveDeleFile == true){
			$("#deleModal").modal('show');
		}else{
			alert("未选择要删除的文件！");
		}
	}
	function delete_submit_form(){
		document.forms["dele_form"].submit();
	}
	function select_all(){
		var check = $("#chk_all");
		var ids = $('input[name="ids"]');
			if(check.prop('checked'))
			{
				ids.prop("checked", true);
			}
			else
			{
				ids.prop("checked", false);
				check.prop("checked", false);
			}
	}
</script>
</head>
<body>
	<div id="wrapper">
		<%@include file="common/nav.jsp" %>
		<div id="page-wrapper">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3><a href="admin?action=userList">用户列表</a>>${theUser.username }</h3>
					<div class="bs-example4" data-example-id="contextual-table">
						<button class="btn btn-danger" onclick="dele()">删除所选项</button>
						<table class="table">
							<thead>
								<tr>
									<th><input type="checkbox" id="chk_all" onchange="select_all()"></th>
									<th>文件名</th>
									<th>大小</th>
									<th>上传日期</th>
									<th>共享状态</th>
									<th>下载</th>
								</tr>
							</thead>
							<form action="admin" id="dele_form" method="post">
							<input type="hidden" name="action" value="delete">
							<input type="hidden" name="from" value="showUser&id=${theUser.id }">
							<tbody>
							<c:forEach items="${fileList }" var="userFile">
								<tr>
									<th scope="row"><input type="checkbox" name="ids" value="${userFile.id }"></th>
									<td>${userFile.filename }</td>
									<td>${userFile.fileSize }</td>
									<td>${userFile.createTime }</td>
									<td>
									<c:choose>
										<c:when test="${userFile.isShared==1 }">
					  						已分享
										</c:when>
										<c:otherwise>
											未分享
										</c:otherwise>
									</c:choose> 
									</td>
									<td><a class="btn btn-primary" href="download?id=${userFile.id }"><i class="fa fa-download"></i>下载</a></td>
								</tr>
							</c:forEach>
							</tbody>
							</form>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
<!--删除 模态框（Modal） -->
<div class="modal fade" id="deleModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
          		   文件删除
            </h4>
         </div>
         <div class="modal-body">
    		       确定要删除？
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <a class="btn btn-danger" onclick="delete_submit_form()">确定删除</a>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!--分享 模态框（Modal） -->
<div class="modal fade" id="shareModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
          		   确认
            </h4>
         </div>
         <div class="modal-body">
    		       确定要分享？
         </div>
         <div class="modal-footer">
            <form action="admin" id="share_form">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            	<input class="hidden" value="share" name="action">
            	<input class="hidden" id="share_id" name="id">
           		<input type="submit" class="btn btn-primary" value="确定" />
            </form>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--上传  模态框（Modal） -->
	<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="upload" method="post" enctype="multipart/form-data">
			<input type="hidden" name="from" value="admin">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">上传文件</h4>
					</div>
					<div class="modal-body">
						<input type="file" name="file" value="上传文件">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							关闭
						</button>
						<input type="submit" class="btn btn-primary" value="确定上传" />
					</div>
				</div>
				<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal -->
	
	
</body>
</html>
