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
<title>User_list</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<link href="static/css/custom.css" rel="stylesheet">

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function submit_form(){
		document.forms["search_form"].submit();
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
			alert("未选择要删除的用户！");
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
					<h3><a href="admin?action=userList">用户列表</a></h3>
					<div class="bs-example4" data-example-id="contextual-table">
					<div class="row">
						<div class="col-md-2">
							<button class="btn btn-danger" onclick="dele()">删除所选用户</button>
						</div>
					</div>
						<table class="table">
							<thead>
								<tr>
									<th><input id="chk_all" onchange="select_all()" type="checkbox"></th>
									<th>用户ID</th>
									<th>用户名</th>
									<th>密码</th>
									<th>查看用户文件</th>
								</tr>
							</thead>
							<form action="admin" id="dele_form" method="post">
							<input type="hidden" name="action" value="deleteUser">
							<tbody>
								<c:forEach items="${userList }" var="user">
								<tr>
									<th scope="row"><input type="checkbox" name="ids" value="${user.id }"></th>
									<td>${user.id }</td>
									<td>${user.username }</td>
									<td>${user.password }</td>
									<td><a class="btn btn-primary" href="admin?action=showUser&id=${user.id }"><i class="fa fa-th-list"></i> 查看文件</a></td>
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
          		  用户删除
            </h4>
         </div>
         <div class="modal-body">
    		       确定要删除该用户？
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
</body>
</html>
